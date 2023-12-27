package com.da0hn.craft.system.core.domain;

import java.util.Optional;

public record Position(int x, int y) {

  public Position {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Position cannot be negative");
    }
  }

  public static Position of(final int x, final int y) {
    return new Position(x, y);
  }

  public Optional<Position> bottom() {
    if (this.y - 1 < 0) {
      return Optional.empty();
    }
    return Optional.of(new Position(this.x, this.y - 1));
  }

  public Optional<Position> top(final int limit) {
    if (this.y + 1 >= limit) {
      return Optional.empty();
    }
    return Optional.of(new Position(this.x, this.y + 1));
  }

  public Optional<Position> left() {
    if (this.x - 1 < 0) {
      return Optional.empty();
    }
    return Optional.of(new Position(this.x - 1, this.y));
  }

  public Optional<Position> right(final int limit) {
    if (this.x + 1 >= limit) {
      return Optional.empty();
    }
    return Optional.of(new Position(this.x + 1, this.y));
  }

  public Optional<Position> bottomLeft() {
    return this.bottom().flatMap(Position::left);
  }

  public Optional<Position> bottomRight(final int limit) {
    return this.bottom().flatMap(bottom -> bottom.right(limit));
  }

  public Optional<Position> topLeft(final int limit) {
    return this.top(limit).flatMap(Position::left);
  }

  public Optional<Position> topRight(final int limit) {
    return this.top(limit).flatMap(top -> top.right(limit));
  }

}
