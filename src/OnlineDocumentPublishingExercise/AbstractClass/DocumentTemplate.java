package OnlineDocumentPublishingExercise.AbstractClass;

public abstract class DocumentTemplate {
    // Template method defining the sequence of steps
    public void publishDocument(){
        writeContent();
        editContent();
        publishContent();
        addExtras();  // Specific to each document type
    }

    // Common steps implemented in the abstract class
    private void editContent() {
        System.out.println("Editing content...");
    }

    private void publishContent() {
        System.out.println("Publishing content online...");
    }

    // Abstract methods to be implemented by subclasses
    protected abstract void writeContent();
    protected abstract void addExtras();
}

