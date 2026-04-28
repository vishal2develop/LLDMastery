
# 🧠 Flash Sale System – MVP

A simple flash sale system that handles high concurrency while ensuring no overselling.

---

## 🎯 Assumptions

* Single flash sale event
* Multiple products supported
* In-memory storage
* No payment flow
* One user can buy only one unit per product
* Main goal: **no overselling under concurrency**

---

## 🧩 Core Components

| Class             | Responsibility              |
| ----------------- | --------------------------- |
| `User`            | Buyer placing order         |
| `Product`         | Item being sold             |
| `Inventory`       | Manages stock for a product |
| `Order`           | Represents purchase outcome |
| `OrderService`    | Handles order placement     |
| `OrderRepository` | Stores successful orders    |

---

## ⚙️ End-to-End Flow

```text
User clicks Buy
        ↓
OrderService.placeOrder()
        ↓
Check duplicate purchase (same user + product)
        ↓
Fetch Inventory for product
        ↓
Inventory.reserve()  (atomic)
        ↓
If success:
    create order
    save order
Else:
    return failed
```

---

## 🧠 Key Design Decisions

* **Encapsulation**

    * Inventory owns stock and controls stock changes

* **Concurrency Control**

    * Used `synchronized` inside `Inventory.reserve()`
    * Ensures atomic **check + decrement**
    * Lock is per product → better concurrency

* **No Overselling**

    * Stock is reduced only inside synchronized block

* **Duplicate Order Prevention**

    * Used a thread-safe set (`ConcurrentHashMap`)
    * Key = `userId:productId`
    * Ensures one successful purchase per user

---

## ⚠️ Critical Rule

> **Check stock + decrement must be atomic**

---

## 🚀 Concurrency Strategy

```text
Each product has its own Inventory object
        ↓
Each Inventory has its own lock
        ↓
No global lock → better performance
```

---

## 🧠 One-Line Summary

> **Inventory ensures correctness, OrderService orchestrates flow, ConcurrentHashMap prevents duplicate purchases.**

