package com.da0hn.craft.system.core.domain.workbench;

import java.util.UUID;

public record RecipeItemId(String value) {

  public static RecipeItemId newInstance() {
    return new RecipeItemId(UUID.randomUUID().toString());
  }

  public static RecipeItemId of(final String value) {
    return new RecipeItemId(UUID.fromString(value).toString());
  }

}
