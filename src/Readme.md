# ATM Machine - LLD

## General Context

Design an ATM machine that allows users to:
- Insert card
- Authenticate using PIN
- Withdraw cash
- Dispense notes
- Check balance
- Eject card

The system is modeled as a state-driven machine where ATM behavior changes depending on its current state.

---

# Phase 1 - Core ATM Workflow

## Supported Features

- Insert card
- Authenticate PIN
- Withdraw cash
- Dispense notes
- Check balance
- Eject card

---

## Out of Scope

- Deposit
- Multi-bank integration
- Real payment network
- ATM cash refill management
- Cardless withdrawal
- Mini statement
- Notifications

---

## Core Entities

| Entity | Responsibility |
|---|---|
| `ATM` | Main machine that delegates behavior to current state |
| `ATMState` | Defines ATM operations based on current state |
| `Card` | User debit/credit card |
| `BankAccount` | Stores account balance |
| `CashDispenser` | Dispenses notes using denomination chain |

---

## Core Flow

```text
Insert Card
      ↓
Enter PIN
      ↓
Authenticate User
      ↓
Withdraw Cash
      ↓
Validate Balance
      ↓
Dispense Notes
      ↓
Eject Card