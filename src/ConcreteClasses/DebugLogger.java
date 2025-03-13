package ConcreteClasses;

import AbstractClass.LogProcessor;

public class DebugLogger extends LogProcessor {

    @Override
    public void log(String message, String level) {
        if(level.equals("DEBUG")){
            System.out.println("[DEBUG]: message = " + message + ", level = " + level);
        }
        else if(nextLogProcessor!=null){
            nextLogProcessor.log(message,level);
        }
    }
}
