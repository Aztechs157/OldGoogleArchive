
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;
import edu.wpi.first.wpilibj.image.NIVision.Rect;

/**
 * Sample program to use NIVision to find rectangles in the scene that are illuminated
 * by a LED ring light (similar to the model from FIRSTChoice). The camera sensitivity
 * is set very low so as to only show light sources and remove any distracting parts
 * of the image.
 * 
 * The CriteriaCollection is the set of criteria that is used to filter the set of
 * rectangles that are detected. In this example we're looking for rectangles with
 * a minimum width of 30 pixels and maximum of 400 pixels.
 * 
 * The algorithm first does a color threshold operation that only takes objects in the
 * scene that have a bright green color component. Then a convex hull operation fills 
 * all the rectangle outlines (even the partially occluded ones). Then a small object filter
 * removes small particles that might be caused by green reflection scattered from other 
 * parts of the scene. Finally all particles are scored on rectangularity, aspect ratio,
 * and hollowness to determine if they match the target.
 *
 * Look in the VisionImages directory inside the project that is created for the sample
 * images as well as the NI Vision Assistant file that contains the vision command
 * chain (open it with the Vision Assistant)
 */

public class VisionSampleProject2013 extends SimpleRobot {

    final int XMAXSIZE = 24;
    final int XMINSIZE = 24;
    final int YMAXSIZE = 24;
    final int YMINSIZE = 48;
    final double xMax[] = {1, 1, 1, 1, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, 1, 1, 1, 1};
    final double xMin[] = {.4, .6, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, 0.6, 0};
    final double yMax[] = {1, 1, 1, 1, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, 1, 1, 1, 1};
    final double yMin[] = {.4, .6, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05,
								.05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05,
								.05, .05, .6, 0};
    
    final int RECTANGULARITY_LIMIT = 60;
    final int ASPECT_RATIO_LIMIT = 75;
    final int X_EDGE_LIMIT = 40;
    final int Y_EDGE_LIMIT = 60;
    
    final int X_IMAGE_RES = 320;          //X Image resolution in pixels, should be 160, 320 or 640
    final int Y_IMAGE_RES = (X_IMAGE_RES * 3)/4;
    //    final double VIEW_ANGLE = 43.5;       //Axis 206 camera
    final double VIEW_ANGLE = 48;       //Axis M1011 camera
    
    final boolean SAVE_IMAGE = false;
    final int BORDER_TOL = 5;
    
    AxisCamera camera;          // the axis camera object (connected to the switch)
    CriteriaCollection cc;      // the criteria for doing the particle filter operation
    
    private static final int HIGH_GOAL = 0;
    private static final int MIDDLE_GOAL = 1;
    private static final int LOW_GOAL = 2;
    
    public class Scores {
        double rectangularity;
        double highGoalAspectRatio;
        double middleGoalAspectRatio;
        double lowGoalAspectRatio;
        double xEdge;
        double yEdge;
        boolean onEdge;
    }
    
    public void robotInit() {
        System.out.println("robotInit() ================================================================================");
        camera = AxisCamera.getInstance("10.1.57.11");  // get an instance of the camera
        cc = new CriteriaCollection();      // create the criteria for the particle filter
        cc.addCriteria(MeasurementType.IMAQ_MT_AREA, 500, 65535, false);
    }

