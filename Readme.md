# 🧠 Payment Recommendation System (LLD)

## 🎯 Requirement

Recommend payment instruments based on:

* Line of Business (LOB)
* Transaction limits / regulations
* User’s available instruments
* Runtime context (device capability)
* Relevance score (ranking)

### Supported LOBs

* **COMMERCE**
* **INVESTMENT**
* **CREDIT_CARD_BILL_PAYMENT**

### Key Constraint

* Credit card ❌ for credit card bill payment

---

# 🚀 Phase 1: MVP (Single LOB Cart)

## 🔄 End-to-End Flow

```text
User + Cart + UserContext
        ↓
PaymentRecommendationService
        ↓
Factory selects Strategy (based on LOB)
        ↓
Strategy applies Rules
        ↓
Rules filter instruments
        ↓
Strategy sorts by relevanceScore
        ↓
Return recommended instruments
```

---

## 🧩 Key Concepts

| Component              | Responsibility                |
| ---------------------- | ----------------------------- |
| User                   | What user **has**             |
| UserContext            | What user **can use now**     |
| Cart                   | Aggregates items, derives LOB |
| RecommendationStrategy | LOB-specific logic            |
| PaymentRule            | Eligibility checks            |
| StrategyFactory        | Selects strategy              |

---

## 🏗️ Design Patterns

### 🧠 Strategy Pattern

* Encapsulates LOB-specific recommendation logic

### 🧠 Rule (Specification) Pattern

* Each rule checks one condition

### 🧠 Factory Pattern

* Maps `LOB → Strategy`
* Decouples service from strategy creation

---

## ⚙️ Execution Flow

```text
Client → Factory → Service → Strategy → Rules → Result
```

---

## 🔧 Wiring Principle

* Client wires:

    * Rules
    * Strategies
    * Factory mappings

* Strategy:

    * Receives rules via constructor
    * Does not create dependencies

---

## 🧠 Key Design Decisions

* Separation of concerns

    * Service → orchestration
    * Strategy → flow
    * Rule → validation

* Extensibility

    * Add new LOB → new strategy
    * Add new rule → plug into strategy

* Config-driven

    * Transaction limits externalized

---

# 🔁 Phase 2: Mixed LOB Cart (Follow-up)

## 🎯 Problem

Cart contains multiple LOBs:

```text
Cart
 ├── COMMERCE
 └── INVESTMENT
```

---

## 🧠 Solution

Split cart:

```text
Cart → List<CartSplit>
CartSplit → single LOB
```

---

## 🔄 Updated End-to-End Flow

```text
User + Cart + UserContext
        ↓
CartSplitter
        ↓
List<CartSplit>
        ↓
For each CartSplit:
    Factory → Strategy → Rules → Recommendations
        ↓
Aggregate results
```

---

## 🧩 New Components

| Component           | Responsibility                        |
| ------------------- | ------------------------------------- |
| CartSplit           | LOB-specific subset                   |
| CartSplitter        | Splits cart                           |
| PayableContext      | Common abstraction (Cart / CartSplit) |
| SplitRecommendation | Response per split                    |

---

## 🧠 Design Enhancement

```text
PayableContext
 ├── Cart
 └── CartSplit
```

Enables reuse of:

* Strategy
* Rules

---

## 🧠 Key Insight

> Move from **cart-level → split-level processing**

---

# 🧠 One-Line Summary

> **Strategy selects logic, Rules filter eligibility, Factory decouples creation, Service orchestrates.**

---