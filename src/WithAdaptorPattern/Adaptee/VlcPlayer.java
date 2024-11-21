package WithAdaptorPattern.Adaptee;

public class VlcPlayer implements AdvancedMediaPlayer{
    @Override
    public void playMp4(String fileName) {
        //  Do nothing, as this player doesn't support MP4
    }

    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing VLC file: " + fileName);
    }

    // Each concrete class (`Mp4Player` and `VlcPlayer`) implements only the functionality it supports (`MP4` or `VLC`).
}
