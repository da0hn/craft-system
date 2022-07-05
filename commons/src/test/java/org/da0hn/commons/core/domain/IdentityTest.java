package org.da0hn.commons.core.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;


@Tag("unit")
@DisplayName("Test Identity")
class IdentityTest {


  @Test
  @DisplayName("should create an identity with null value")
  void test1() {
    final var identity = Identity.empty();
    assertNull(identity.getId());
  }

  @Test
  @DisplayName("should create an identity with value")
  void test2() {
    final var identity = Identity.of(1L);
    Assertions.assertEquals(1L, identity.getId());
  }

}
