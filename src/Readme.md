## **Proxy Pattern**

The **Proxy Pattern** is a **structural design pattern** that provides a **surrogate or placeholder** for another object to control access to it. It is useful when **direct access to an object is expensive, restricted, or needs additional functionality** (e.g., security, logging, caching).

## **Key Concepts**

1. **Subject (Interface)** – Defines the common interface for the real object and the proxy.
2. **Real Subject (Actual Object)** – The actual implementation that performs the real work.
3. **Proxy (Surrogate Object)** – Controls access to the real object by adding extra functionality like **caching, security, logging, or lazy initialization**.


## **Example Scenario: Image Loading System**

We have an application that **loads high-resolution images**. Loading an image is **expensive** because it requires fetching data from disk or a network. Instead of loading the image **immediately**, we can use a **proxy** to delay loading until it’s actually needed.

---

## Without Proxy (Problematic Approach)**
```java
public class RealImage {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromDisk(); // Expensive operation
    }

    private void loadImageFromDisk() {
        System.out.println("Loading image from disk: " + filename);
    }

    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

public class Main {
    public static void main(String[] args) {
        RealImage image1 = new RealImage("photo1.png");
        image1.display();

        RealImage image2 = new RealImage("photo2.png");
        image2.display();
    }
}
```

### **Problems with This Approach**

❌ **Expensive Initialization** – Images are loaded **even if they are never displayed**.  
❌ **Inefficient Memory Usage** – Storing multiple high-resolution images takes up memory.  
❌ **Cannot Control Access** – No security or caching mechanism is applied.

## Solution: Using Proxy Pattern

Instead of loading the image immediately, we create a **proxy** that **loads the image only when needed** (Lazy Loading).

> Refer Solution

### **Explanation**

1. **Proxy delays expensive operations** (Lazy Loading).
2. **Only loads the image when `display()` is called**.
3. **Reuses already loaded images**, improving performance.

---

## **Real-World Use Cases**

🔹 **Virtual Proxy** – Lazy loading of expensive objects (e.g., images, database connections).  
🔹 **Protection Proxy** – Controls access to objects (e.g., user authentication).  
🔹 **Caching Proxy** – Stores previous results to avoid expensive operations.  
🔹 **Remote Proxy** – Represents objects in different locations (e.g., API calls, distributed systems).

---

## **Benefits of Proxy Pattern**

✅ **Lazy Initialization** – Loads objects only when needed.  
✅ **Improves Performance** – Avoids unnecessary memory consumption.  
✅ **Adds Security & Access Control** – Restricts access to certain objects.  
✅ **Supports Caching** – Can store previous results for efficiency.

---

## **When to Use Proxy Pattern?**

✔ When **object creation is expensive** (e.g., loading large files, database connections).  
✔ When you need **controlled access** to an object (e.g., authentication, permissions).  
✔ When **remote objects** need a local representation (e.g., API calls).  
✔ When **caching or logging** is required before accessing the real object.

---

