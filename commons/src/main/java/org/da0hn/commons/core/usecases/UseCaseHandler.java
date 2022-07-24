package org.da0hn.commons.core.usecases;

import java.util.function.Function;


@FunctionalInterface
public interface UseCaseHandler {

  <RESULT, INPUT extends UseCase.Input, OUTPUT extends UseCase.Output> RESULT handle(
    UseCase<INPUT, OUTPUT> useCase,
    INPUT input,
    Function<OUTPUT, RESULT> mapper
  );

}
