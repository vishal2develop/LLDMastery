# Vending Machine - LLD

## Context

Design a vending machine that supports:

* View products
* Insert money
* Select product
* Dispense product
* Return change

---

# Core Entities

| Entity | Responsibility |
|---|---|
| `Product` | Product metadata |
| `ProductSlot` | Product inventory and quantity |
| `Inventory` | Stores and searches product slots |
| `Payment` | Payment information |
| `VendingMachine` | Purchase orchestration |

---

# Phase 1 - Core Purchase Flow

## Flow

```text
Insert Money
    ↓
Select Product
    ↓
Validate Funds
    ↓
Dispense Product
    ↓
Return Change
```

## Design Decisions

### Product vs ProductSlot

`Product` stores catalog information:

* id
* name
* price

`ProductSlot` stores inventory information:

* product
* quantity

This separates product metadata from stock management.

### Inventory Responsibility

`Inventory` is responsible for:

* storing slots
* product lookup

### VendingMachine Responsibility

`VendingMachine` orchestrates:

* validating funds
* product selection
* dispensing products
* returning change

---

# Phase 2 - State Pattern

## Goal

Ensure operations happen in the correct order.

## States

```text
IdleState
MoneyInsertedState
DispensingState
```

## State Flow

```text
IdleState
    ↓ insertMoney
MoneyInsertedState
    ↓ selectProduct
DispensingState
    ↓ dispenseProductAndReturnChange
IdleState
```

## Design Decision

State-specific behavior is moved from `VendingMachine` into state objects.

---

# Phase 3 - Strategy Pattern

## Goal

Support multiple payment methods.

## Strategies

```text
PaymentStrategy
    ├── CashPaymentStrategy
    ├── CreditCardPaymentStrategy
    └── UpiPaymentStrategy
```

## Design Decision

Payment processing behavior varies by payment type and is encapsulated behind a common strategy interface.

---

# Phase 4 - Factory Pattern

## Goal

Centralize payment strategy creation.

## Flow

```text
PaymentMode
    ↓
PaymentStrategyFactory
    ↓
PaymentStrategy
```

## Design Decision

Client asks the factory for a payment strategy instead of directly creating concrete strategy objects.

---

# Phase 5 - Concurrency

## Goal

Prevent overselling when multiple users try to buy the last item at the same time.

## Critical Section

```text
check quantity
    ↓
decrement quantity
```

## Design Decision

`ProductSlot` owns product quantity, so quantity mutation is synchronized inside `ProductSlot`.

```text
ProductSlot.quantity
        ↓
synchronized decrementQuantity()
```

This ensures stock check and decrement happen atomically.

## Better Alternatives

* `ReentrantLock` → timeout / fairness / tryLock
* `@Transactional` → DB-backed inventory
* `@Version` → optimistic locking
* Distributed lock → multi-server setup

---

# Architecture

```mermaid
flowchart TD

    Client --> VendingMachine

    VendingMachine --> VendingMachineState
    VendingMachine --> Inventory
    VendingMachine --> Payment

    Inventory --> ProductSlot
    ProductSlot --> Product

    Payment --> PaymentStrategy
    PaymentStrategyFactory --> PaymentStrategy
```

---

## One-Line Summary

> VendingMachine orchestrates purchases, State Pattern controls machine behavior, Strategy Pattern handles payment processing, Factory creates payment strategies, and ProductSlot synchronization prevents overselling.