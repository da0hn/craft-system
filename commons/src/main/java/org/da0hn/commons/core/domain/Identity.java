package org.da0hn.commons.core.domain;

import java.util.Objects;


public final class Identity<ID> {

  private final ID id;

  private Identity(final ID id) {
    this.id = id;
  }

  public static <ID> Identity<ID> of(final ID value) {
    return new Identity<>(value);
  }

  public static <ID> Identity<ID> empty() {
    return new Identity<>(null);
  }

  public ID getId() {
    return this.id;
  }

  @Override public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override public boolean equals(final Object o) {
    if(this == o) return true;
    if(!(o instanceof final Identity<?> that)) return false;
    return this.id.equals(that.id);
  }

  @Override public String toString() {
    return "{\"Identity\":{" +
           "\"id\":" + this.id
           + "}}";
  }

}
