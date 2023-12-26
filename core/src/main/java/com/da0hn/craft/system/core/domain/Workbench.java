package com.da0hn.craft.system.core.domain;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Workbench {

  private final String name;

  private final WorkbenchId workbenchId;

  private final Set<Slot> slots;

  private final Set<Recipe> recipes;

  private Workbench(final String name, final WorkbenchId workbenchId) {
    this.name = name;
    this.workbenchId = workbenchId;
    this.slots = new LinkedHashSet<>(0);
    this.recipes = new LinkedHashSet<>(0);
  }

  public static Workbench newWorkbench(final String workbenchName, final int width, final int height) {
    final Workbench workbench = new Workbench(workbenchName, WorkbenchId.newInstance());

    final var slots = IntStream.range(0, width)
      .mapToObj(y -> IntStream.range(0, height).mapToObj(x -> Slot.newSlot(workbenchName, Position.of(x, y))))
      .flatMap(Function.identity())
      .collect(Collectors.toCollection(LinkedHashSet::new));

    workbench.slots.addAll(slots);
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

}
