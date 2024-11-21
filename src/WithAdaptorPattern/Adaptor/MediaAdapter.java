package WithAdaptorPattern.Adaptor;

import WithAdaptorPattern.Adaptee.AdvancedMediaPlayer;
import WithAdaptorPattern.Adaptee.Mp4Player;
import WithAdaptorPattern.Adaptee.VlcPlayer;
import WithAdaptorPattern.Interface.MediaPlayer;

/**
 * The `MediaAdapter` class implements the **Target Interface** (`MediaPlayer`).
 * It holds a reference to an **Adaptee** (`AdvancedMediaPlayer`) and translates calls from `play()`
 * to the appropriate method in `AdvancedMediaPlayer`.
 */
public class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedMediaPlayer; // Holds reference to legacy code

    // Constructor determines which AdvancedMediaPlayer to use
    public MediaAdapter(String audioType){
        if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer = new Mp4Player();
        } else if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer = new VlcPlayer();
        }
    }

    // Translate the play() call to the appropriate AdvancedMediaPlayer method


    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer.playMp4(fileName);
        } else if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer.playVlc(fileName);
        }
    }
}
