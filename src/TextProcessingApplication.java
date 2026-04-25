public class TextProcessingApplication {

    public static void main(String[] args) {
        // Step1: Create a plain text component
        TextComponent text = new PlainText("Hello World!");

        //  Step2: Decorate the text with our decorators
        TextComponent formattedText = new FontDecorator(new ColorDecorator(text, "red"), "Arial");

        // get formatted text
        System.out.println(formattedText.getText());
    }
}
