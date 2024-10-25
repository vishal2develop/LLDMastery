package TextEditor;

import TextEditor.ConcreteCommands.AddTextCommand;
import TextEditor.ConcreteCommands.RemoveTextCommand;
import TextEditor.Interface.Command;
import TextEditor.Invoker.EditorInvoker;
import TextEditor.Receiver.TextEditor;

public class Main {
    public static void main(String[] args) {
        // 1. Receiver
        TextEditor textEditor = new TextEditor();

        // 2. Invoker
        EditorInvoker editorInvoker = new EditorInvoker();

        // 3. Commands
        Command addTextCommand1 = new AddTextCommand(textEditor, "Hello ");
        Command addTextCommand2 = new AddTextCommand(textEditor, "World!");
        Command removeTextCommand = new RemoveTextCommand(textEditor, 6);

        // 4. Execute Commands
        editorInvoker.executeCommand(addTextCommand1); // Adds "Hello "
        editorInvoker.executeCommand(addTextCommand2);   // Adds "World!"
        editorInvoker.executeCommand(removeTextCommand); // Removes "World!"

        // Undo last command (re-add "World!")
        editorInvoker.undoLastCommand();

        // Redo last command (remove "World!" again)
        editorInvoker.redoLastCommand();

        // Undo all commands to clear the text
        editorInvoker.undoLastCommand();
        editorInvoker.undoLastCommand();
        editorInvoker.undoLastCommand();

        // Redo all commands to restore "Hello World!"
        editorInvoker.redoLastCommand();
        editorInvoker.redoLastCommand();

    }
}
