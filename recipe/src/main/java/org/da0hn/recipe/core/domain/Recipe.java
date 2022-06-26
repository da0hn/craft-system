package org.da0hn.recipe.core.domain;

import java.util.Objects;

public class Recipe {
  private final Long id;
  private final String name;
  private final Constraints constraints;

  public Recipe(
    final Long id,
    final String name
  ) {
    this.id = Objects.requireNonNull(id, "Recipe id must be not null");
    this.name = Objects.requireNonNull(name, "Recipe name must be not null");

    if(name.isEmpty() || name.isBlank()) {
      throw new RecipeValidationException("Recipe name must be not empty");
    }

    this.constraints = ConstraintContainer.empty();
  }


  public boolean hasConstraints() {
    return !this.constraints.isEmpty();
  }
}
