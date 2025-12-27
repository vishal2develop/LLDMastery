import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        // Create instance of Caretaker & Originator
        ConfigurationCaretaker configurationCaretaker = new ConfigurationCaretaker();

        ConfigurationOriginator configurationOriginator = new ConfigurationOriginator(100, 200);

        // Create snapshot of state
        ConfigurationMemento snapshot1 = configurationOriginator.createMemento();
        // Add snapshot to caretaker
        configurationCaretaker.addMemento(snapshot1);

        // change state of originator
        configurationOriginator.setHeight(300);
        configurationOriginator.setWidth(400);

        // Create & save snapshot to state
        ConfigurationMemento snapshot2 = configurationOriginator.createMemento();
        configurationCaretaker.addMemento(snapshot2);

        // Again, change state of originator
        configurationOriginator.setHeight(500);
        configurationOriginator.setWidth(600);



        // Get state to restore to - Restores to recently created snapshot
        ConfigurationMemento restoreSnapshot = configurationCaretaker.undo();
        // Restore state
        configurationOriginator.restoreMemento(restoreSnapshot);

        System.out.println("height: " + configurationOriginator.height + " width: " + configurationOriginator.width);
    }
}