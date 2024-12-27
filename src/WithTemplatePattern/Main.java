package WithTemplatePattern;

import WithTemplatePattern.AbstractClass.BeverageTemplate;
import WithTemplatePattern.ConcreteClass.Coffee;
import WithTemplatePattern.ConcreteClass.Tea;

public class Main {
    public static void main(String[] args) {
        System.out.println("Preparing Tea:");
        BeverageTemplate tea = new Tea();
        tea.prepareBeverage();  // Calls the template method for tea

        System.out.println("\nPreparing Coffee:");
        BeverageTemplate coffee = new Coffee();
        coffee.prepareBeverage();  // Calls the template method for coffee
    }
}
