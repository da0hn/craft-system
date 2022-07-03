package org.da0hn.commons.core.usecases;

public interface UseCase<INPUT extends UseCase.Input, OUTPUT extends UseCase.Output> {

  OUTPUT execute(INPUT input);

  interface Input {}

  interface Output {}

}
