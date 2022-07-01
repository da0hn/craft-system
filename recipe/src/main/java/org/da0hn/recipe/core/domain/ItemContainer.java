package org.da0hn.recipe.core.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class ItemContainer implements Items {

  private final Set<Item> constraints = new HashSet<>();

  public static Items empty() {
    return new ItemContainer();
  }

  @Override public boolean isEmpty() {
    return this.constraints.isEmpty();
  }

  @Override public void add(final Item constraint) {
    this.constraints.add(Objects.requireNonNull(constraint, "Item must be not null"));
  }

}
