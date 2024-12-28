package MusicStreamingExercise;

import MusicStreamingExercise.ConcreteCollection.SongCollection;
import MusicStreamingExercise.Interfaces.Iterator;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        SongCollection collection = new SongCollection(3);

        collection.addSong(new Song("Genius","Labrinth"));
        collection.addSong(new Song("Boulevard of broken dreams","Greenday"));
        collection.addSong(new Song("Stronger","The Score"));

        // Traverse using iterator
        Iterator<Song> iterator = collection.createIterator();

        while(iterator.hasNext()){
            System.out.println("Song Title:"+iterator.next().getTitle());
        }
    }
}
