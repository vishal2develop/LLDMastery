import java.util.ArrayList;
import java.util.Stack;

public class ConfigurationCaretaker {
    ArrayList<ConfigurationMemento> configurationMementos = new ArrayList<>();

    public void addMemento(ConfigurationMemento configurationMemento){
        configurationMementos.add(configurationMemento);
    }

    public ConfigurationMemento undo(){
        if(!configurationMementos.isEmpty()){
            // get last memento
            int lastMementoIndex = configurationMementos.size()-1;

            // get last memento
            ConfigurationMemento lastMemento = configurationMementos.get(lastMementoIndex);

            // remove last memento
            configurationMementos.remove(lastMementoIndex);
            // return last memento
            return lastMemento;
        }
        // return null if no mementos exist
        return null;
    }
}
