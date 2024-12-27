package WithTemplatePattern.AbstractClass;

public abstract class BeverageTemplate {
    // Template method defining the sequence of steps
    public final void prepareBeverage() {
        boilWater();
        brew();           // Abstract method to be implemented by subclasses
        pourInCup();
        addCondiments();  // Abstract method to be implemented by subclasses
    }

    // Common step implemented in the abstract class
    private void boilWater() {
        System.out.println("Boiling water...");
    }

    // Common step implemented in the abstract class
    private void pourInCup() {
        System.out.println("Pouring into the cup...");
    }

    // Abstract methods to be implemented by subclasses
    protected abstract void brew();
    protected abstract void addCondiments();

}
