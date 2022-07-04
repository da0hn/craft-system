package org.da0hn.commons.core.usecases;

@FunctionalInterface
public interface UseCase<INPUT extends UseCase.Input, OUTPUT extends UseCase.Output> {

  OUTPUT execute(final INPUT input);

  interface Input {}

  interface Output {}

}
