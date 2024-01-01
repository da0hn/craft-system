package com.da0hn.craft.system.core.domain.item;

import java.util.UUID;

public record ItemId(String value) {

  public static ItemId newInstance() {
    return new ItemId(UUID.randomUUID().toString());
  }

  public static ItemId of(final String value) {
    return new ItemId(UUID.fromString(value).toString());
  }

}
