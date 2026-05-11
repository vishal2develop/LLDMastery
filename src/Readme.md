# Traffic Management System

Design a simple traffic system that:

```aiignore
Receives vehicle location/speed updates
        ↓
Groups them by road segment
        ↓
Detects congestion using average speed
        ↓
Notifies subscribed users
```
## MVP Assumptions
- In-memory storage
- No Kafka / DB / real WebSocket
- Road segments are already known
  - Each vehicle update already contains roadSegmentId
- Congestion = average speed below threshold (This is one Strategy)
- Users subscribe to road segments for alerts

**congestion logic - Average speed on segment < threshold**