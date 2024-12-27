package OnlineDocumentPublishingExercise.ConcreteClass;

import OnlineDocumentPublishingExercise.AbstractClass.DocumentTemplate;

public class ResearchPaper extends DocumentTemplate {

    @Override
    protected void writeContent() {
        System.out.println("Writing research paper content...");
    }

    @Override
    protected void addExtras() {
        System.out.println("Adding references and citations...");
    }
}
