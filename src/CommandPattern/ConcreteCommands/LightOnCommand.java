package CommandPattern.ConcreteCommands;

import CommandPattern.Interface.Command;
import CommandPattern.Receiver.Light;

public class LightOnCommand implements Command {
    private Light light;

    // Constructor that binds the receiver
    public LightOnCommand(Light light){
        this.light=light;
    }
    // Command execution delegates to the receiver
    @Override
    public void execute() {
        light.turnOn(); // call the method on receiver
    }
}
