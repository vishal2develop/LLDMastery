# Tic Tac Toe - LLD

## Requirements

* Two-player game
* Configurable board size (NxN)
* Move validation
* Winner detection
* Draw detection
* Multiple active games
* Extensible winning rules

---

# Core Components

| Component         | Responsibility              |
| ----------------- | --------------------------- |
| `GameController`  | Manages active games        |
| `Game`            | Orchestrates gameplay       |
| `Board`           | Manages board state         |
| `Cell`            | Represents a board position |
| `Player`          | Player details              |
| `WinningStrategy` | Winner detection            |

---

# Phase 1 - MVP

## Scope

* Two players
* Configurable board size
* Move validation
* Winner detection
* Draw detection
* Player switching

## Flow

```text
Player Move
    ↓
Game
    ↓
Board.placeMove()
    ↓
Winner?
    ↓
Draw?
    ↓
Switch Player
```

## Design Decisions

### Game as Orchestrator

`Game` controls the gameplay flow and delegates board operations.

```text
Game
  ↓
Board
```

### Board Owns Board State

`Board` is responsible for:

* Cells
* Move validation
* Board display
* Draw detection

### Configurable Board Size

Board size flows from:

```text
Client
  ↓
Game
  ↓
Board
```

This allows future support for any `NxN` board.

### Cell Stores Player

A cell stores only the occupying player.

```text
Player
  ↓
Symbol
```

avoiding duplicated state.

---

# Phase 2 - Strategy Pattern

## Scope

* Extract winner detection from `Board`
* Support pluggable winning rules

## Design Decisions

### WinningStrategy

Winner detection changes independently from board management.

```text
Game
  ↓
WinningStrategy
  ↓
DefaultWinningStrategy
```

Benefits:

* Separation of concerns
* Easily extensible for future game variants
* Board focuses only on board state

### DefaultWinningStrategy

Current implementation checks:

* Rows
* Columns
* Diagonals

---

# Phase 3 - GameController

## Scope

* Support multiple active games
* Centralize game management

## Design Decisions

### GameController as Facade

Clients interact only with `GameController`.

```text
Client
  ↓
GameController
  ↓
Game
```

This hides game creation and move orchestration from the client.

### Singleton

`GameController` is implemented as a Singleton since it acts as the single in-memory registry for all active games.

```text
gameId
   ↓
Game
```

> Note: Singleton is appropriate for this in-memory LLD. In a distributed system, game state would typically be stored in a shared database or cache.

### Central Game Registry

Active games are maintained using:

```text
Map<gameId, Game>
```

Responsibilities:

* Create game
* Make move
* Retrieve game
* Retrieve game status
* Print board

---

# Architecture

```text
Client
   │
   ▼
GameController (Singleton)
   │
   ▼
Game
   ├── Board
   └── WinningStrategy
           │
           ▼
DefaultWinningStrategy

Board
   │
   ▼
Cell
```

---

# Phase 4 - Concurrency

## Goal

Make `GameController` thread-safe for concurrent access.

## Design Decisions

### Bill Pugh Singleton

Replaced lazy initialization with Bill Pugh Singleton.

Benefits:

* Thread-safe
* Lazy initialization
* No explicit synchronization

### Concurrent Game Registry

Replace:

```text
HashMap<String, Game>
```

with:

```text
ConcurrentHashMap<String, Game>
```

to safely support concurrent game creation and retrieval.

### Fine-Grained Locking

Synchronize on the individual `Game` instead of the `GameController`.

```text
Game A → Lock A
Game B → Lock B
```

This allows different games to progress concurrently without blocking each other.

---

# Architecture

```text
Client
   │
   ▼
GameController (Singleton)
   │
   ▼
ConcurrentHashMap<gameId, Game>
   │
   ▼
Game
   ├── Board
   └── WinningStrategy
           │
           ▼
DefaultWinningStrategy

Board
   │
   ▼
Cell
```

---

# Next Phases

## Phase 5

* AI Player (Minimax)

## Phase 6

* Persistence (Database / Redis)

---

## One-Line Summary

> `GameController` manages multiple games, `Game` orchestrates gameplay, `Board` manages state, and `WinningStrategy` encapsulates the winning algorithm.

