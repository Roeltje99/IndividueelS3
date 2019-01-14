package application.Dal.ExeptionHandling;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.springframework.http.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(value = IllegalArgumentException.class)
    public final ResponseEntity<ApiError> handleIllegalArgumentException (IllegalArgumentException e, WebRequest request) {
        ApiError details = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), request.getDescription(false), new Date());
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiError> handleExpection(Exception e, WebRequest request) {
        ApiError details = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error. Better luck next time!", request.getDescription(false), new Date());
        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
