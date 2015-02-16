
package org.usfirst.frc.team157.robot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

// FIXME replace these in proper place
// TODO calibration technique... forget about that until end of build day

@Deprecated
public class Calibration
{
	private File f;
	private Scanner fileScanner;
	private Scanner lineScanner;
	private PrintStream printToFile;
	
	private double lowEndVoltage;
	
	private double highEndVoltage;
	
	public Calibration(String filePath)
	{
		lowEndVoltage = -1;
		highEndVoltage = 4;
		
		f = new File(filePath);
		
		if (!f.exists())
		{
			System.out.println("File not found rotary encoder: " + filePath);
			System.out.println("Setting defualt values!");
			createDefualtFile();
		}
		else
		{
			assignValuesFromFile();
			try
			{
				printToFile = new PrintStream(new File("results.txt"));
			}
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public double getHighEndVoltage()
	{
		return highEndVoltage;
	}
	
	public double getLowEndVoltage()
	{
		return lowEndVoltage;
	}
	
	private void assignValuesFromFile()
	{
		// File should be found by now
		try
		{
			fileScanner = new Scanner(f);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lineScanner = new Scanner(fileScanner.nextLine()); // Low: #
		lineScanner.next(); // Low:
		lowEndVoltage = Integer.parseInt(lineScanner.next()); // #
		
		lineScanner = new Scanner(fileScanner.nextLine()); // High: #
		lineScanner.next(); // High:
		highEndVoltage = Integer.parseInt(lineScanner.next()); // #
	}
	
	private void createDefualtFile()
	{
		System.out.println("Creating a defualt values file in rotary encoder: " + f.getName());
		try
		{
			f.createNewFile();
		}
		catch (IOException e)
		{
			System.out.println("COULD NOT CREATE FILE!");
		}
		try
		{
			printToFile = new PrintStream(f);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		printToFile.println("Low: " + lowEndVoltage);
		printToFile.println("High: " + highEndVoltage);
	}
}
