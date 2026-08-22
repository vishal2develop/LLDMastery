# Chess Game LLD

## Goal

Build the design phase by phase for senior/staff LLD interview prep.

Principle: keep the domain clean first; introduce patterns only when they solve a real design pressure.

## Requirements

Eventually support:

1. Standard piece movement.
2. Check, checkmate, stalemate.
3. Castling, en passant, promotion.
4. Draw rules.
5. Undo/redo.
6. Multiple games.
7. Persistence.

## Current Status

### Phase 1: Core Domain Model

Done:

1. `Position`: immutable board coordinate, validates `0..7`, supports `equals/hashCode`.
2. `Piece`: immutable `PieceType + PieceColor`, does not store position.
3. `Board`: owns `Map<Position, Piece>`.
4. `Player`: id, name, color.
5. `Move`: from, to, moved piece, captured piece.
6. `Game`: players, board, current turn, status, move history.
7. Enums: `PieceColor`, `PieceType`, `GameStatus`.

Key decision:

```text
Board owns placement.
Piece does not know its position.
Game owns flow state.
```

### Phase 2: Basic Move Validation

In progress / mostly complete.

Done:

1. `MovementStrategy`
2. One strategy per piece.
3. `Board.hasOwnPiece(...)`
4. `Board.hasOpponentPiece(...)`
5. `Board.isPathClear(...)`
6. `MoveValidator`

Phase 2 validates movement patterns only.

Not included yet:

1. Check/checkmate.
2. Castling.
3. En passant.
4. Promotion.
5. Move execution.
6. Turn switching.

## Piece Movement Rules

### Pawn

1. Moves one square forward if destination is empty.
2. Moves two squares forward from starting row if path is empty.
3. Captures one square diagonally forward.
4. White moves toward smaller row numbers.
5. Black moves toward larger row numbers.

Out of scope: en passant, promotion.

### Rook

1. Moves horizontally or vertically.
2. Cannot jump over pieces.
3. Destination cannot contain own piece.

### Knight

1. Moves in L-shape: `2 + 1`.
2. Can jump over pieces.
3. Destination cannot contain own piece.

### Bishop

1. Moves diagonally.
2. `abs(rowDiff) == abs(colDiff)`.
3. Cannot jump over pieces.
4. Destination cannot contain own piece.

### Queen

1. Combines rook + bishop movement.
2. Moves straight or diagonal.
3. Cannot jump over pieces.
4. Destination cannot contain own piece.

### King

1. Moves one square in any direction.
2. Destination cannot contain own piece.

Out of scope: castling, moving into check.

## MoveValidator

Responsibilities:

1. Source must contain a piece.
2. Piece must belong to current player.
3. Source and destination must differ.
4. Pick strategy by `PieceType`.
5. Delegate to `canMove(...)`.

It does not move pieces.

## Invariants

1. A position is always inside the board.
2. One square has at most one piece.
3. Board is the source of truth for placement.
4. Piece remains immutable.
5. Game owns turn/status/history.

## Roadmap

1. Phase 1: Core domain model.
2. Phase 2: Basic movement validation.
3. Phase 3: Move execution, captures, turn switching.
4. Phase 4: Check, checkmate, stalemate.
5. Phase 5: Castling, en passant, promotion.
6. Phase 6: Draw rules.
7. Phase 7: Undo/redo with Command pattern.
8. Phase 8: Multiple games and per-game locking.
9. Phase 9: Persistence with Repository pattern.

## Pattern Timing

1. Strategy: movement validation.
2. Command: undo/redo.
3. Observer: notifications.
4. Repository: persistence.
5. Flyweight: optional scale optimization.
6. Singleton: avoid hard Singleton; prefer app-scoped services.
