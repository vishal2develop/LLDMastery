## Text Processing Application

- Pattern Used: Decorator

### Key Components:

- Component Interface - The **component interface** defines the core functionality that both the base object and all decorators will share.
- Concrete Component - actual base object that we want to add new features to. The object that we want to decorate.
- Base Decorator - abstract class (or base class) that wraps a **component** and implements the same interface as the component. This class will act as a foundation for extending functionality.
- Concrete Decorator - are the actual implementations of the decorator class. Each concrete decorator adds specific behavior to the base object while still adhering to the component interface.
- Client - creates the base object and applies various decorators dynamically to add different features.