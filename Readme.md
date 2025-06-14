# Splitwise - Low Level Design Architecture Document

## 1. System Overview
Splitwise is a low-level design implementation of an expense-sharing application that allows users to split expenses among groups of people. The system handles different types of expense splits and maintains balance sheets for all users.

## 2. Core Components

### 2.1 User Management
- **User Class**
    - Represents a user in the system
    - Contains basic user information (ID, name)
    - Maintains a balance sheet for tracking expenses

- **UserController**
    - Manages user operations
    - Handles user creation and retrieval
    - Maintains a list of all users in the system

### 2.2 Group Management
- **Group Class**
    - Represents a group of users
    - Maintains a list of group members
    - Handles expense creation within the group

- **GroupController**
    - Manages group operations
    - Handles group creation and member management
    - Links users to groups

### 2.3 Expense Management
- **Expense Class**
    - Represents an individual expense
    - Contains expense details (ID, description, amount)
    - Maintains information about who paid and how it's split

- **ExpenseController**
    - Manages expense operations
    - Handles expense creation and tracking

- **ExpenseSplitType**
    - Enum defining different ways to split expenses:
        - EQUAL: Equal distribution among all members
        - UNEQUAL: Custom distribution based on specified amounts

- **Split Class**
    - Represents how an expense is split among users
    - Contains user and amount information

### 2.4 Balance Management
- **BalanceSheetController**
    - Manages user balance sheets
    - Tracks who owes whom and how much
    - Provides balance information for users

- **UserExpenseBalanceSheet**
    - Maintains individual user's balance information
    - Tracks total paid, total owed, and individual balances with other users

- **Balance**
    - Represents a balance between two users
    - Tracks amount owed between users

## 3. Key Features

### 3.1 Expense Splitting
- Supports multiple split types (Equal and Unequal)
- Handles complex expense scenarios
- Maintains accurate split calculations

### 3.2 Group Management
- Create and manage groups
- Add/remove members from groups
- Track group expenses

### 3.3 Balance Tracking
- Real-time balance updates
- Individual user balance sheets
- Detailed tracking of who owes whom

## 4. Data Flow

1. **User Creation**
    - Users are created and added to the system
    - Each user gets a unique ID

2. **Group Formation**
    - Users can create groups
    - Other users can be added to groups

3. **Expense Creation**
    - Users can create expenses within groups
    - Specify split type and amounts
    - System automatically updates balance sheets

4. **Balance Management**
    - System maintains running balances
    - Users can view their balance sheets
    - Tracks both group and individual balances

## 5. Design Patterns Used

1. **Factory Pattern**
    - Used in SplitFactory for creating different types of expense splits

2. **Controller Pattern**
    - Separate controllers for Users, Groups, and Expenses
    - Provides clean separation of concerns

3. **Single Responsibility Principle**
    - Each class has a specific responsibility
    - Clear separation between user, group, and expense management

## 6. Example Usage

```java
// Create users
User user1 = new User("U1001", "User1");
User user2 = new User("U2001", "User2");

// Create a group
Group group = new Group("G1001", "Outing with Friends", user1);
group.addMember(user2);

// Create an expense
List<Split> splits = new ArrayList<>();
splits.add(new Split(user1, 300));
splits.add(new Split(user2, 300));
group.createExpense("Exp1001", "Breakfast", 600, splits, ExpenseSplitType.EQUAL, user1);
```


