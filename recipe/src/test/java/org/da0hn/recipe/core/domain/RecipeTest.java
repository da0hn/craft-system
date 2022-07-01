package org.da0hn.recipe.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@Tag("unit")
@DisplayName("Test recipe")
class RecipeTest {

  @Test
  @DisplayName("Should create an recipe")
  void test1() {
    final var recipe = new Recipe(
      1L,
      "Wooden Sword",
      ItemType.FINAL,
      1
    );
    assertNotNull(recipe);
  }

  @Test
  @DisplayName("Should create an recipe with empty constraints")
  void test2() {
    final var recipe = new Recipe(
      1L,
      "Wooden Sword",
      ItemType.FINAL,
      1
    );
    assertFalse(recipe.hasConstraints());
  }

  @Nested
  @DisplayName("Should not create an recipe")
  class ShouldNotCreateRecipeTests {

    @Test
    @DisplayName("With null id")
    void test1() {
      assertThrows(
        NullPointerException.class,
        () -> new Recipe(
          null,
          "Wooden Sword",
          ItemType.FINAL,
          1
        ),
        "Recipe id must be not null"
      );
    }

    @Test
    @DisplayName("With null name")
    void test2() {
      assertThrows(
        NullPointerException.class,
        () -> new Recipe(
          1L,
          null,
          ItemType.FINAL,
          1
        ),
        "Recipe name must be not null"
      );
    }

    @Test
    @DisplayName("With empty name")
    void test3() {
      assertThrows(
        RecipeValidationException.class,
        () -> new Recipe(
          1L,
          "",
          ItemType.FINAL,
          1
        ),
        "Recipe name must be not empty"
      );

      assertThrows(
        RecipeValidationException.class,
        () -> new Recipe(
          1L,
          "           ",
          ItemType.FINAL,
          1
        ),
        "Recipe name must be not empty"
      );
    }

    @Test
    @DisplayName("with null item type")
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
      assertEquals("Recipe produced item type must be not null", exception.getMessage());
    }

    @Test
    @DisplayName("with null quantity produced")
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
      assertEquals("Recipe quantity produced must be not null", exception.getMessage());
    }

    @Test
    @DisplayName("with quantity produced less than 1")
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
      assertEquals("Recipe quantity produced must be greater than one", exception.getMessage());
    }

  }


}
