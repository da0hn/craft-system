package org.da0hn.recipe.core.domain;

import java.util.stream.Stream;


public interface Items {

  boolean isEmpty();

  void add(ItemModel constraint);

  Stream<ItemModel> stream();

}
