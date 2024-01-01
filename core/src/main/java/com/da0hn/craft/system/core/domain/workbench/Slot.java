package com.da0hn.craft.system.core.domain.workbench;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;

public class Slot implements Comparable<Slot> {

  private final String label;

  private final Position position;

  private final Set<NeighbourSlot> neighbours;

  public Slot(final String label, final Position position) {
    this.label = label;
    this.position = position;
    this.neighbours = new LinkedHashSet<>(0);
  }

  public static Slot newSlot(final String workbenchName, final Position position) {
    final var label = "%s(%s, %s)".formatted(workbenchName, position.x(), position.y());
    return new Slot(label, position);
  }

  public String label() {
    return this.label;
  }

  public Position position() {
    return this.position;
  }

  public Set<NeighbourSlot> neighbours() {
    return Collections.unmodifiableSet(this.neighbours);
  }

  public void addNeighbour(final NeighbourSlot newNeighbour) {
    if (this.hasNeighbourIn(neighbour -> newNeighbour.direction().equals(neighbour.direction()))) {
      throw new IllegalArgumentException("Neighbour already exists in this direction");
    }
    this.neighbours.add(newNeighbour);
  }

  @Override
  public int compareTo(final Slot other) {
    if (other.position.x() == this.position.x()) {
      return this.position.y() - other.position.y();
    }
    return this.position.x() - other.position.x();
  }

  public boolean hasSamePosition(final Position otherPosition) {
    return this.position.equals(otherPosition);
  }

  private boolean hasNeighbourIn(final Predicate<? super NeighbourSlot> criteria) {
    return this.neighbours.stream().anyMatch(criteria);
  }

}
