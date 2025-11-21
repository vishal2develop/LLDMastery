

public class HumanoidRobot implements IRobot {
    // intrinsic (shared) properties
    private String type;
    private Sprites body;

    HumanoidRobot(String type, Sprites body) {
        this.type = type;
        this.body = body;
    }

    @Override
    public void display(int x, int y) {
        System.out.println("Displaying Humanoid Robot at (" + x + ", " + y+")");
    }

    // getters
    public String getType() {
        return type;
    }
    public Sprites getBody() {
        return body;
    }
}
