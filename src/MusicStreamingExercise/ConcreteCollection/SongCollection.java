package MusicStreamingExercise.ConcreteCollection;

import MusicStreamingExercise.ConcreteIterator.SongIterator;
import MusicStreamingExercise.Interfaces.Collection;
import MusicStreamingExercise.Interfaces.Iterator;
import MusicStreamingExercise.Song;


public class SongCollection implements Collection<Song> {

    private Song[] songs;
    private int index;

    public SongCollection(int size){
        songs = new Song[size]; // initializing a new array of size = size
        index=0;
    }

    public void addSong(Song song){
        if(index<songs.length){
            songs[index] = song;
            index+=1;
        }
        else{
            System.out.println("Collection is full!");
        }

    }

    @Override
    public Iterator<Song> createIterator() {
        return new SongIterator(songs);
    }
}
