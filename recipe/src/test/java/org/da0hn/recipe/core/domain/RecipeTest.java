package org.da0hn.recipe.core.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Tag("unit")
@DisplayName("Test recipe")
class RecipeTest {

  @Test
  @DisplayName("Should create an recipe")
  void test1() {
    final var recipe = new Recipe(
      1L,
      "Wooden Sword"
    );
    assertNotNull(recipe);
  }

  @Test
  @DisplayName("Should create an recipe with empty constraints")
  void test2() {
    final var recipe = new Recipe(
      1L,
      "Wooden Sword"
    );
    assertFalse(recipe.hasConstraints());
  }

  @Nested
  @DisplayName("Should not create an recipe")
  class ShouldNotCreateRecipeTests {

    @Test
    @DisplayName("With null id")
    void test2() {
      Assertions.assertThrows(
        NullPointerException.class,
        () -> new Recipe(
          null,
          "Wooden Sword"
        ),
        "Recipe id must be not null"
      );
    }

    @Test
    @DisplayName("With null name")
    void test3() {
      Assertions.assertThrows(
        NullPointerException.class,
        () -> new Recipe(
          1L,
          null
        ),
        "Recipe name must be not null"
      );
    }

    @Test
    @DisplayName("With empty name")
    void test4() {
      Assertions.assertThrows(
        RecipeValidationException.class,
        () -> new Recipe(
          1L,
          ""
        ),
        "Recipe name must be not empty"
      );

      Assertions.assertThrows(
        RecipeValidationException.class,
        () -> new Recipe(
          1L,
          "           "
        ),
        "Recipe name must be not empty"
      );
    }

  }


}
