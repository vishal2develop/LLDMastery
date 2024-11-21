## Applying the Adapter Pattern

### **Classes Involved**

1. **Target Interface**: `MediaPlayer` – defines a method `play()` that accepts a file and plays it.
2. **Adaptee**: `AdvancedMediaPlayer` – a third-party library or legacy code that has its own way of playing `MP4` and `VLC` formats.
3. **Adapter**: `MediaAdapter` – converts the interface of `AdvancedMediaPlayer` to `MediaPlayer`.
4. **Client**: The client interacts with `MediaPlayer`, unaware of `AdvancedMediaPlayer`.

---

### **1. Target Interface**

The `MediaPlayer` interface represents the standard media player interface, which the client expects. It has a single `play()` method that takes in an audio type (e.g., `mp3`, `mp4`, `vlc`) and a file name.


```java
// Target Interface
public interface MediaPlayer {
    void play(String audioType, String fileName);
}

```

This is the interface our client code will interact with.

---

### **2. Adaptee**

The `AdvancedMediaPlayer` is a third-party library or legacy system that has a different interface. It supports playing **MP4** and **VLC** file formats, with separate methods for each format.

---

### **3. Adapter Class**

The `MediaAdapter` class implements the **Target Interface** (`MediaPlayer`). It holds a reference to an **Adaptee** (`AdvancedMediaPlayer`) and translates calls from `play()` to the appropriate method in `AdvancedMediaPlayer`.

- `MediaAdapter` determines which `AdvancedMediaPlayer` implementation to use based on the file type.
- It translates the `play()` method in `MediaPlayer` to the corresponding `playMp4()` or `playVlc()` method in `AdvancedMediaPlayer`.

---

### **4. Concrete MediaPlayer Implementation**

The `AudioPlayer` class is our main **MediaPlayer** implementation. It can handle **MP3** files directly and uses `MediaAdapter` for **MP4** and **VLC** formats.

- The `AudioPlayer` class checks the audio type.
- If it’s `mp3`, it plays the file directly.
- If it’s `mp4` or `vlc`, it creates an adapter to handle the playback.

---

### **5. Client Code**

The client code interacts with `MediaPlayer` through the `AudioPlayer` class. It uses the `AudioPlayer` class to play different file types without needing to know about `AdvancedMediaPlayer` or `MediaAdapter`.

---

### **Explanation**

1. **Target Interface (`MediaPlayer`)**:
    - The interface the client expects. The client can use `MediaPlayer` to play audio files.
2. **Adaptee (`AdvancedMediaPlayer`)**:
    - The existing/legacy(incompatible) library with its own interface for playing MP4 and VLC formats.
3. **Adapter (`MediaAdapter`)**:
    - Implements `MediaPlayer`, adapts `AdvancedMediaPlayer` to be used with `MediaPlayer`.
4. **Client Code**:
    - The client (`AudioPlayer`) can now use `MediaAdapter` to handle MP4 and VLC files without needing changes.

---

### **When to Use the Adapter Pattern**

- When you need to integrate an existing class with an incompatible interface into your system.
- When you want to avoid modifying or extending an existing class, especially if it is from a third-party library or legacy code.
- When you want to reuse existing code in a new environment that expects a different interface.

---

### **Benefits of the Adapter Pattern**

- **Decouples Client from Adaptee**: The client doesn’t need to know the details of the adaptee, only the adapter.
- **Improves Reusability**: Allows you to reuse existing classes without modifying them.
- **Supports Open/Closed Principle**: New adapters can be added without changing the core classes.

---

