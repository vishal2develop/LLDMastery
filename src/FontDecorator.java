public class FontDecorator extends TextDecorator{
    // Decorator specific attributes and methods
    private String fontFace;

    public FontDecorator(TextComponent textComponent, String fontFace) {
        super(textComponent);
        this.fontFace = fontFace;
    }

    @Override
    public String getText() {
        // apply font style to the text
        return "<font face='" + fontFace + "'>" + super.getText() + "</font>";
    }
}
