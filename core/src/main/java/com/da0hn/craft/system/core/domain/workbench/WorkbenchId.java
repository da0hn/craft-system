package com.da0hn.craft.system.core.domain.workbench;

import java.util.UUID;

public record WorkbenchId(String value) {

  public static WorkbenchId newInstance() {
    return new WorkbenchId(UUID.randomUUID().toString());
  }

  public static WorkbenchId of(final String value) {
    return new WorkbenchId(UUID.fromString(value).toString());
  }

}
