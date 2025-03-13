package ConcreteClasses;

import AbstractClass.LogProcessor;

public class ErrorLogger extends LogProcessor {

    @Override
    public void log(String message, String level) {
        if(level.equals("ERROR")){
            System.out.println("[ERROR]: message = " + message + ", level = " + level);
        }
        else{
            System.out.println("[UNKNOWN]: message = " + message + ", level = " + level);
        }
    }
}
