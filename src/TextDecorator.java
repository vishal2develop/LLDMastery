// Abstract Decorator class
/**
 * Takes in a TextComponent and decorates it.
 * getText() is implemented in the base class.(TextComponent)
 */
abstract public class TextDecorator implements TextComponent {
    protected TextComponent textComponent;

    public TextDecorator(TextComponent textComponent) {
        this.textComponent = textComponent;
    }

    @Override
    public String getText() {
        return textComponent.getText();
    }
}
