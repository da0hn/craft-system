package org.da0hn.recipe.core.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ConstraintContainer implements Constraints {

  private final Set<Constraint> constraints = new HashSet<>();

  public static Constraints empty() {
    return new ConstraintContainer();
  }

  @Override public boolean isEmpty() {
    return this.constraints.isEmpty();
  }

  @Override public void add(final Constraint constraint) {
    this.constraints.add(Objects.requireNonNull(constraint, "Constraint must be not null"));
  }
}
