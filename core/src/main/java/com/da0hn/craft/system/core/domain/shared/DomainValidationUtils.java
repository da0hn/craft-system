package com.da0hn.craft.system.core.domain.shared;

import java.util.Collection;

public final class DomainValidationUtils {

  public static String assertNotEmpty(final String value, final String message) {
    if (value == null || value.isEmpty()) {
      throw new IllegalArgumentException(message);
    }
    return value;
  }

  public static <T> T assertNotNull(final T value, final String message) {
    if (value == null) {
      throw new IllegalArgumentException(message);
    }
    return value;
  }

  public static <T> T assertIsNull(final T value, final String message) {
    if (value != null) {
      throw new IllegalArgumentException(message);
    }
    return null;
  }

  public static <T extends Collection<?>> T assertCollectionIsNotEmpty(final T collection, final String message) {
    if (collection == null || collection.isEmpty()) {
      throw new IllegalArgumentException(message);
    }
    return collection;
  }

  public static void isTrue(final boolean condition, final String message) {
    if (!condition) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void isFalse(final boolean condition, final String message) {
    if (condition) {
      throw new IllegalArgumentException(message);
    }
  }

}
