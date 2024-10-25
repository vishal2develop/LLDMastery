package CommandPattern;

import CommandPattern.ConcreteCommands.FanOnCommand;
import CommandPattern.ConcreteCommands.LightOnCommand;
import CommandPattern.Interface.Command;
import CommandPattern.Invoker.RemoteControl;
import CommandPattern.Receiver.Fan;
import CommandPattern.Receiver.Light;

public class Main {
    public static void main(String[] args) {
        // Receivers
        Light livingRoomLight = new Light();
        Fan ceilingFan = new Fan();

        // Concrete Commands
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command fanOn = new FanOnCommand(ceilingFan);

        // Invoker (Remote Control)
        RemoteControl remote = new RemoteControl();

        // Turn on the light
        remote.setCommand(lightOn);
        remote.pressButton();  // Output: "The light is on"

        // Turn on the fan
        remote.setCommand(fanOn);
        remote.pressButton();  // Output: "The fan is on"
    }
}
