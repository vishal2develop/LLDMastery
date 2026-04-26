Here’s the **final clean README with follow-up included** 👇

---

# 🧠 Payment Recommendation System (LLD)

## 🎯 Requirement

Design a system to recommend payment instruments during checkout based on:

* Transaction type (**Line of Business - LOB**)
* Transaction limits and regulations
* User’s available payment instruments
* Runtime context (e.g., UPI enabled)
* Relevance score (ranking)

### Supported LOBs

* **COMMERCE** → regular purchases (e.g., headphones, mobile)
* **INVESTMENT** → financial products (e.g., mutual funds, insurance)
* **CREDIT_CARD_BILL_PAYMENT** → paying credit card bills

### Key Constraint Example

* Credit card **cannot** be used for credit card bill payment

---

## 🔄 Core Flow

Given `User + Cart + UserContext`, the system:

1. Fetches user’s payment instruments
2. Identifies cart LOB
3. Selects recommendation strategy
4. Applies eligibility rules
5. Filters invalid instruments
6. Sorts by relevance score
7. Returns recommended instruments

---

## 🧩 Key Concepts

| Class       | Responsibility                    |
| ----------- | --------------------------------- |
| User        | What user **has**                 |
| UserContext | What user **can use right now**   |
| Cart        | Aggregates items and derives LOB  |
| Strategy    | LOB-specific recommendation logic |
| Rule        | Individual eligibility checks     |

---

## 🏗️ Design

### Strategy Pattern

* Encapsulates LOB-specific recommendation logic
* Keeps service layer clean and extensible

### Rule (Specification) Pattern

* Each rule validates one condition
* Strategies compose rules for filtering

---

## ⚙️ Execution Flow

```text
Client → Factory → Service → Strategy → Rules → Result
```

---

## 🔧 Wiring Principle

* Client wires:

    * Strategies
    * Rules
    * Factory mappings

* Strategy:

    * Receives rules via constructor
    * Does not create dependencies

---

## 🔁 Follow-up (Advanced Requirement)

### Problem

Cart may contain items from **multiple LOBs** (e.g., commerce + investment).

### Approach

* Split cart into logical units:

```text
Cart → List<OrderSplit>
OrderSplit → single LOB
```

* Apply recommendation logic **per split**
* Return recommendations per split or aggregated view

---

## 🧠 Key Design Decisions

* **Separation of concerns**

    * Service → orchestration
    * Strategy → flow
    * Rule → validation

* **Extensibility**

    * Add new LOB → new strategy
    * Add new rule → plug into strategy

* **Config-driven**

    * Transaction limits and constraints are externalized

---

## 🧠 One-Line Summary

> **Strategy selects logic, Rules filter eligibility, Service orchestrates.**
