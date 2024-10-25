package CommandPattern.Invoker;

import CommandPattern.Interface.Command;

public class RemoteControl {
    private Command command;

    // Set the command to be executed
    public void setCommand(Command command) {
        this.command = command;
    }

    // Execute the command
    public void pressButton() {
        command.execute();
    }
}
