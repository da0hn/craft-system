package org.da0hn.recipe.core.domain;

import java.util.stream.Stream;


public interface Items {

  boolean isEmpty();

  void add(Item constraint);

  Stream<Item> stream();

}
