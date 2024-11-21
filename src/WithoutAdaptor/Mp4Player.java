package WithoutAdaptor;

public class Mp4Player {

    Mp4Player(){
        System.out.println("Creating Mp4Player");
    }

    public void playMp4(String filename){
        System.out.println("Playing Mp4 filename = " + filename);
    }
}
