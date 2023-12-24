package com.da0hn.craft.system.core.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WorkbenchTest {

  @BeforeEach
  void setUp() {
  }

  @Test
  @DisplayName("Should create Workbench with 9 slots")
  void test1() {

    final var workbench = Workbench.newWorkbench(3, 3);

    Assertions.assertThat(workbench.slots()).hasSize(9);
  }

}
