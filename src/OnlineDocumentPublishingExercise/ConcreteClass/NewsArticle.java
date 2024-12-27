package OnlineDocumentPublishingExercise.ConcreteClass;

import OnlineDocumentPublishingExercise.AbstractClass.DocumentTemplate;

public class NewsArticle extends DocumentTemplate {

    @Override
    protected void writeContent() {
        System.out.println("Writing news article content...");
    }

    @Override
    protected void addExtras() {
        System.out.println("Adding headline and summary...");
    }
}
