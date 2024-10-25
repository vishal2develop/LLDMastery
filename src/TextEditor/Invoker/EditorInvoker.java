package TextEditor.Invoker;

import TextEditor.Interface.Command;

import java.util.Stack;

public class EditorInvoker {
    private Stack<Command> history = new Stack<>();  // Stack to track command history
    private Stack<Command> redoStack = new Stack<>();  // Stack to track undone commands

    // Execute a command and add it to history
    public void executeCommand(Command command) {
        command.execute();
        history.push(command);
        // Clear the redo stack whenever a new command is executed
        redoStack.clear();
    }

    // Undo the last command
    public void undoLastCommand() {
        if (!history.isEmpty()) {

            Command lastCommand = history.pop();
            lastCommand.undo();
            redoStack.push(lastCommand); // Move to redo stack for potential redo
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    public void redoLastCommand(){
        if (!redoStack.isEmpty()){
            Command lastCommand = redoStack.pop(); // take most recent command
            lastCommand.execute(); // execute it
            history.push(lastCommand); // And Move it back to history stack
        }
        else{
            System.out.println("Nothing to Redo");
        }
    }

}
