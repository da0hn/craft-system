package org.da0hn.recipe.core.domain;

public interface Constraints {
  boolean isEmpty();
  void add(Constraint constraint);
}
