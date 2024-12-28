package MusicStreamingExerciseV2.ConcreteIterators;

import MusicStreamingExerciseV2.Interfaces.Iterator;
import MusicStreamingExerciseV2.Song;

import java.util.List;

public class PlaylistIterator implements Iterator<Song> {
    private final List<Song> songs;
    private int position;

    public PlaylistIterator(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean hasNext() {
        return position < songs.size();
    }

    @Override
    public Song next() {
        return songs.get(position++);
    }

}
