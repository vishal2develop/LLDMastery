public class ConfigurationOriginator {
    int height;
    int width;

    public ConfigurationOriginator(int height, int width) {
        this.height = height;
        this.width = width;
    }

    // Create a new memento object - Create a snapshot of the current state
    public ConfigurationMemento createMemento(){
        return new ConfigurationMemento(height, width);
    }
    // Restore a memento object - Restore to a given snapshot
    public void restoreMemento(ConfigurationMemento configurationMemento){
        this.height = configurationMemento.getHeight();
        this.width = configurationMemento.getWidth();
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
