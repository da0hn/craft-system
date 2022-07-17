package org.da0hn.recipe.core.domain;

import org.da0hn.commons.core.domain.Identity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.da0hn.recipe.core.shared.RecipeMessages.*;


@Tag("unit")
@DisplayName("Test recipe")
class RecipeTest {

  private static final String WOODEN_SWORD = "Wooden Sword";

  private static final int QUANTITY = 1;

  private static final String WOODEN_STICK = "wooden stick";

  private static final String PLANK = "plank";

  private static void makeRecipeWithItem(
    final Identity<Long> id,
    final String name,
    final ItemType itemType,
    final Integer quantity
  ) {
    new Recipe(
      id,
      name,
      itemType,
      quantity,
      ItemContainer.of(
        new Item(Identity.of(1L), ItemType.MATERIAL, WOODEN_STICK, 1),
        new Item(Identity.of(2L), ItemType.MATERIAL, PLANK, 2)
      )
    );
  }

  @Test
  @DisplayName("Should create an recipe")
  void test1() {
    final var recipe = makeRecipe(
      Identity.of(1L),
      WOODEN_SWORD,
      ItemType.FINAL,
      QUANTITY,
      ItemContainer.of(
        new Item(Identity.of(1L), ItemType.MATERIAL, WOODEN_STICK, 1),
        new Item(Identity.of(2L), ItemType.MATERIAL, PLANK, 2)
      )
    );
    assertNotNull(recipe);
  }

  private static RecipeModel makeRecipe(
    final Identity<Long> id,
    final String name,
    final ItemType itemType,
    final int quantity,
    final Items items
  ) {
    return new Recipe(id, name, itemType, quantity, items);
  }


  @Test
  @DisplayName("Should add item to recipe")
  void test3() {
    final var recipe = makeRecipe(
      Identity.of(1L),
      WOODEN_SWORD,
      ItemType.FINAL,
      QUANTITY,
      ItemContainer.of(
        new Item(Identity.of(1L), ItemType.MATERIAL, WOODEN_STICK, 1),
        new Item(Identity.of(2L), ItemType.MATERIAL, PLANK, 2)
      )
    );
    recipe.addItem(makeItem(ItemType.MATERIAL, 2));
    assertTrue(recipe.hasItems());
  }

  private static Item makeItem(
    final ItemType itemType,
    final int quantity
  ) {
    return new Item(Identity.of(1L), itemType, "Plank", quantity);
  }


  @Nested
  @DisplayName("Should not create an recipe")
  class ShouldNotCreateRecipeTests {

    @Test
    @DisplayName("With null id")
    void test1() {
      final var exception = assertThrows(
        NullPointerException.class,
        () -> makeRecipeWithItem(null, WOODEN_SWORD, ItemType.FINAL, QUANTITY)
      );
      assertEquals(RECIPE_ID_NOT_NULL, exception.getMessage());
    }

    @Test
    @DisplayName("With null name")
    void test2() {
      final var exception = assertThrows(
        NullPointerException.class,
        () -> makeRecipeWithItem(Identity.of(1L), null, ItemType.FINAL, QUANTITY)
      );
      assertEquals(RECIPE_NAME_NOT_NULL, exception.getMessage());
    }

    @Test
    @DisplayName("With empty name")
    void test3() {
      final var exception1 = assertThrows(
        RecipeValidationException.class,
        () -> makeRecipeWithItem(Identity.empty(), "", ItemType.FINAL, QUANTITY)
      );

      assertEquals(RECIPE_NAME_NOT_EMPTY, exception1.getMessage());

      final var exception2 = assertThrows(
        RecipeValidationException.class,
        () -> makeRecipeWithItem(
          Identity.empty(),
          "           ",
          ItemType.FINAL,
          QUANTITY
        )
      );
      assertEquals(RECIPE_NAME_NOT_EMPTY, exception2.getMessage());
    }

    @Test
    @DisplayName("With null item type")
    void test4() {
      final var exception = assertThrows(
        NullPointerException.class,
        () -> makeRecipeWithItem(
          Identity.empty(),
          WOODEN_SWORD,
          null,
          QUANTITY
        )
      );
      assertEquals(RECIPE_TYPE_PRODUCED_NOT_NULL, exception.getMessage());
    }

    @Test
    @DisplayName("With null quantity produced")
    void test5() {
      final var exception = assertThrows(
        NullPointerException.class,
        () -> makeRecipeWithItem(
          Identity.empty(),
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
        () -> makeRecipeWithItem(
          Identity.empty(),
          "Wooden stick",
          ItemType.MATERIAL,
          0
        )
      );
      assertEquals(
        RECIPE_QUANTITY_PRODUCED_LESS_THAN_ONE,
        exception.getMessage()
      );
    }

    @Test
    @DisplayName("With empty items")
    void test7() {
      final var exception = assertThrows(
        RecipeValidationException.class,
        () -> makeRecipe(
          Identity.of(1L),
          WOODEN_SWORD,
          ItemType.FINAL,
          QUANTITY,
          ItemContainer.empty()
        )
      );
      assertEquals(RECIPE_ITEMS_NOT_EMPTY, exception.getMessage());
    }


    @Test
    @DisplayName("With null items")
    void test8() {
      final var exception = assertThrows(
        NullPointerException.class,
        () -> makeRecipe(
          Identity.of(1L),
          WOODEN_SWORD,
          ItemType.FINAL,
          QUANTITY,
          null
        )
      );
      assertEquals(RECIPE_ITEMS_NOT_NULL, exception.getMessage());
    }

  }

  @Nested
  @DisplayName("Should not add item to recipe")
  class ShouldNotAddItemTests {

    @Test
    @DisplayName("With item type final")
    void test1() {
      final var recipe = makeRecipe(
        Identity.of(1L),
        WOODEN_SWORD,
        ItemType.FINAL,
        QUANTITY,
        ItemContainer.of(
          new Item(Identity.of(1L), ItemType.MATERIAL, WOODEN_STICK, 1),
          new Item(Identity.of(2L), ItemType.MATERIAL, PLANK, 2)
        )
      );
      final var exception = assertThrows(
        RecipeValidationException.class,
        () -> recipe.addItem(makeItem(ItemType.FINAL, 2))
      );
      assertEquals(RECIPE_ITEM_TYPE_NOT_FINAL, exception.getMessage());
    }

    @Test
    @DisplayName("With item quantity less than 1")
    void test2() {
      final var recipe = makeRecipe(
        Identity.of(1L),
        WOODEN_SWORD,
        ItemType.FINAL,
        QUANTITY,
        ItemContainer.of(
          new Item(Identity.of(1L), ItemType.MATERIAL, WOODEN_STICK, 1),
          new Item(Identity.of(2L), ItemType.MATERIAL, PLANK, 2)
        )
      );
      final var exception = assertThrows(
        RecipeValidationException.class,
        () -> recipe.addItem(makeItem(ItemType.MATERIAL, 0))
      );
      assertEquals(RECIPE_ITEM_QUANTITY_LESS_THAN_ONE, exception.getMessage());
    }


  }

}
