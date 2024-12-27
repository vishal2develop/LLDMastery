## With the Template Method Pattern

Using the Template Method Pattern, we’ll define the common steps in a base class and let subclasses implement the specific steps. This approach eliminates duplication and ensures a consistent process flow.

### Concepts Required

**1. Abstract Class (Template)**

- We create an abstract class BeverageTemplate that defines the template method and common steps.

**2. Concrete Classes**
- Subclasses Tea and Coffee will extend the BeverageTemplate and provide specific implementations for steps specific to tea and coffee.

**3. Client Code**
- The client code now interacts with the `BeverageTemplate`, calling the template method to prepare the beverage.

---

## Benefits of Using the Template Method Pattern
**1. Eliminates Code Duplication:**

- Common steps like boiling water and pouring into a cup are defined once in the base class, reducing duplicate code.

**2. Ensures Consistency:**

- The template method enforces a consistent sequence of steps, ensuring that all beverages follow the same preparation process.

**3. Easier Maintenance:**

- Changes to the common steps need to be made only in the base class, simplifying maintenance and reducing the risk of errors.

**4. Open/Closed Principle:**

- You can extend the system by adding new beverages (like Hot Chocolate) without modifying the existing code. Just create a new subclass that implements the specific steps.

---

## When to Use the Template Method Pattern
- When multiple classes share a similar process but differ in some steps.
- When you want to enforce a consistent algorithm structure while allowing customization of specific steps.
- When common behavior needs to be centralized in a base class to promote code reuse.