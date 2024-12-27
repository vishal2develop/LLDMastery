# Without Template Method Pattern

## Problem Scenario: Preparing Beverages

Imagine we have a system that prepares different beverages like Tea and Coffee. Both beverages follow a similar process but have some steps unique to each drink.

Process Steps:
- Boil water.
- Brew the beverage (**specific for each drink**).
- Pour into a cup.
- Add condiments (**specific for each drink**).

---

## Without the Template Method Pattern (Problem)
In this version, each beverage class implements the entire preparation process independently. This leads to duplicate code and makes the system harder to maintain or extend.

---

## Problems with This Approach
1. **Code Duplication:**

- Both Tea and Coffee classes have identical code for boiling water and pouring into a cup. This duplication increases maintenance efforts.
2. **Lack of Consistency:**

- Any change in the common steps (like boiling water) needs to be made in multiple places, increasing the risk of errors.

3. **Hard to Extend:**
- Adding a new beverage requires copying and pasting similar code, leading to a lot of repeated logic.

