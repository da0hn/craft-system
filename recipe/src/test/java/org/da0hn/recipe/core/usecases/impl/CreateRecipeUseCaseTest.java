package org.da0hn.recipe.core.usecases.impl;

import org.da0hn.recipe.core.domain.ItemType;
import org.da0hn.recipe.core.domain.RecipeModel;
import org.da0hn.recipe.core.domain.RecipeValidationException;
import org.da0hn.recipe.core.ports.RecipeRepository;
import org.da0hn.recipe.core.usecases.CreateRecipeUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_ITEMS_NOT_EMPTY;
import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_ITEM_TYPE_NOT_FINAL;
import static org.da0hn.recipe.core.usecases.CreateRecipeUseCase.ItemCreateInput;
import static org.da0hn.recipe.core.usecases.CreateRecipeUseCase.RecipeCreateInput;


@Tag("unit")
@DisplayName("Test create recipe usecase")
class CreateRecipeUseCaseTest {

  private CreateRecipeUseCase createRecipeUseCase;

  private RecipeInMemoryRepository repository;

  @BeforeEach
  void setUp() {
    this.repository = new RecipeInMemoryRepository();
    this.createRecipeUseCase = new CreateRecipeUseCaseImpl(
      this.repository
    );
  }

  @Test
  @DisplayName("Should create an recipe with items")
  void test1() {

    final var input = makeRecipeInput(List.of(
      makeItemInput("item 1", ItemType.MATERIAL, 2),
      makeItemInput("item 2", ItemType.MATERIAL, 1)
    ));

    final var output = this.createRecipeUseCase.execute(input);

    assertNotNull(output.idRecipe());
    assertEquals(2, output.items().size());
    assertEquals(1, this.repository.repositoryCounter);
  }

  private static RecipeCreateInput makeRecipeInput(final List<ItemCreateInput> items) {
    return new RecipeCreateInput(
      "final item 1",
      ItemType.FINAL,
      2,
      items
    );
  }

  private static ItemCreateInput makeItemInput(
    final String name,
    final ItemType material,
    final int quantity
  ) {
    return new ItemCreateInput(name, material, quantity);
  }

  @Test
  @DisplayName("Should not create an recipe with any final items as material")
  void test2() {
    final var input = makeRecipeInput(List.of(
      makeItemInput("item 1", ItemType.FINAL, 2),
      makeItemInput("item 2", ItemType.MATERIAL, 1)
    ));

    final var exception = assertThrows(
      RecipeValidationException.class,
      () -> this.createRecipeUseCase.execute(input)
    );

    assertEquals(RECIPE_ITEM_TYPE_NOT_FINAL, exception.getMessage());
  }

  @Test
  @DisplayName("Should not create an recipe without material items")
  void test3() {
    final var input = makeRecipeInput(List.of());

    final var exception = assertThrows(
      RecipeValidationException.class,
      () -> this.createRecipeUseCase.execute(input)
    );

    assertEquals(RECIPE_ITEMS_NOT_EMPTY, exception.getMessage());
  }

  static class RecipeInMemoryRepository implements RecipeRepository {

    int repositoryCounter;

    @Override
    public void create(final RecipeModel recipe) {
      this.repositoryCounter++;
    }

  }

}
