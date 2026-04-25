public class ColorDecorator extends TextDecorator{

    private String color;

    public ColorDecorator(TextComponent textComponent, String color) {
        super(textComponent);
        this.color = color;
    }

    @Override
    public String getText() {
        return "<span style='color: "+color+"'>"+super.getText()+"</span>";
    }
}
