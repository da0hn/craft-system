package org.da0hn.recipe.core.domain;

import java.io.Serial;


public class RecipeException extends RuntimeException {

  @Serial private static final long serialVersionUID = 9170346069740360616L;

  public RecipeException() {
  }

  public RecipeException(final String message) {
    super(message);
  }

  public RecipeException(
    final String message,
    final Throwable cause
  ) {
    super(message, cause);
  }

}
