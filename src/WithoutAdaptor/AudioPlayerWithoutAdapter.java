package WithoutAdaptor;

public class AudioPlayerWithoutAdapter implements MediaPlayer {
        private Mp4Player mp4Player = new Mp4Player();
        private VlcPlayer vlcPlayer = new VlcPlayer();

        @Override
        public void play(String audioType, String fileName) {
            if (audioType.equalsIgnoreCase("mp3")) {
                System.out.println("Playing MP3 file: " + fileName);
            } else if (audioType.equalsIgnoreCase("mp4")) {
                mp4Player.playMp4(fileName);  // Directly calling Mp4Player
            } else if (audioType.equalsIgnoreCase("vlc")) {
                vlcPlayer.playVlc(fileName);  // Directly calling VlcPlayer
            } else {
                System.out.println("Invalid media type: " + audioType + " format not supported");
            }
        }
}
