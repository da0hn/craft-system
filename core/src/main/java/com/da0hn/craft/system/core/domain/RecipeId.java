package com.da0hn.craft.system.core.domain;

import java.util.UUID;

public record RecipeId(String value) {

  public static RecipeId newInstance() {
    return new RecipeId(UUID.randomUUID().toString());
  }

  public static RecipeId of(final String value) {
    return new RecipeId(UUID.fromString(value).toString());
  }

}
