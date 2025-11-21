//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        IRobot humanoidRobot = RoboticFactory.createRobot("HUMANOID");
        humanoidRobot.display(10,20);

        IRobot humanoidRobot2 = RoboticFactory.createRobot("HUMANOID");
        humanoidRobot2.display(50,60);

        IRobot dogRobot = RoboticFactory.createRobot("DOG");
        dogRobot.display(100,200);

        IRobot dogRobot2 = RoboticFactory.createRobot("DOG");
        dogRobot2.display(500,600);

    }
}