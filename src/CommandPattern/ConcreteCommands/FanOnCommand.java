package CommandPattern.ConcreteCommands;

import CommandPattern.Interface.Command;
import CommandPattern.Receiver.Fan;

public class FanOnCommand implements Command {
    private Fan fan;

    // Constructor that binds the receiver
    public FanOnCommand(Fan fan) {
        this.fan = fan;
    }

    // Command execution delegates to the receiver


    @Override
    public void execute() {
        fan.turnOn();
    }
}
