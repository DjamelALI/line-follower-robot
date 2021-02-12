package ev3dev.actuators;

import ev3dev.actuators.lego.motors.EV3LargeRegulatedMotor;
import ev3dev.sensors.Battery;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class RunMotors {
    // Vars
    private final EV3LargeRegulatedMotor leftMotor, rightMotor;
    private int leftMotorSpeed, rightMotorSpeed;

    public RunMotors() {
        System.out.println("Creating Motor A & D");
        leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
        rightMotor = new EV3LargeRegulatedMotor(MotorPort.D);

        //To Stop the motor in case of pkill java for example
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Emergency Stop");
            leftMotor.stop();
            rightMotor.stop();
        }));

        System.out.println("Defining the Stop mode");
        leftMotor.brake();
        rightMotor.brake();

        System.out.println("Defining motor speed");
        leftMotorSpeed = 240;
        rightMotorSpeed = 240;
        leftMotor.setSpeed(leftMotorSpeed);
        rightMotor.setSpeed(rightMotorSpeed);
    }

    public void startTurning() {

        System.out.println("Go Forward with the motors");
        leftMotor.forward();
        rightMotor.forward();

        Delay.msDelay(3000);

        System.out.println("Stop motors");
        leftMotor.stop();
        rightMotor.stop();

        System.out.println("Go Backward with the motors");
        leftMotor.backward();
        rightMotor.backward();

        Delay.msDelay(3000);

        System.out.println("Stop motors");
        leftMotor.stop();
        rightMotor.stop();

        System.out.println("Checking Battery");
        System.out.println("Voltage: " + Battery.getInstance().getVoltage());

        System.exit(0);
    }

    public int getLeftMotorSpeed() {
        return leftMotorSpeed;
    }

    public void setLeftMotorSpeed(int _leftMotorSpeed) {
        this.leftMotorSpeed = _leftMotorSpeed;
    }

    public int getRightMotorSpeed() {
        return rightMotorSpeed;
    }

    public void setRightMotorSpeed(int _rightMotorSpeed) {
        this.rightMotorSpeed = _rightMotorSpeed;
    }
}