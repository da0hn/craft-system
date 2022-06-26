package org.da0hn.recipe.core.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("unit")
@DisplayName("Test Constraint container")
class ConstraintsTest {

  @Test
  @DisplayName("Should add an Constraint")
  void test1() {
    final var aContainer = ConstraintContainer.empty();
    aContainer.add(new Constraint());
    assertFalse(aContainer.isEmpty());
  }

  @Test
  @DisplayName("Should not add an null Constraint")
  void test2() {
    final var aContainer = ConstraintContainer.empty();
    Assertions.assertThrows(
      NullPointerException.class,
      () -> aContainer.add(null),
      "Constraint must be not null"
    );
  }

  @Nested
  @DisplayName("Should create Constraint containter using static constructor")
  class StaticConstructorsTest {
    @Test
    @DisplayName("Should create an Constraint container empty")
    void test1() {
      final var emptyContainer = ConstraintContainer.empty();
      assertTrue(emptyContainer.isEmpty());
    }
  }

}
