package ConcreteClasses;

import AbstractClass.LogProcessor;

public class InfoLogger extends LogProcessor {

    @Override
    public void log(String message, String level) {
        if(level.equals("INFO")){
            System.out.println("[INFO]: message = " + message + ", level = " + level);
        }
        else if(nextLogProcessor!=null){
            nextLogProcessor.log(message,level);
        }
    }
}
