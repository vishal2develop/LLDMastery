package WithAdaptorPattern.Adaptee;

public class Mp4Player implements AdvancedMediaPlayer{

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing MP4 file: " + fileName);
    }

    @Override
    public void playVlc(String fileName) {
        // Do nothing, as this player doesn't support VLC
    }

    // Each concrete class (`Mp4Player` and `VlcPlayer`) implements only the functionality it supports (`MP4` or `VLC`).
}
