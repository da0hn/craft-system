package org.da0hn.recipe.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_ID_NOT_NULL;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_ITEM_QUANTITY_LESS_THAN_ONE;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_ITEM_TYPE_NOT_FINAL;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_NAME_NOT_EMPTY;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_NAME_NOT_NULL;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_QUANTITY_PRODUCED_LESS_THAN_ONE;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_QUANTITY_PRODUCED_NOT_NULL;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_TYPE_PRODUCED_NOT_NULL;


@Tag("unit")
@DisplayName("Test recipe")
class RecipeTest {

  @Test
  @DisplayName("Should create an recipe")
  void test1() {
    final var recipe = makeRecipe();
    assertNotNull(recipe);
  }

  private static Recipe makeRecipe() {
    return new Recipe(
      1L,
      "Wooden Sword",
      ItemType.FINAL,
      1
    );
  }

  @Test
  @DisplayName("Should create an recipe with empty constraints")
  void test2() {
    final var recipe = makeRecipe();
    assertFalse(recipe.hasItems());
  }

  @Test
  @DisplayName("Should add item to recipe")
  void test3() {
    final var recipe = makeRecipe();
    recipe.addItem(makeItem(ItemType.MATERIAL, 2));
    assertTrue(recipe.hasItems());
  }

  private static Item makeItem(
    final ItemType itemType,
    final int quantity
  ) {
    return new Item(
      1L,
      itemType,
      "Plank",
      quantity
    );
  }


  @Nested
  @DisplayName("Should not create an recipe")
  class ShouldNotCreateRecipeTests {

    @Test
    @DisplayName("With null id")
    void test1() {
      final var exception = assertThrows(
        NullPointerException.class,
        () -> new Recipe(
          null,
          "Wooden Sword",
          ItemType.FINAL,
          1
        )

      );
      assertEquals(RECIPE_ID_NOT_NULL, exception.getMessage());
    }

    @Test
    @DisplayName("With null name")
    void test2() {
      final var exception = assertThrows(
        NullPointerException.class,
        () -> new Recipe(
          1L,
          null,
          ItemType.FINAL,
          1
        )

      );
      assertEquals(RECIPE_NAME_NOT_NULL, exception.getMessage());
    }

    @Test
    @DisplayName("With empty name")
    void test3() {
      final var exception1 = assertThrows(
        RecipeValidationException.class,
        () -> new Recipe(
          1L,
          "",
          ItemType.FINAL,
          1
        )
      );

      assertEquals(RECIPE_NAME_NOT_EMPTY, exception1.getMessage());

      final var exception2 = assertThrows(
        RecipeValidationException.class,
        () -> new Recipe(
          1L,
          "           ",
          ItemType.FINAL,
          1
        )
      );
      assertEquals(RECIPE_NAME_NOT_EMPTY, exception2.getMessage());
    }

    @Test
    @DisplayName("With null item type")
    void test4() {
      final var exception = assertThrows(
        NullPointerException.class,
        () -> new Recipe(
          1L,
          "Wooden stick",
          null,
          1
        )
      );
      assertEquals(RECIPE_TYPE_PRODUCED_NOT_NULL, exception.getMessage());
    }

    @Test
    @DisplayName("With null quantity produced")
    void test5() {
      final var exception = assertThrows(
        NullPointerException.class,
        () -> new Recipe(
          1L,
          "Wooden stick",
          ItemType.MATERIAL,
          null
        )
      );
      assertEquals(RECIPE_QUANTITY_PRODUCED_NOT_NULL, exception.getMessage());
    }

    @Test
    @DisplayName("With quantity produced less than 1")
    void test6() {
      final var exception = assertThrows(
        RecipeValidationException.class,
        () -> new Recipe(
          1L,
          "Wooden stick",
          ItemType.MATERIAL,
          0
        )
      );
      assertEquals(RECIPE_QUANTITY_PRODUCED_LESS_THAN_ONE, exception.getMessage());
    }

  }

  @Nested
  @DisplayName("Should not add item to recipe")
  class ShouldNotAddItemTests {

    @Test
    @DisplayName("With item type final")
    void test1() {
      final var recipe = makeRecipe();
      final var exception = assertThrows(
        RecipeValidationException.class,
        () -> recipe.addItem(makeItem(ItemType.FINAL, 2))
      );
      assertEquals(RECIPE_ITEM_TYPE_NOT_FINAL, exception.getMessage());
    }

    @Test
    @DisplayName("With item quantity less than 1")
    void test2() {
      final var recipe = makeRecipe();
      final var exception = assertThrows(
        RecipeValidationException.class,
        () -> recipe.addItem(makeItem(ItemType.MATERIAL, 0))
      );
      assertEquals(RECIPE_ITEM_QUANTITY_LESS_THAN_ONE, exception.getMessage());
    }


  }

}
