package com.da0hn.craft.system.core.domain;

public class Slot implements Comparable<Slot> {

  private final String label;

  private final Position position;

  public Slot(final String label, final Position position) {
    this.label = label;
    this.position = position;
  }

  public static Slot newSlot(final Position position) {
    final var label = "%s : %s".formatted(position.x(), position.y());
    return new Slot(label, position);
  }

  public String label() {
    return this.label;
  }

  public Position position() {
    return this.position;
  }

  @Override
  public int compareTo(final Slot other) {
    if (other.position.y() == this.position.y()) {
      return this.position.x() - other.position.x();
    }
    return this.position.y() - other.position.y();
  }

}
