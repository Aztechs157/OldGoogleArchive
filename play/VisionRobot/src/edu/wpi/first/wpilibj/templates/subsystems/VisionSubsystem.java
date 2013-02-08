/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import com.sun.squawk.DebuggerSupport;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.LinearAverages;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;


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

/**
 *
 * @author aztechs
 */
public class VisionSubsystem extends Subsystem implements Runnable {

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

    
    AxisCamera camera_;          // the axis camera object (connected to the switch)
    CriteriaCollection cc_;      // the criteria for doing the particle filter operation

    int goalFound_;
    double xDeltaNorm_;
    double yDeltaNorm_;
    double xDeltaDeg_;
    double yDeltaDeg_;
    
    boolean enabled_;
    
    final double yMinDeg_ = -24.0;
    final double yMaxDeg_ = +24.0;
    final double xMinDeg_ = -24.0;
    final double xMaxDeg_ = +24.0;
    
//    public enum GoalType { GOAL__NONE, GOAL__HIGH, GOAL__MIDDLE, GOAL__LOW }
    
    public static final int GOAL__NONE = 0;
    public static final int GOAL__HIGH = 1;
    public static final int GOAL__MIDDLE = 2;
    public static final int GOAL__LOW = 3;
    
    public class Scores {
        double rectangularity;
        double highGoalAspectRatio;
        double middleGoalAspectRatio;
        double lowGoalAspectRatio;
        double xEdge;
        double yEdge;
        boolean onEdge;
    }

    
    public void initDefaultCommand() {
        Thread t = new Thread(this);
        t.start();
    }
    
    public VisionSubsystem()
    {
        camera_ = AxisCamera.getInstance("10.1.57.11");  // get an instance of the camera
        cc_ = new CriteriaCollection();      // create the criteria for the particle filter
        cc_.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA, 500, 65535, false);


        goalFound_ = GOAL__NONE;
        xDeltaNorm_ = 0.0;
        yDeltaNorm_ = 0.0;
        xDeltaDeg_ = 0.0;
        yDeltaDeg_ = 0.0;

