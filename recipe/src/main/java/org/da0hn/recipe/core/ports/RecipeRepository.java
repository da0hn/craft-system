package org.da0hn.recipe.core.ports;

import org.da0hn.recipe.core.domain.RecipeModel;


@FunctionalInterface
public interface RecipeRepository {

  void create(RecipeModel recipe);

}
