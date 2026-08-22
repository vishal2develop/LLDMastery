# Chess Game LLD

## Goal

Design a Chess Game phase by phase for senior/staff LLD interview prep.

Do not build everything at once. Keep each phase small, reviewable, and grounded in clear ownership and invariants.

## Requirements

Eventually support:

1. Standard chess movement rules.
2. Check, checkmate, and stalemate.
3. Castling, en passant, and pawn promotion.
4. Draw rules.
5. Undo/redo.
6. Multiple game sessions.
7. Persistence.

## Current Phase

### Phase 1: Core Domain Model

Status: mostly complete.

Implemented:

1. `Position`
   - Immutable row/column coordinate.
   - Validates range `0..7`.
   - Implements value-based `equals()` and `hashCode()`.

2. `Piece`
   - Immutable.
   - Stores only `PieceType` and `PieceColor`.
   - Does not know its position.

3. `Board`
   - Owns piece placement.
   - Uses `Map<Position, Piece>`.
   - Supports place/get/remove/check-empty operations.

4. `Player`
   - Stores id, name, and color.

5. `Move`
   - Captures move data: source, destination, moved piece, captured piece.

6. `Game`
   - Owns board, players, turn, status, and move history.
   - White starts first.
   - Status starts as `NOT_STARTED`.

7. Enums
   - `PieceColor`
   - `PieceType`
   - `GameStatus`

## Key Decisions

### Board Owns Placement

Use:

```text
Board: Position -> Piece
```

Do not store position inside `Piece`.

Why:

1. Single source of truth.
2. Avoids board/piece position mismatch.
3. Makes lookup, snapshots, undo/redo, and hashing easier later.

### Piece Is Immutable

`Piece` contains:

```text
PieceType
PieceColor
```

Game-specific state like turn, move history, castling eligibility, and en passant should live in `Game` / history, not inside `Piece`.

### Game Owns Flow

`Game` coordinates:

1. Players.
2. Board.
3. Current turn.
4. Game status.
5. Move history.

The board should not know turns. Pieces should not know the game.

## Phase 1 Invariants

1. Board positions are always within `0..7`.
2. One position has at most one piece.
3. A piece has one type and one color.
4. A piece does not store position.
5. Board is the source of truth for placement.
6. Game has one white player and one black player.
7. White starts.
8. Only `Game` owns turn state.

## Phase 1 Cleanup

Before Phase 2:

1. Rename `Board.createStandardBoard()` to `createEmptyBoard()` unless it actually places all pieces.
2. Add `getFrom()` and `getTo()` to `Move`.
3. Make `Game.moveHistory` final.
4. Add getters for `gameId`, players, and status if needed.

## Phase 2: Basic Move Validation

Goal: validate normal movement patterns only.

Out of scope:

1. Check/checkmate.
2. Castling.
3. En passant.
4. Promotion.
5. Full legal move validation.

Introduce Strategy here:

```java
public interface MovementStrategy {
    boolean canMove(Board board, Position from, Position to, PieceColor color);
}
```

Strategies:

1. `PawnMovementStrategy`
2. `RookMovementStrategy`
3. `KnightMovementStrategy`
4. `BishopMovementStrategy`
5. `QueenMovementStrategy`
6. `KingMovementStrategy`

Important distinction:

```text
movement-valid: piece follows its movement pattern
legal-move: move is valid in full game state
```

Phase 2 focuses on movement-valid.

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

Do not introduce patterns early.

Use patterns when they solve real pressure:

1. Strategy: piece movement validation.
2. Command: undo/redo.
3. Observer: player/UI notifications.
4. Repository: persistence.
5. Flyweight: optional scale optimization for shared piece definitions.
6. Singleton: avoid hard Singleton; prefer app-scoped services.
