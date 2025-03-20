import Interfaces.Image;

public class Main {
    public static void main(String[] args) {
        // Creating images
        Image image1 = new ImageProxy("image1.png");
        Image image2 = new ImageProxy("image2.png");

        // Images are not loaded yet
        System.out.println("\nDisplaying Images:");
        image1.display(); // from disk
        image1.display(); // from cache
        image2.display(); // from disk
        image2.display(); // from cache

    }
}