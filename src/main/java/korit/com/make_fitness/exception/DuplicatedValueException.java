package korit.com.make_fitness.exception;

import korit.com.make_fitness.controller.advice.FieldError;
import lombok.Getter;


import java.util.List;

@Getter
public class DuplicatedValueException extends RuntimeException {
  private List<FieldError> fieldErrors;

  public DuplicatedValueException(List<FieldError> fieldErrors) {
    super("중복 오류");
    this.fieldErrors = fieldErrors;
  }
}
