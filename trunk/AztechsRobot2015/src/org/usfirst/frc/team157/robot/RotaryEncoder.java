
package org.usfirst.frc.team157.robot;

import java.io.File;
import java.util.Scanner;
import edu.wpi.first.wpilibj.AnalogInput;

public class RotaryEncoder extends AnalogInput
{
	public File f;
	public Scanner fileScanner;
	public Scanner lineScanner;
	
	public double lowEndVoltage;
	public double highEndVoltage;
	
	public RotaryEncoder(int channel, String fileName)
	{
		super(channel);
		
		lowEndVoltage = -1;
		highEndVoltage = 4;
		
		/*
		 * f = new File(fileName);
		 * try
		 * {
		 * fileScanner = new Scanner(f);
		 * }
		 * catch (FileNotFoundException e)
		 * {
		 * // TODO Auto-generated catch block
		 * e.printStackTrace();
		 * }
		 * 
		 * lineScanner = new Scanner(fileScanner.next);
		 * 
		 * System.out.println(lineScanner.next());
		 */
	}
}
