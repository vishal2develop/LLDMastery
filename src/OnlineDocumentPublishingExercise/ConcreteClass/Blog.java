package OnlineDocumentPublishingExercise.ConcreteClass;

import OnlineDocumentPublishingExercise.AbstractClass.DocumentTemplate;

public class Blog extends DocumentTemplate {

    @Override
    protected void writeContent() {
        System.out.println("Writing blog content...");
    }

    @Override
    protected void addExtras() {
        System.out.println("Adding images and tags...");
    }
}
