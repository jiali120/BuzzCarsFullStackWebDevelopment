package gatech.cs.buzzcar.common.except;

import gatech.cs.buzzcar.common.model.Result;
import gatech.cs.buzzcar.common.model.ValidateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail(e.getMessage());
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("400 BAD REQUEST-HttpMessageNotReadableException {}", e.getMessage());
        log.error(e.getMessage(), e);
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler({
            HttpRequestMethodNotSupportedException.class
    })
    public Result httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("405 MethodNotSupported {}", e.getMessage());
        log.error(e.getMessage(), e);
        return Result.fail("MethodNotSupported");
    }

    @ExceptionHandler({
            RuntimeException.class
    })
    public Result server500(RuntimeException e) {
        log.error("500 exception {}", e.getMessage());
        log.error(e.getMessage(), e);
        return Result.fail( e.getMessage());
    }


    @ExceptionHandler(ValidateException.class)
    public Result handleValidationException(ValidateException e) {
        log.error("param error ValidateException");
        log.error(e.getMessage(), e);
        //, e.getValidateResultList()
        return Result.fail(
                "param error-" + e.getValidateResultList().parallelStream().map(validateResult -> validateResult.getField() + "-" + validateResult.getMessage())
                        .collect(Collectors.joining("|")) );
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidatedException(MethodArgumentNotValidException e) {
        log.error("param error validateException");
        log.error(e.getMessage(), e);
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<ValidateResult> validateResults = fieldErrors.stream().map(fieldError -> new ValidateResult(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
        return Result.fail(
                "param error-" + validateResults.parallelStream()
                        .map(validateResult -> validateResult.getField() + "-" + validateResult.getMessage()).collect(Collectors.joining("|")));

    }



    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException e) {
        log.info("spring default param BindException", e);
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<ValidateResult> validateResults = fieldErrors.stream().map(fieldError -> new ValidateResult(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
        return Result.fail(
                "param error-" + validateResults.parallelStream()
                        .map(validateResult -> validateResult.getField() + "-" + validateResult.getMessage()).collect(Collectors.joining("|")));
    }
}