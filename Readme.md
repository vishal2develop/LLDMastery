
## Core Use Case Flow
The system should:
1. Get user’s available payment instruments
2. Identify cart LOB
3. Pick recommendation strategy for that LOB
4. Apply eligibility rules
5. Remove invalid instruments
6. Sort valid instruments by relevanceScore
7. Return recommended payment instruments

| Class       | Responsibility              |
| ----------- | --------------------------- |
| User        | What user HAS               |
| UserContext | What user CAN USE right now |

## Design Choices
- Since recommendation logic varies by line of business, Use the **Strategy pattern** to encapsulate different `recommendation algorithms` and keep the service layer clean.

- **Clean Flow:**
    ```aiignore
    Client wires dependencies
            ↓
    Factory stores LOB → Strategy mapping
            ↓
    Service receives request
            ↓
    Service asks Factory for Strategy
            ↓
    Strategy applies its Rules
            ↓
    Rules decide eligibility
            ↓
    Strategy sorts by relevanceScore
            ↓
    Service returns recommendations
    ```
- Avoid creating rules inside the strategy and instead inject them through the constructor to keep the design flexible and testable.
    - Strategy should not create rules — it should receive them from the client.
