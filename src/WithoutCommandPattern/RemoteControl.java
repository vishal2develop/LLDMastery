package WithoutCommandPattern;

public class RemoteControl {
    public void pressButton(String device) {
        if (device.equals("light")) {
            System.out.println("Light turned on");
        } else if (device.equals("fan")) {
            System.out.println("Fan turned on");
        }
    }
}
