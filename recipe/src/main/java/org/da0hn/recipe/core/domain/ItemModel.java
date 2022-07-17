package org.da0hn.recipe.core.domain;

import org.da0hn.commons.core.domain.Identity;


public interface ItemModel {

  ItemType getType();

  Integer getQuantity();

  Identity<Long> getIdentity();

}
