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

1. `Position`: immutable coordinate, validates `0..7`, supports `equals/hashCode`.
2. `Piece`: immutable `PieceType + PieceColor`, does not store position.
3. `Board`: owns `Map<Position, Piece>`.
4. `Player`: id, name, color.
5. `Move`: from, to, moved piece, captured piece.
6. `Game`: players, board, turn, status, move history.
7. Enums: `PieceColor`, `PieceType`, `GameStatus`.

Key decision:

```text
Board owns placement.
Piece does not know its position.
Game owns flow state.
```

### Phase 2: Basic Move Validation

Done:

1. `MovementStrategy`
2. One strategy per piece.
3. `Board.hasOwnPiece(...)`
4. `Board.hasOpponentPiece(...)`
5. `Board.isPathClear(...)`
6. `MoveValidator`

Validates movement patterns only. Does not check king safety.

### Phase 3: Move Execution and Game Flow

Done:

1. `Game.makeMove(...)`
2. Reject moves unless game is `IN_PROGRESS`.
3. Validate with `MoveValidator`.
4. Move pieces through `Board.movePiece(...)`.
5. Capture opponent piece at destination.
6. Store move in history.
7. Switch turns.
8. Expose read-only move history.

### Phase 4A: Check Detection

Done:

1. `PiecePosition`: read model for `piece + position`.
2. `Board.getPieces()`: exposes read-only board snapshot.
3. `CheckDetector.isInCheck(...)`: detects if a king is under attack.
4. Reuses `MoveValidator` for non-pawn attacks.
5. Handles pawn attacks separately because pawns move forward but attack diagonally.

### Phase 4B: Prevent Self-Check Moves

Done:

1. `Board.undoMove(...)`: restores board after a trial move.
2. `Game.makeMove(...)`: applies move, checks own king safety, rolls back if needed.
3. Illegal self-check moves do not change board, turn, or move history.

Not included yet:

1. Checkmate/stalemate.
2. Castling.
3. En passant.
4. Promotion.
5. Undo/redo.

## Piece Movement Rules

### Pawn

1. One square forward if empty.
2. Two squares forward from starting row if path is empty.
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

## Responsibility Split

### Game

1. Owns game flow.
2. Checks game status.
3. Uses validator.
4. Applies move.
5. Checks own king safety.
6. Rolls back illegal self-check moves.
7. Records history.
8. Switches turn.

### MoveValidator

1. Source must contain a piece.
2. Piece must belong to current player.
3. Source and destination must differ.
4. Pick strategy by `PieceType`.
5. Delegate to `canMove(...)`.

### Board

1. Owns placement.
2. Applies already-validated moves.
3. Exposes current pieces through read-only `PiecePosition` list.
4. Can undo a move using captured move data.
5. Does not enforce turns or piece-specific rules.

### CheckDetector

1. Finds the king for a color.
2. Checks whether any opponent piece attacks that king.
3. Treats pawn attack separately from pawn movement.
4. Does not decide checkmate or stalemate.

## Invariants

1. A position is always inside the board.
2. One square has at most one piece.
3. Board is the source of truth for placement.
4. Piece remains immutable.
5. Game owns turn/status/history.
6. Board move execution assumes validation already happened.
7. Check detection only reads board state.
8. A rejected self-check move must leave board, turn, and history unchanged.

## Roadmap

1. Phase 1: Core domain model.
2. Phase 2: Basic movement validation.
3. Phase 3: Move execution, captures, turn switching.
4. Phase 4A: Check detection.
5. Phase 4B: Prevent illegal self-check moves.
6. Phase 4C: Checkmate and stalemate.
7. Phase 5: Castling, en passant, promotion.
8. Phase 6: Draw rules.
9. Phase 7: Undo/redo with Command pattern.
10. Phase 8: Multiple games and per-game locking.
11. Phase 9: Persistence with Repository pattern.

## Pattern Timing

1. Strategy: movement validation.
2. Command: undo/redo.
3. Observer: notifications.
4. Repository: persistence.
5. Flyweight: optional scale optimization.
6. Singleton: avoid hard Singleton; prefer app-scoped services.
