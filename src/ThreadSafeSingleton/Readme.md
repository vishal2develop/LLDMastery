## Thread-Safe Singleton:

In a multithreaded environment, multiple threads could call `getInstance()` at the same time and potentially create multiple instances.
To prevent this, we can make the Singleton thread-safe using synchronized blocks.

`synchronized` keyword ensures that only one thread can create the instance at a time, making the Singleton thread-safe.
