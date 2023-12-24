package com.da0hn.craft.system.core.domain;

public class Slot {

  private final Position position;

  public Slot(final Position position) { this.position = position; }

  public static Slot newSlot(final Position position) {
    return new Slot(position);
  }

  public Position position() {
    return this.position;
  }

}
