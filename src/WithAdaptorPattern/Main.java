package WithAdaptorPattern;

import WithAdaptorPattern.Client.AudioPlayer;
import WithAdaptorPattern.Interface.MediaPlayer;

/**
 * The client code interacts with `MediaPlayer` through the `AudioPlayer` class.
 * It uses the `AudioPlayer` class to play different file types without needing to know about
 * `AdvancedMediaPlayer` or `MediaAdapter`.
 */
public class Main {
    public static void main(String[] args) {
        MediaPlayer audioPlayer = new AudioPlayer();

        // Directly supported by AudioPlayer
        audioPlayer.play("mp3", "song.mp3");

        // Played via MediaAdapter
        audioPlayer.play("mp4", "movie.mp4");
        audioPlayer.play("vlc", "video.vlc");

        // Unsupported format
        audioPlayer.play("avi", "unsupported.avi");

    }
}
