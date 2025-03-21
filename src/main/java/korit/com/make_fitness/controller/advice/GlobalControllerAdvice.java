package korit.com.make_fitness.controller.advice;

import korit.com.make_fitness.exception.DuplicatedValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalControllerAdvice {

    // ✅ 중복 예외 처리 (Swagger에서 응답 표시되도록 수정)
    @ExceptionHandler(DuplicatedValueException.class)
    public ResponseEntity<?> duplicatedException(DuplicatedValueException e) {
        List<FieldError> errors = e.getFieldErrors();

        if (errors == null || errors.isEmpty()) {
            return ResponseEntity.badRequest().body("중복된 값이 존재합니다.");
        }

        // JSON 형식으로 반환
        return ResponseEntity.badRequest().body(errors);
    }


}