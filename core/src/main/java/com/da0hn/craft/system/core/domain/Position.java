package com.da0hn.craft.system.core.domain;

public record Position(int x, int y) {

  public Position {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Position cannot be negative");
    }
  }

  public static Position of(final int x, final int y) {
    return new Position(x, y);
  }

}
