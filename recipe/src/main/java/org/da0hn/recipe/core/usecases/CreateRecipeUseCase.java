package org.da0hn.recipe.core.usecases;

import org.da0hn.commons.core.domain.Identity;
import org.da0hn.commons.core.usecases.UseCase;
import org.da0hn.recipe.core.domain.ItemType;

import java.util.List;


@FunctionalInterface
public interface CreateRecipeUseCase extends
  UseCase<CreateRecipeUseCase.RecipeCreateInput, CreateRecipeUseCase.RecipeCreatedOutput> {

  record RecipeCreateInput(
    String name,
    ItemType typeProduced,
    Integer quantityProduced,
    List<ItemCreateInput> items
  ) implements UseCase.Input {}

  record ItemCreateInput(
    String name,
    ItemType type,
    Integer quantity
  ) implements UseCase.Input {}

  record RecipeCreatedOutput(
    Identity<Long> idRecipe,
    List<Identity<Long>> items
  ) implements UseCase.Output {
  }

}
