package AbstractClass;

public abstract class LogProcessor {
    protected LogProcessor nextLogProcessor;

    public void setNextHandler(LogProcessor logProcessor){
        this.nextLogProcessor = logProcessor;
    }

    public void log(String message,String level){
        if(nextLogProcessor!=null){
            nextLogProcessor.log(message,level);
        }
    }
}
