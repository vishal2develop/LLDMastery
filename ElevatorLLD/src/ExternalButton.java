import Enums.Direction;

public class ExternalButton {
    ExternalDispatcher externalDispatcher = new ExternalDispatcher();

    void pressButton(int destination, Direction direction){
        externalDispatcher.submitExternalRequest(destination,direction);
    }
}
