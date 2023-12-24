package com.da0hn.craft.system.core.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Workbench {

  private final Set<Slot> slots;

  private Workbench() {
    this.slots = new HashSet<>(0);
  }

  public static Workbench newWorkbench(final int width, final int height) {
    final var slots = IntStream.range(0, width)
      .mapToObj(x -> IntStream.range(0, height).mapToObj(y -> Slot.newSlot(Position.of(x, y))))
      .flatMap(Function.identity())
      .collect(Collectors.toSet());

    final Workbench workbench = new Workbench();
    workbench.slots.addAll(slots);
    return workbench;
  }

  public Set<Slot> slots() {
    return Collections.unmodifiableSet(this.slots);
  }

}
