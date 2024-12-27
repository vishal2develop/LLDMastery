package WithoutTemplatePattern;

public class Main {
    public static void main(String[] args) {
        Tea tea = new Tea();
        System.out.println("Preparing Tea:");
        tea.prepareTea();

        Coffee coffee = new Coffee();
        System.out.println("\nPreparing Coffee:");
        coffee.prepareCoffee();
    }
}