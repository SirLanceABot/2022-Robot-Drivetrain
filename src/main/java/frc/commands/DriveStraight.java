package frc.commands;

import java.lang.invoke.MethodHandles;

import edu.wpi.first.math.geometry.Translation2d;
import frc.drivetrain.Drivetrain;
import frc.robot.RobotContainer;

public class DriveStraight implements Command 
{
    private static final String fullClassName = MethodHandles.lookup().lookupClass().getCanonicalName();

    // *** STATIC INITIALIZATION BLOCK ***
    // This block of code is run first when the class is loaded
    static
    {
        System.out.println("Loading: " + fullClassName);
    }

    // *** CLASS & INSTANCE VARIABLES ***
    private static final Drivetrain DRIVETRAIN = RobotContainer.DRIVETRAIN;
    private double speed_metersPerSecond;
    private double distance_meters;
    private boolean isFinished;
    private final double SLOW_RAMP_RATE = 0.75;
    private final double FAST_RAMP_RATE = 0.1;

    private Translation2d startingPosition;

    // This variable is only used to simulate the distance driven
    // private double distanceDrivenSimulation = 0.0;


    // *** CLASS CONSTRUCTOR ***
    public DriveStraight(double speed_metersPerSecond, double distance_meters)
    {
        this.speed_metersPerSecond = speed_metersPerSecond;
        this.distance_meters = distance_meters;
        isFinished = false;
    }

    
    // *** CLASS & INSTANCE METHODS ***
    public void init()
    {
        System.out.println(this);

        isFinished = false;
        // distanceDrivenSimulation = 0.0;

        // DRIVETRAIN.resetEncoders();
        startingPosition = DRIVETRAIN.getCurrentTranslation();
        DRIVETRAIN.configOpenLoopRamp(SLOW_RAMP_RATE);
    }

    public void execute()
    {
        // distanceDrivenSimulation += 0.05;
        // System.out.printf("Distance driven: %5.2f meters\n", distanceDrivenSimulation);

        // if(distanceDrivenSimulation >= distance_meters)
        // {
        //     isFinished = true;
        // }

        if(DRIVETRAIN.driveStraight(startingPosition, speed_metersPerSecond, distance_meters))
        {
            System.out.println("Finished driving");
            isFinished = true;
        }
    }

    public boolean isFinished()
    {
        return isFinished;
    }

    public void end()
    {
        DRIVETRAIN.configOpenLoopRamp(FAST_RAMP_RATE);
        DRIVETRAIN.stopMotor();
        // DRIVETRAIN.resetEncoders();
    }

    public String toString()
    {
        return "DriveDistance(" + speed_metersPerSecond + ", " + distance_meters + ")";
    }
}