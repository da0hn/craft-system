package org.da0hn.recipe.core.usecases.impl;

import org.da0hn.commons.core.domain.Identity;
import org.da0hn.recipe.core.domain.Item;
import org.da0hn.recipe.core.domain.ItemContainer;
import org.da0hn.recipe.core.domain.ItemModel;
import org.da0hn.recipe.core.domain.Items;
import org.da0hn.recipe.core.domain.Recipe;
import org.da0hn.recipe.core.domain.RecipeException;
import org.da0hn.recipe.core.ports.RecipeRepository;
import org.da0hn.recipe.core.usecases.CreateRecipeUseCase;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.da0hn.recipe.core.shared.RecipeMessages.RECIPE_CREATION_UNEXPECTED_ERROR;


public class CreateRecipeUseCaseImpl implements CreateRecipeUseCase {

  private final RecipeRepository repository;

  public CreateRecipeUseCaseImpl(final RecipeRepository repository) {
    this.repository = repository;
  }

  @Override
  public RecipeCreatedOutput execute(final RecipeCreateInput input) {

    final var items = mapToDomainItems(input);

    final var recipe = new Recipe(
      Identity.empty(),
      input.name(),
      input.typeProduced(),
      input.quantityProduced(),
      items
    );

    this.repository.create(recipe);

    return Stream.of(recipe)
      .map(r -> new RecipeCreatedOutput(
        r.getIdentity(),
        r.itemStream().map(ItemModel::getIdentity).toList()
      ))
      .findFirst()
      .orElseThrow(() -> new RecipeException(RECIPE_CREATION_UNEXPECTED_ERROR));
  }

  private static Items mapToDomainItems(final RecipeCreateInput input) {
    return input.items().stream()
      .map(CreateRecipeUseCaseImpl::mapToDomainItem)
      .collect(Collectors.collectingAndThen(Collectors.toList(), ItemContainer::of));
  }

  private static Item mapToDomainItem(final ItemCreateInput item) {
    return new Item(
      Identity.empty(),
      item.type(),
      item.name(),
      item.quantity()
    );
  }

}
