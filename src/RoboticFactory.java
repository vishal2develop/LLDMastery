import java.util.HashMap;
import java.util.Map;

public class RoboticFactory {
    // in memory cache for robot objects
    private static Map<String, IRobot> robotCache = new HashMap<>();

    public static IRobot createRobot(String robotType){
        // check if robot type already exists in cache
        if(robotCache.containsKey(robotType)){
            return robotCache.get(robotType);
        }
        else{
            // create robot types, add to cache and return the robot
            if(robotType=="HUMANOID"){
                Sprites humanoidSprite = new Sprites();
                IRobot humanoidRobot = new HumanoidRobot(robotType,humanoidSprite);
                robotCache.put(robotType, humanoidRobot);
                return humanoidRobot;
            }
            else if(robotType=="DOG"){
                Sprites dogSprite = new Sprites();
                IRobot dogRobot = new DogRobot(robotType,dogSprite);
                robotCache.put(robotType, dogRobot);
                return dogRobot;
            }
        }
        return null;
    }
}
