package WithAdaptorPattern.Adaptee;

public interface AdvancedMediaPlayer {
    /**
     * The `AdvancedMediaPlayer` is a third-party library or legacy system that has a different interface.
     * It supports playing **MP4** and **VLC** file formats, with separate methods for each format.
     */
    void playMp4(String fileName);
    void playVlc(String fileName);
}
