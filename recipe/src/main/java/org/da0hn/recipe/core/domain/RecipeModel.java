package org.da0hn.recipe.core.domain;

import org.da0hn.commons.core.domain.Identity;

import java.util.stream.Stream;


public interface RecipeModel {

  boolean hasItems();

  void addItem(ItemModel item);

  Identity<Long> getIdentity();

  Stream<ItemModel> itemStream();

}
