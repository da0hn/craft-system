package com.da0hn.craft.system.core.domain.workbench;

import java.util.Set;

public sealed interface ManufacturingMethod {

  static ManufacturingMethod anyPosition() {
    return new AnyPositionMethod();
  }

  static ManufacturingMethod relativePosition(final Set<RelativeToPosition> relativeToItemsAt) {
    return new RelativePositionMethod(relativeToItemsAt);
  }

  record AnyPositionMethod() implements ManufacturingMethod {
  }

  record RelativePositionMethod(Set<RelativeToPosition> relativeTo) implements ManufacturingMethod {
  }

}
