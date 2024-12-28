package MusicStreamingExerciseV2;

import MusicStreamingExerciseV2.ConcreteCollections.Playlist;
import MusicStreamingExerciseV2.Interfaces.Iterator;

public class Main {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.addSong(new Song("Genius","Labrinth"));
        playlist.addSong(new Song("Boulevard of broken dreams","Greenday"));
        playlist.addSong(new Song("Stronger","The Score"));

        // traverse collection using iterator
        Iterator<Song> iterator = playlist.createIterator();
        System.out.println("Playlist:");
        while(iterator.hasNext()){
            Song song = iterator.next();
            System.out.println(song.getTitle() + " by " + song.getArtist());
        }

    }
}
