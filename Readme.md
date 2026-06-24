# Tic Tac Toe - LLD

## Requirements

* Two player game
* Configurable board size (NxN)
* Make move
* Validate move
* Detect winner
* Detect draw
* Extensible winning rules

---

# Core Components

| Component         | Responsibility              |
| ----------------- | --------------------------- |
| `Game`            | Orchestrates gameplay       |
| `Board`           | Manages cells and moves     |
| `Cell`            | Represents a board position |
| `Player`          | Player details and symbol   |
| `WinningStrategy` | Win detection algorithm     |

---

# Phase 1 - MVP

## Scope

* Two players
* NxN board support
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
Winner ?
    ↓
Draw ?
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

### Board Owns State

`Board` manages:

* Cells
* Move validation
* Board display
* Draw detection

### Configurable Board Size

Board size is passed from:

```text
Client
  ↓
Game
  ↓
Board
```

allowing future support for:

```text
3x3
4x4
5x5
NxN
```

### Cell Stores Player

A cell stores only the occupying player.

```text
Player
  ↓
Symbol
```

This avoids duplicating symbol and occupancy state.

---

# Phase 2 - Strategy Pattern

## Scope

* Extract winner detection from Board
* Support future winning rule variations

## Design Decisions

### WinningStrategy

Winner detection varies independently from board management.

```text
Game
  ↓
WinningStrategy
  ↓
DefaultWinningStrategy
```

Benefits:

* Board focuses on board state
* Winning logic is pluggable
* Easier to support future variants

### DefaultWinningStrategy

Current implementation checks:

* Rows
* Columns
* Diagonals

for the active player.

---

# Architecture

```text
Client
  ↓
Game
  ↓
Board

Game
  ↓
WinningStrategy
  ↓
DefaultWinningStrategy

Board
  ↓
Cell
```

---

# Future Enhancements

## Phase 3 - Observer Pattern

Events:

* Move Made
* Game Won
* Game Draw

Observers:

* Logger
* Scoreboard
* Notification Service

## Phase 4 - Game Controller

Manage multiple games.

```text
gameId → Game
```

Potential Singleton for a single in-memory game registry.

## Phase 5 - Advanced Winning Rules

Support:

* Connect-K
* Custom win length
* Alternate game modes

---

## One-Line Summary

> Game orchestrates gameplay, Board manages state, and WinningStrategy encapsulates winner detection logic.
