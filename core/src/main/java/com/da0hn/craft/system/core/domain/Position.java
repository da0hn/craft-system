package com.da0hn.craft.system.core.domain;

public class Position {

  private final int x;

  private final int y;

  private Position(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  public static Position of(final int x, final int y) {
    return new Position(x, y);
  }

  public int x() {
    return this.x;
  }

  public int y() {
    return this.y;
  }

}
