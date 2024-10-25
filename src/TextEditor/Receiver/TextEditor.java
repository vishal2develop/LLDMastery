package TextEditor.Receiver;

public class TextEditor {
    private StringBuilder content = new StringBuilder();

    // Add text to the editor
    public void addText(String text){
        content.append(text);
        System.out.println("Added text: " + text);
        System.out.println("Current content: " + content);
    }

    // Remove text from the editor and return the removed text
    public String removeText(int textLength){
        int start = content.length() - textLength;
        String removed = content.substring(start);
        content.delete(start, content.length());
        System.out.println("Removed text: " + removed);
        System.out.println("Current content: " + content);
        return removed;
    }

    public String getContent() {
        return content.toString();
    }

}