    public void autonomous() {
        while (isAutonomous() && isEnabled()) {
            try {
                /**
                 * Do the image capture with the camera and apply the algorithm
                 * described above. This sample will either get images from the
                 * camera or from an image file stored in the top level
                 * directory in the flash memory on the cRIO. The file name in
                 * this case is "testImage.jpg"
                 *
                 */
                ColorImage image;
                // Read Image from Camera
                    image = camera.getImage();     // comment if using stored images
                if(SAVE_IMAGE) image.write("/1_baseimage.bmp");

                // Read Stored Image
//                image = new RGBImage("/1_baseimage.bmp");		// get the sample image from the cRIO flash
                
//                System.out.println("Got Image");
//                BinaryImage thresholdImage = image.thresholdRGB(60, 100, 90, 255, 20, 255);   // keep only red objects
                BinaryImage thresholdImage = image.thresholdRGB(0, 78, 76, 179, 71, 133);   // keep only green objects
//                BinaryImage thresholdImage = image.thresholdHSV(60, 100, 90, 255, 20, 255);   // keep only green objects
                if(SAVE_IMAGE) thresholdImage.write("/2_threshold.bmp");
                BinaryImage convexHullImage = thresholdImage.convexHull(false);          // fill in occluded rectangles
                if(SAVE_IMAGE) convexHullImage.write("/3_convexHull.bmp");
                BinaryImage filteredImage = convexHullImage.particleFilter(cc);           // filter out small particles
                if(SAVE_IMAGE) filteredImage.write("/4_filteredImage.bmp");

                //iterate through each particle and score to see if it is a target
                Scores scores[] = new Scores[filteredImage.getNumberParticles()];
                for (int i = 0; i < scores.length; i++) {
                    ParticleAnalysisReport report = filteredImage.getParticleAnalysisReport(i);
                    scores[i] = new Scores();
                    
                    scores[i].rectangularity = scoreRectangularity(report);
                    scores[i].middleGoalAspectRatio = scoreAspectRatio(filteredImage,report, i, MIDDLE_GOAL);
                    scores[i].highGoalAspectRatio = scoreAspectRatio(filteredImage, report, i, HIGH_GOAL);
                    scores[i].lowGoalAspectRatio = scoreAspectRatio(filteredImage, report, i, LOW_GOAL);                    
                    scores[i].xEdge = scoreXEdge(thresholdImage, report);
                    scores[i].yEdge = scoreYEdge(thresholdImage, report);
                    scores[i].onEdge = isTouchingEdge(report);

                    boolean goalFound = false;     
                    
                    if(scoreCompare(scores[i], HIGH_GOAL)) // High Goal
                    {
                        System.out.println("particle: " + i + "is a === High Goal === Strength: " + scores[i].highGoalAspectRatio + " centerX: " + report.center_mass_x_normalized + "centerY: " + report.center_mass_y_normalized);
//			System.out.println("Distance: " + computeDistance(thresholdImage, report, i, false));
                        goalFound = true; 

                    }
                    else if (scoreCompare(scores[i], MIDDLE_GOAL)) // Mid Goal 
                    {
                        System.out.println("particle: " + i + "is a === Middle Goal === Strength: " + scores[i].lowGoalAspectRatio + " centerX: " + report.center_mass_x_normalized + "centerY: " + report.center_mass_y_normalized);

                         goalFound = true; 
			
                    }
                    else if (scoreCompare(scores[i], LOW_GOAL)) // Mid Goal 
                    {
			System.out.println("particle: " + i + "is a === Low Goal === Strength: " + scores[i].lowGoalAspectRatio + " centerX: " + report.center_mass_x_normalized + "centerY: " + report.center_mass_y_normalized);
//			System.out.println("Distance: " + computeDistance(thresholdImage, report, i, true));
//                        System.out.println("----------------------------------------------- L: " + report.boundingRectLeft + " R: " + (report.boundingRectLeft + report.boundingRectWidth));
//                        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||| T: " + report.boundingRectTop  + " B: " + (report.boundingRectTop  + report.boundingRectHeight)); 
                         goalFound = true; 
                    }
                    else
                    {
                        System.out.println("particle: " + i + "is not a goal  centerX: " + report.center_mass_x_normalized + "centerY: " + report.center_mass_y_normalized);
                    }
                    
                    if (goalFound) { 
                       //			System.out.println("Distance: " + computeDistance(thresholdImage, report, i, true));
                        double yMinDeg = -24.0;
                        double yMaxDeg = +24.0;
                        double xMinDeg = -24.0;
                        double xMaxDeg = +24.0;
                        // get target normalized within image
//                        double tiltDeltaAngleDeg = report.center_mass_y_normalized * tiltCameraConv + tiltCameraMin;
                        double xDeltaDeg = (double)(report.center_mass_x_normalized + 1) / 2.0 * (xMaxDeg - xMinDeg) + xMinDeg;
                        double yDeltaDeg = (double)(-report.center_mass_y_normalized + 1) / 2.0 * (yMaxDeg - yMinDeg) + yMinDeg;
                        System.out.println("tilt: delta= *****************************************************************************x=" + xDeltaDeg + ",y="+yDeltaDeg);
                    }
//                    int centeredX = report.center_mass_x - (320 / 2);
//                    int centeredY = report.center_mass_y - (240 / 2);
//                    double azimuth = (VIEW_ANGLE / 320.0) * (double) centeredY;
//                    double elevation = ((VIEW_ANGLE * (3.0 / 4.0)) / 240.0) * (double) centeredX;
//                    System.out.println("P[" + i + "] " + centeredX + "-" + centeredY + " Az, El, Range = " + azimuth + ", " + elevation + ", " + range);

//                    System.out.println("rect: " + scores[i].rectangularity + "ARinner: " + scores[i].highGoalAspectRatio);
//                    System.out.println("ARouter: " + scores[i].middleGoalAspectRatio + "xEdge: " + scores[i].xEdge + "yEdge: " + scores[i].yEdge);	
//
//                    System.out.println("P[" + i + "] AREA = " + report.particleArea);
//                    System.out.println("P[" + i + "] ASPECT = " + ((double)report.boundingRectHeight / (double)report.boundingRectWidth));
//                    System.out.println("P[" + i + "] X,Y = " + report.center_mass_x + ", " + report.center_mass_y);

                }

                
                /**
                 * all images in Java must be freed after they are used since they are allocated out
                 * of C data structures. Not calling free() will cause the memory to accumulate over
                 * each pass of this loop.
                 */
                filteredImage.free();
                convexHullImage.free();
                thresholdImage.free();
                image.free();
                
//            } catch (AxisCameraException ex) {        // this is needed if the camera.getImage() is called
//                ex.printStackTrace();
            } catch (NIVisionException ex) {
                System.out.println("NI Vision Exception");
                ex.printStackTrace();
            } catch (AxisCameraException ex) {
                System.out.println("Axis Camera Exception");
                ex.printStackTrace();
            }

            if(SAVE_IMAGE)
            {
                System.out.println("Something is done!");
                break;
            }
        }
        System.out.println("Out of loop... Finished");
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
            Timer.delay(1);
        }
    }
    
    /**
     * Computes the estimated distance to a target using the height of the particle in the image. For more information and graphics
     * showing the math behind this approach see the Vision Processing section of the ScreenStepsLive documentation.
     * 
     * @param image The image to use for measuring the particle estimated rectangle
     * @param report The Particle Analysis Report for the particle
     * @param outer True if the particle should be treated as an outer target, false to treat it as a center target
     * @return The estimated distance to the target in Inches.
     */
    double computeDistance (BinaryImage image, ParticleAnalysisReport report, int particleNumber, boolean outer) throws NIVisionException {
            double rectShort, height;
            int targetHeight;

            rectShort = NIVision.MeasureParticle(image.image, particleNumber, false, MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
            //using the smaller of the estimated rectangle short side and the bounding rectangle height results in better performance
            //on skewed rectangles
            height = Math.min(report.boundingRectHeight, rectShort);
            targetHeight = outer ? 29 : 21;

            return X_IMAGE_RES * targetHeight / (height * 12 * 2 * Math.tan(VIEW_ANGLE*Math.PI/(180*2)));
    }
    
    public boolean isTouchingEdge(ParticleAnalysisReport report)
    {
        int left = report.boundingRectLeft;
        int right = report.boundingRectLeft + report.boundingRectWidth;
        int top = report.boundingRectTop;
        int bottom = report.boundingRectTop  + report.boundingRectHeight;
                
        if ((left < BORDER_TOL) ||
            (right > (Y_IMAGE_RES - BORDER_TOL)) ||
            (top > (X_IMAGE_RES - BORDER_TOL)) ||
            (bottom < BORDER_TOL))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Computes a score (0-100) comparing the aspect ratio to the ideal aspect ratio for the target. This method uses
     * the equivalent rectangle sides to determine aspect ratio as it performs better as the target gets skewed by moving
     * to the left or right. The equivalent rectangle is the rectangle with sides x and y where particle area= x*y
     * and particle perimeter= 2x+2y
     * 
     * @param image The image containing the particle to score, needed to performa additional measurements
     * @param report The Particle Analysis Report for the particle, used for the width, height, and particle number
     * @param outer	Indicates whether the particle aspect ratio should be compared to the ratio for the inner target or the outer
     * @return The aspect ratio score (0-100)
     */
    public double scoreAspectRatio(BinaryImage image, ParticleAnalysisReport report, int particleNumber, int goal) throws NIVisionException
    {
        //              Opening     Outside Tape
        //              Horiz Vert  Horiz Vert    Altitude      
        // Low Goal     29    24    37    32      19        (1x 1pt)
        // Middle Goal  54    21    62    29      88 5/8    (2x 2pt)
        // High Goal    54    12    62    20      104 1/8   (1x 3pt)
        
        double rectLong, rectShort, aspectRatio;

        rectLong = NIVision.MeasureParticle(image.image, particleNumber, false, MeasurementType.IMAQ_MT_EQUIVALENT_RECT_LONG_SIDE);
        rectShort = NIVision.MeasureParticle(image.image, particleNumber, false, MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
//        idealAspectRatio = outer ? (62/29) : (62/20);	//Dimensions of goal opening + 4 inches on all 4 sides for reflective tape
	
        double idealAspectRatio = 62/20;  // default is high goal  
        if (goal == HIGH_GOAL) idealAspectRatio = 62/20;        // high goal AR (outside tape)
        else if (goal == MIDDLE_GOAL) idealAspectRatio = 62/29; // mid goal AR (outside tape)
        else if (goal == LOW_GOAL) idealAspectRatio = 37/32;    // low goal AR (outside tape)
            
        //Divide width by height to measure aspect ratio
        if(report.boundingRectWidth > report.boundingRectHeight){
            //particle is wider than it is tall, divide long by short
            aspectRatio = 100*(1-Math.abs((1-((rectLong/rectShort)/idealAspectRatio))));
        } else {
            //particle is taller than it is wide, divide short by long
                aspectRatio = 100*(1-Math.abs((1-((rectShort/rectLong)/idealAspectRatio))));
        }
	return (Math.max(0, Math.min(aspectRatio, 100.0)));		//force to be in range 0-100
    }
    
    /**
     * Compares scores to defined limits and returns true if the particle appears to be a target
     * 
     * @param scores The structure containing the scores to compare
     * @param outer True if the particle should be treated as an outer target, false to treat it as a center target
     * 
     * @return True if the particle meets all limits, false otherwise
     */
    boolean scoreCompare(Scores scores, int goal) {
        boolean isTarget = true;

        if (goal == HIGH_GOAL) {
            isTarget &= scores.highGoalAspectRatio > ASPECT_RATIO_LIMIT;
        } else if (goal == MIDDLE_GOAL) {
            isTarget &= scores.middleGoalAspectRatio > ASPECT_RATIO_LIMIT;
        } else if (goal == LOW_GOAL) {
            isTarget &= scores.lowGoalAspectRatio > ASPECT_RATIO_LIMIT;
        }
        isTarget &= scores.rectangularity > RECTANGULARITY_LIMIT;
        isTarget &= scores.xEdge > X_EDGE_LIMIT;
        isTarget &= scores.yEdge > Y_EDGE_LIMIT;

        return isTarget;
    }
    
    /**
     * Computes a score (0-100) estimating how rectangular the particle is by comparing the area of the particle
     * to the area of the bounding box surrounding it. A perfect rectangle would cover the entire bounding box.
     * 
     * @param report The Particle Analysis Report for the particle to score
     * @return The rectangularity score (0-100)
     */
    double scoreRectangularity(ParticleAnalysisReport report){
            if(report.boundingRectWidth*report.boundingRectHeight !=0){
                    return 100*report.particleArea/(report.boundingRectWidth*report.boundingRectHeight);
            } else {
                    return 0;
            }	
    }
    
    /**
     * Computes a score based on the match between a template profile and the particle profile in the X direction. This method uses the
     * the column averages and the profile defined at the top of the sample to look for the solid vertical edges with
     * a hollow center.
     * 
     * @param image The image to use, should be the image before the convex hull is performed
     * @param report The Particle Analysis Report for the particle
     * 
     * @return The X Edge Score (0-100)
     */
    public double scoreXEdge(BinaryImage image, ParticleAnalysisReport report) throws NIVisionException
    {
        double total = 0;
        LinearAverages averages;
        
        Rect rect = new Rect(report.boundingRectTop, report.boundingRectLeft, report.boundingRectHeight, report.boundingRectWidth);
        averages = NIVision.getLinearAverages(image.image, LinearAverages.LinearAveragesMode.IMAQ_COLUMN_AVERAGES, rect);
        float columnAverages[] = averages.getColumnAverages();
        for(int i=0; i < (columnAverages.length); i++){
                if(xMin[(i*(XMINSIZE-1)/columnAverages.length)] < columnAverages[i] 
                   && columnAverages[i] < xMax[i*(XMAXSIZE-1)/columnAverages.length]){
                        total++;
                }
        }
        total = 100*total/(columnAverages.length);
        return total;
    }
    
    /**
	 * Computes a score based on the match between a template profile and the particle profile in the Y direction. This method uses the
	 * the row averages and the profile defined at the top of the sample to look for the solid horizontal edges with
	 * a hollow center
	 * 
	 * @param image The image to use, should be the image before the convex hull is performed
	 * @param report The Particle Analysis Report for the particle
	 * 
	 * @return The Y Edge score (0-100)
	 *
    */
    public double scoreYEdge(BinaryImage image, ParticleAnalysisReport report) throws NIVisionException
    {
        double total = 0;
        LinearAverages averages;
        
        Rect rect = new Rect(report.boundingRectTop, report.boundingRectLeft, report.boundingRectHeight, report.boundingRectWidth);
        averages = NIVision.getLinearAverages(image.image, LinearAverages.LinearAveragesMode.IMAQ_ROW_AVERAGES, rect);
        float rowAverages[] = averages.getRowAverages();
        for(int i=0; i < (rowAverages.length); i++){
                if(yMin[(i*(YMINSIZE-1)/rowAverages.length)] < rowAverages[i] 
                   && rowAverages[i] < yMax[i*(YMAXSIZE-1)/rowAverages.length]){
                        total++;
                }
        }
        total = 100*total/(rowAverages.length);
        return total;
    }
    
}
        