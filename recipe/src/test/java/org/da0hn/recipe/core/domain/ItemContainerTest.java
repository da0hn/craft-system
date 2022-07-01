package org.da0hn.recipe.core.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Tag("unit")
@DisplayName("Test Item container")
class ItemContainerTest {

  @Test
  @DisplayName("Should add an Constraint")
  void test1() {
    final var aContainer = ItemContainer.empty();
    aContainer.add(new Item());
    assertFalse(aContainer.isEmpty());
  }

  @Test
  @DisplayName("Should not add an null Item")
  void test2() {
    final var aContainer = ItemContainer.empty();
    final var exception = Assertions.assertThrows(
      NullPointerException.class,
      () -> aContainer.add(null)
    );
    assertEquals("Item must be not null", exception.getMessage());
  }

  @Nested
  @DisplayName("Should create Item containter using static constructor")
  class StaticConstructorsTest {

    @Test
    @DisplayName("Should create an Item container empty")
    void test1() {
      final var emptyContainer = ItemContainer.empty();
      assertTrue(emptyContainer.isEmpty());
    }

  }

}
