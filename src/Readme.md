## Problem

Imagine we have a logging system where logs can have different levels (INFO, DEBUG, ERROR). We want to pass each log message through a chain of log handlers, where each handler decides whether to process the log or pass it to the next one.

## Explanation
- Client sends logs to the first handler (InfoLogger).
- If a handler can process the log, it prints it. Otherwise, it forwards it to the next handler.
- The request moves through the chain until it gets handled.

## Benefits of Using Chain of Responsibility
- ✅ Open/Closed Principle – Easily add new log levels without modifying existing handlers.
- ✅ Decouples Logging Logic – Each logger is responsible only for its level.
- ✅ Flexible Processing – Order of logging can be changed dynamically.
- ✅ Extensible – New log handlers (e.g., FileLogger, DatabaseLogger) can be added without modifying existing code.

## When to Use This Pattern?
- ✔ When multiple handlers might process a request.
- ✔ When request handling should be decoupled from request sending.
- ✔ When avoiding if-else conditions is necessary.
- ✔ When handlers need to be easily extended or modified.


