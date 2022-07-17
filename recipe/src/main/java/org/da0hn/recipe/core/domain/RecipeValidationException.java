package org.da0hn.recipe.core.domain;

import java.io.Serial;

public class RecipeValidationException extends RecipeException {

  @Serial
  private static final long serialVersionUID = -1603102988227453606L;

  public RecipeValidationException() {
  }

  public RecipeValidationException(final String message) {
    super(message);
  }

}
