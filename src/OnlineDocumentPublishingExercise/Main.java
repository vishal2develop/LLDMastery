package OnlineDocumentPublishingExercise;

import OnlineDocumentPublishingExercise.AbstractClass.DocumentTemplate;
import OnlineDocumentPublishingExercise.ConcreteClass.Blog;
import OnlineDocumentPublishingExercise.ConcreteClass.NewsArticle;
import OnlineDocumentPublishingExercise.ConcreteClass.ResearchPaper;

public class Main {

    public static void main(String[] args) {
        System.out.println("Publishing Blog:");
        DocumentTemplate blog = new Blog();
        blog.publishDocument(); // Calls the template method for Blog

        System.out.println("\nPublishing News Article:");
        DocumentTemplate article = new NewsArticle();
        article.publishDocument();  // Calls the template method for News Article

        System.out.println("\nPublishing Research Paper:");
        DocumentTemplate paper = new ResearchPaper();
        paper.publishDocument();  // Calls the template method for Research Paper
    }
}