        enabled_ = false;
    }
    
    public int goalFound()
    {
        return goalFound_;
    }
    
    public double getXErrorNorm()
    {
        return xDeltaNorm_;
    }
    
    public double getYErrorNorm()
    {
        return yDeltaNorm_;
    }
    
    public double getXErrorDeg()
    {
        return xDeltaDeg_;
    }
    
    public double getYErrorDeg()
    {
        return yDeltaDeg_;
    }
    
    public void enable()
    {
        enabled_ = true;
    }
    
    public void disable()
    {
        enabled_ = false;
        goalFound_ = GOAL__NONE;
    }
    
    public void run()
    {
        while(true)
        {
            try
            {
                camera_.getImage();
                System.out.println("Camera Initialized!");
                break;
            } catch (NIVisionException ex) {
                System.out.println("NI Vision Exception");
                ex.printStackTrace();
            } catch(AxisCameraException ex) {
                System.out.println("Camera Initializing..."); 
                Timer.delay(1.0);
            }
        }

        while(true)
        {
            
            if(enabled_)
            {
                try{
//                    System.out.println("Processing new image.");
                    ColorImage image;
                    image = camera_.getImage();     // comment if using stored images

//                System.out.println("Got Image");
//                BinaryImage thresholdImage = image.thresholdRGB(60, 100, 90, 255, 20, 255);   // keep only red objects
                    BinaryImage thresholdImage = image.thresholdRGB(0, 78, 76, 179, 71, 133);   // keep only green objects
//                BinaryImage thresholdImage = image.thresholdHSV(60, 100, 90, 255, 20, 255);   // keep only green objects
                    if(SAVE_IMAGE) thresholdImage.write("/2_threshold.bmp");
                    BinaryImage convexHullImage = thresholdImage.convexHull(false);          // fill in occluded rectangles
                    if(SAVE_IMAGE) convexHullImage.write("/3_convexHull.bmp");
                    BinaryImage filteredImage = convexHullImage.particleFilter(cc_);           // filter out small particles
                    if(SAVE_IMAGE) filteredImage.write("/4_filteredImage.bmp");

                    //iterate through each particle and score to see if it is a target
                    Scores scores[] = new Scores[filteredImage.getNumberParticles()];
                    double lowestDist = 10.0; 
                    int closestTargetIdx = 0; 
                    int closestGoalType = GOAL__NONE;
                    int goalFound = GOAL__NONE; 
                    if(scores.length>0)
                    {
//                        System.out.println("Scoring "+scores.length+" particles");
                        for (int i = 0; i < scores.length; i++) {
                            ParticleAnalysisReport report = filteredImage.getParticleAnalysisReport(i);
                            scores[i] = new Scores();
                    
                            scores[i].rectangularity = scoreRectangularity(report);
                            scores[i].middleGoalAspectRatio = scoreAspectRatio(filteredImage,report, i, GOAL__MIDDLE);
                            scores[i].highGoalAspectRatio = scoreAspectRatio(filteredImage, report, i, GOAL__HIGH);
                            scores[i].lowGoalAspectRatio = scoreAspectRatio(filteredImage, report, i, GOAL__LOW);
                            scores[i].xEdge = scoreXEdge(thresholdImage, report);
                            scores[i].yEdge = scoreYEdge(thresholdImage, report);
                            scores[i].onEdge = isTouchingEdge(report); 
                    
                            if(scoreCompare(scores[i], GOAL__HIGH)) // High Goal
                            {
  //                              System.out.println("particle: " + i + "is a === High Goal === Strength: " + scores[i].highGoalAspectRatio + " centerX: " + report.center_mass_x_normalized + "centerY: " + report.center_mass_y_normalized);
                                goalFound = GOAL__HIGH; 
                            }
                            else if (scoreCompare(scores[i], GOAL__MIDDLE)) // Mid Goal 
                            {
 //                               System.out.println("particle: " + i + "is a === Middle Goal === Strength: " + scores[i].lowGoalAspectRatio + " centerX: " + report.center_mass_x_normalized + "centerY: " + report.center_mass_y_normalized);
                                goalFound = GOAL__MIDDLE; 
                            }
                            else if (scoreCompare(scores[i], GOAL__LOW)) // Mid Goal 
                            {
//                                System.out.println("particle: " + i + "is a === Low Goal === Strength: " + scores[i].lowGoalAspectRatio + " centerX: " + report.center_mass_x_normalized + "centerY: " + report.center_mass_y_normalized);
                                goalFound = GOAL__LOW; 
                            }
                    
                            if (goalFound != GOAL__NONE) { 
//                                System.out.println("Particle "+i+" is a goal.");
                                if ( Math.abs(report.center_mass_x_normalized) < lowestDist) { 
//                                    System.out.println("  particle "+i+" is closer than "+closestTargetIdx+" @ "+lowestDist);
//                                    System.out.println("Replacing "+lowestDist+" with "+Math.abs(report.center_mass_x_normalized));
                                    closestGoalType = goalFound;
                                    closestTargetIdx = i; 
                                    lowestDist = Math.abs(report.center_mass_x_normalized); 
//                                    System.out.println("  new lowest is "+lowestDist);
                                }  
                                
                                // get target normalized within image
                                                      
                            }
                            
                        }
                        
                        if (goalFound != GOAL__NONE)
                        {
                            ParticleAnalysisReport report = filteredImage.getParticleAnalysisReport(closestTargetIdx);
                            System.out.println("                                                               ** "+closestTargetIdx+" of "+scores.length+"**");
                            //System.out.println("particle: " + closestTargetIdx + " is the  === PICKED === Strength: " + scores[closestTargetIdx].highGoalAspectRatio + " centerX: " + report.center_mass_x_normalized + "centerY: " + report.center_mass_y_normalized);
                            xDeltaNorm_ = report.center_mass_x_normalized;
                            yDeltaNorm_ = -report.center_mass_y_normalized;
                            xDeltaDeg_ = (double)(xDeltaNorm_ + 1) / 2.0 * (xMaxDeg_ - xMinDeg_) + xMinDeg_;
                            yDeltaDeg_ = (double)(yDeltaNorm_ + 1) / 2.0 * (yMaxDeg_ - yMinDeg_) + yMinDeg_;
                            goalFound_ = closestGoalType; 
                        }
                        else
                        {
                            goalFound_ = GOAL__NONE;
                        }
                    }
                    else
                    {
                        goalFound_ = GOAL__NONE; 
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
                } catch (AxisCameraException ex) {        // this is needed if the camera.getImage() is called
                    ex.printStackTrace();
                } catch (NIVisionException ex) {
                    ex.printStackTrace();
                }
            }
            else
            {
                System.out.println("Camera Disabled");
                Timer.delay(0.5);
            }
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

            rectShort = NIVision.MeasureParticle(image.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
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
            (right > (X_IMAGE_RES - BORDER_TOL)) ||
            (top < BORDER_TOL ) ||
            (bottom > (Y_IMAGE_RES - BORDER_TOL)))
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

        rectLong = NIVision.MeasureParticle(image.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_LONG_SIDE);
        rectShort = NIVision.MeasureParticle(image.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
//        idealAspectRatio = outer ? (62/29) : (62/20);	//Dimensions of goal opening + 4 inches on all 4 sides for reflective tape
	
        double idealAspectRatio = 62/20;  // default is high goal  
        if (goal == GOAL__HIGH) idealAspectRatio = 62/20;        // high goal AR (outside tape)
        else if (goal == GOAL__MIDDLE) idealAspectRatio = 62/29; // mid goal AR (outside tape)
        else if (goal == GOAL__LOW) idealAspectRatio = 37/32;    // low goal AR (outside tape)
            
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

        if(scores.onEdge)
        {
            return false;
        }
        else
        {
            if (goal == GOAL__HIGH) {
                isTarget &= scores.highGoalAspectRatio > ASPECT_RATIO_LIMIT;
            } else if (goal == GOAL__MIDDLE) {
                isTarget &= scores.middleGoalAspectRatio > ASPECT_RATIO_LIMIT;
            } else if (goal == GOAL__LOW) {
                isTarget &= scores.lowGoalAspectRatio > ASPECT_RATIO_LIMIT;
            }
            isTarget &= scores.rectangularity > RECTANGULARITY_LIMIT;
            isTarget &= scores.xEdge > X_EDGE_LIMIT;
            isTarget &= scores.yEdge > Y_EDGE_LIMIT;

            return isTarget;
        }
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
        
        NIVision.Rect rect = new NIVision.Rect(report.boundingRectTop, report.boundingRectLeft, report.boundingRectHeight, report.boundingRectWidth);
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
        
        NIVision.Rect rect = new NIVision.Rect(report.boundingRectTop, report.boundingRectLeft, report.boundingRectHeight, report.boundingRectWidth);
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
    
//}
    
}
