package TextEditor.ConcreteCommands;

import TextEditor.Interface.Command;
import TextEditor.Receiver.TextEditor;

public class RemoveTextCommand implements Command {
    private TextEditor textEditor;
    private int lengthToRemove;
    private String removedText;  // Store removed text for undo

    public RemoveTextCommand(TextEditor textEditor,int lengthToRemove){
        this.textEditor=textEditor;
        this.lengthToRemove=lengthToRemove;
    }

    @Override
    public void execute() {
        removedText = textEditor.removeText(lengthToRemove);

    }

    @Override
    public void undo() {
        if (removedText!=null){
            textEditor.addText(removedText);
        }

    }
}
