package MusicStreamingExerciseV2.ConcreteCollections;

import MusicStreamingExerciseV2.ConcreteIterators.PlaylistIterator;
import MusicStreamingExerciseV2.Interfaces.Collection;
import MusicStreamingExerciseV2.Interfaces.Iterator;
import MusicStreamingExerciseV2.Song;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements Collection<Song> {

    private List<Song> songs = new ArrayList<>();


    public void addSong(Song song){
        songs.add(song);
    }

    @Override
    public Iterator<Song> createIterator() {
        return new PlaylistIterator(songs);
    }
}
