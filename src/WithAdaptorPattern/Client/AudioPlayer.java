package WithAdaptorPattern.Client;

import WithAdaptorPattern.Adaptor.MediaAdapter;
import WithAdaptorPattern.Interface.MediaPlayer;

public class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter; // Reference to adapter to handler mp4 & vlc types

    /**
     * The `AudioPlayer` class checks the audio type.
     * If it’s `mp3`, it plays the file directly.
     * If it’s `mp4` or `vlc`, it creates an adapter to handle the playback.
     */
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing MP3 file: " + fileName);
        } else if (audioType.equalsIgnoreCase("mp4") || audioType.equalsIgnoreCase("vlc")) {
            mediaAdapter = new MediaAdapter(audioType);  // Use the adapter for non-MP3 files
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media type: " + audioType + " format not supported");
        }
    }
}
