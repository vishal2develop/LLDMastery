# Balance Sheet Component - Detailed Documentation

## Purpose

The **Balance Sheet** subsystem is responsible for tracking and displaying the financial relationships between users in the Splitwise system. It records:
- How much each user has paid
- How much each user owes
- How much each user should get back
- The net balances between every pair of users

This enables the system to answer questions like "Who owes whom, and how much?" at any point in time.

---

## Key Classes

### 1. `UserExpenseBalanceSheet`

**Role:**  
Represents the balance sheet for a single user, tracking all their financial interactions.

**Fields:**
- `Map<String, Balance> userVsBalance`:  
  Maps other user IDs to a `Balance` object, representing the amount owed to/from each user.
- `double totalYourExpense`:  
  Total amount this user is responsible for (their share in all expenses).
- `double totalPayment`:  
  Total amount this user has actually paid.
- `double totalYouOwe`:  
  Total amount this user owes to others.
- `double totalYouGetBack`:  
  Total amount this user should get back from others.

**Usage:**  
Each user has their own `UserExpenseBalanceSheet` instance, which is updated whenever an expense is created or settled.

---

### 2. `Balance`

**Role:**  
Represents the financial relationship between two users.

**Fields:**
- `double amountOwe`:  
  Amount this user owes to the other user.
- `double amountGetBack`:  
  Amount this user should get back from the other user.

**Usage:**  
For every pair of users who have shared expenses, a `Balance` object is maintained in their respective balance sheets.

---

### 3. `BalanceSheetController`

**Role:**  
Handles all operations related to updating and displaying user balance sheets.

**Key Methods:**

- `updateUserExpenseBalanceSheet(User expensePaidBy, List<Split> splits, double totalExpenseAmount)`
    - Updates the balance sheets of all users involved in an expense.
    - Increments the payer's `totalPayment` and `totalYouGetBack`.
    - For each user in the split:
        - If the user is not the payer, updates both the payer's and the owe-user's `userVsBalance` maps and their respective totals.
        - If the user is the payer, only updates their own `totalYourExpense`.

- `showBalanceSheetOfUser(User user)`
    - Prints a summary of the user's balance sheet, including:
        - Total expenses, payments, amounts owed, and amounts to get back.
        - Detailed per-user balances (who owes whom and how much).

---

## Data Flow Example

Suppose Alice pays $90 for a meal split equally among Alice, Bob, and Carol.

1. **Expense Creation:**
    - Alice is the payer.
    - Each user owes $30.

2. **Balance Sheet Updates:**
    - Alice's `totalPayment` increases by $90.
    - Alice's `totalYourExpense` increases by $30 (her own share).
    - Alice's `totalYouGetBack` increases by $60 (Bob + Carol's shares).
    - Bob and Carol's `totalYouOwe` and `totalYourExpense` increase by $30 each.
    - In Alice's `userVsBalance`, Bob and Carol's `amountGetBack` increase by $30 each.
    - In Bob and Carol's `userVsBalance`, Alice's `amountOwe` increases by $30 each.

---

## Example Output

```
---------------------------------------
Balance sheet of user : U1001
TotalYourExpense: 400.0
TotalGetBack: 600.0
TotalYourOwe: 0.0
TotalPaymnetMade: 900.0
userID:U2001 YouGetBack:300.0 YouOwe:0.0
userID:U3001 YouGetBack:300.0 YouOwe:0.0
---------------------------------------
```

---

## Design Rationale

- **Separation of Concerns:**  
  The balance sheet logic is isolated from user, group, and expense management, making the system modular and maintainable.

- **Efficient Lookups:**  
  Using a map (`userVsBalance`) allows quick access to balances between any two users.

- **Extensibility:**  
  The design can be extended to support settlements, notifications, or more complex balance calculations.

---

## Summary Table

| Class                     | Responsibility                                      |
|---------------------------|-----------------------------------------------------|
| UserExpenseBalanceSheet   | Tracks all balances and totals for a single user    |
| Balance                   | Tracks pairwise balances between two users          |
| BalanceSheetController    | Updates and displays user balance sheets            |

---

