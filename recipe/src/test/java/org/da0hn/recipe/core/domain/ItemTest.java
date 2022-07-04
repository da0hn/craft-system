package org.da0hn.recipe.core.domain;

import org.da0hn.commons.core.domain.Identity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.da0hn.recipe.core.shared.RecipeMessages.ITEM_ID_NOT_NULL;
import static org.da0hn.recipe.core.shared.RecipeMessages.ITEM_NAME_NOT_EMPTY;
import static org.da0hn.recipe.core.shared.RecipeMessages.ITEM_NAME_NOT_NULL;
import static org.da0hn.recipe.core.shared.RecipeMessages.ITEM_QUANTITY_NEGATIVE;
import static org.da0hn.recipe.core.shared.RecipeMessages.ITEM_QUANTITY_NOT_NULL;
import static org.da0hn.recipe.core.shared.RecipeMessages.ITEM_TYPE_NOT_NULL;


@Tag("unit")
@DisplayName("Test item")
class ItemTest {

  @Test
  @DisplayName("Should create an item")
  void test1() {
    final var item = new Item(
      Identity.of(1L),
      ItemType.MATERIAL,
      "Plank",
      2
    );
    Assertions.assertNotNull(item);
  }

  @Nested
  @DisplayName("Should not create an Item")
  class ShouldNotCreateItemTest {

    @Test
    @DisplayName("With null id")
    void test1() {
      final var exception = Assertions.assertThrows(
        NullPointerException.class,
        () -> new Item(
          null,
          ItemType.MATERIAL,
          "Plank",
          2
        )
      );
      assertEquals(ITEM_ID_NOT_NULL, exception.getMessage());
    }

    @Test
    @DisplayName("With null item type")
    void test2() {
      final var exception = Assertions.assertThrows(
        NullPointerException.class,
        () -> new Item(
          Identity.empty(),
          null,
          "Plank",
          2
        )
      );
      assertEquals(ITEM_TYPE_NOT_NULL, exception.getMessage());
    }

    @Test
    @DisplayName("With null name")
    void test3() {
      final var exception = Assertions.assertThrows(
        NullPointerException.class,
        () -> new Item(
          Identity.empty(),
          ItemType.FINAL,
          null,
          2
        )
      );
      assertEquals(ITEM_NAME_NOT_NULL, exception.getMessage());
    }

    @Test
    @DisplayName("With empty name")
    void test4() {
      final var exception = Assertions.assertThrows(
        RecipeValidationException.class,
        () -> new Item(
          Identity.of(1L),
          ItemType.FINAL,
          "",
          2
        )
      );
      assertEquals(ITEM_NAME_NOT_EMPTY, exception.getMessage());
    }

    @Test
    @DisplayName("With null quantity")
    void test5() {
      final var exception = Assertions.assertThrows(
        NullPointerException.class,
        () -> new Item(
          Identity.of(1L),
          ItemType.FINAL,
          "Plank",
          null
        )
      );
      assertEquals(ITEM_QUANTITY_NOT_NULL, exception.getMessage());
    }

    @Test
    @DisplayName("With negative quantity")
    void test6() {
      final var exception = Assertions.assertThrows(
        RecipeValidationException.class,
        () -> new Item(
          Identity.of(1L),
          ItemType.FINAL,
          "Plank",
          -1
        )
      );
      assertEquals(ITEM_QUANTITY_NEGATIVE, exception.getMessage());
    }

  }


}
