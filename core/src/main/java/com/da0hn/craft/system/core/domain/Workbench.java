package com.da0hn.craft.system.core.domain;

import com.da0hn.craft.system.core.domain.command.CreateWorkbenchCommand;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Workbench {

  private final WorkbenchId workbenchId;

  private final String name;

  private final int width;

  private final int height;

  private final Set<Slot> slots;

  private final Set<Recipe> recipes;

  private Workbench(final String name, final WorkbenchId workbenchId, final int width, final int height) {
    if (width <= 1 && height <= 1) {
      throw new IllegalArgumentException("Workbench must be at least 2 by 2");
    }
    this.name = name;
    this.workbenchId = workbenchId;
    this.width = width;
    this.height = height;
    this.slots = new LinkedHashSet<>(0);
    this.recipes = new LinkedHashSet<>(0);
  }

  public static Workbench newWorkbench(final CreateWorkbenchCommand command) {
    final Workbench workbench = new Workbench(command.workbenchName(), WorkbenchId.newInstance(), command.width(), command.height());

    final var slots = IntStream.range(0, command.width())
      .mapToObj(y -> IntStream.range(0, command.height()).mapToObj(x -> Slot.newSlot(command.workbenchName(), Position.of(x, y))))
      .flatMap(Function.identity())
      .collect(Collectors.toCollection(LinkedHashSet::new));

    workbench.slots.addAll(slots);
    workbench.loadNeighbourPositions();
    return workbench;
  }

  public WorkbenchId workbenchId() {
    return this.workbenchId;
  }

  public String name() {
    return this.name;
  }

  public Set<Recipe> recipes() {
    return Collections.unmodifiableSet(this.recipes);
  }

  public Set<Slot> slots() {
    return Collections.unmodifiableSet(this.slots);
  }

  private void loadNeighbourPositions() {
    this.slots.forEach(slot -> {
      final var position = slot.position();
      this.findSlotAt(position.left())
        .map(NeighbourSlot::newNeighbourAtLeft)
        .ifPresent(slot::addNeighbour);
      this.findSlotAt(position.right(this.width))
        .map(NeighbourSlot::newNeighbourAtRight)
        .ifPresent(slot::addNeighbour);
      this.findSlotAt(position.top(this.height))
        .map(NeighbourSlot::newNeighbourAtTop)
        .ifPresent(slot::addNeighbour);
      this.findSlotAt(position.bottom())
        .map(NeighbourSlot::newNeighbourAtBottom)
        .ifPresent(slot::addNeighbour);
      this.findSlotAt(position.topLeft(this.height))
        .map(NeighbourSlot::newNeighbourAtTopLeft)
        .ifPresent(slot::addNeighbour);
      this.findSlotAt(position.topRight(this.height))
        .map(NeighbourSlot::newNeighbourAtTopRight)
        .ifPresent(slot::addNeighbour);
      this.findSlotAt(position.bottomLeft())
        .map(NeighbourSlot::newNeighbourAtBottomLeft)
        .ifPresent(slot::addNeighbour);
      this.findSlotAt(position.bottomRight(this.width))
        .map(NeighbourSlot::newNeighbourAtBottomRight)
        .ifPresent(slot::addNeighbour);
    });
  }

  private Optional<Slot> findSlotAt(final Optional<Position> maybePosition) {
    return this.slots.stream()
      .filter(slot -> maybePosition.map(slot::hasSamePosition).orElse(false))
      .findFirst();
  }

}
