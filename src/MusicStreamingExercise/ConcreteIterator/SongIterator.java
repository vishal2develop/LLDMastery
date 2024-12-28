package MusicStreamingExercise.ConcreteIterator;

import MusicStreamingExercise.Interfaces.Iterator;
import MusicStreamingExercise.Song;

public class SongIterator implements Iterator<Song> {

    private Song[] songs;
    private int position;

    public SongIterator(Song[] songs){
        this.songs = songs;
    }

    @Override
    public boolean hasNext() {
        if(position<songs.length && songs[position]!=null){
            return true;
        }
        return false;
    }

    @Override
    public Song next() {
        Song song = songs[position];
        position+=1;
        return song;
    }
}
