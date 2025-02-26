import Enums.Direction;
import Enums.ElevatorState;

public class ElevatorCar {
    int id;
    ElevatorDisplay display;
    int currentFloor;
    Direction elevatorDirection;
    ElevatorState elevatorState;
    InternalButtons internalButtons;
    ElevatorDoor elevatorDoor;


    public ElevatorCar(){
        display = new ElevatorDisplay();
        internalButtons = new InternalButtons();
        elevatorDoor = new ElevatorDoor();
        elevatorState = ElevatorState.IDLE;
        currentFloor=0;
        elevatorDirection = Direction.UP;
    }

    public void showDisplay(){
        display.showDisplay();
    }

    public void setDisplay(){
        this.display.setDisplay(currentFloor,elevatorDirection);
    }

    boolean moveElevator(int floor, Direction direction){
        return true;
    }
}
