package expense.tracker.expensetracker.error;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class ControllerAdvice {
    private final MessageSource messageSource;

    private List<String> processAllErrors(List<ObjectError> allErrors) {
        return allErrors.stream().map(this::resolveLocalizedErrorMessage).collect(Collectors.toList());
    }

    private String resolveLocalizedErrorMessage(ObjectError objectError) {
        Locale currentLocal = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(objectError, currentLocal);
        return localizedErrorMessage;
    }
    

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage processValidationError(MethodArgumentNotValidException ex, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<ObjectError> allErrors = result.getAllErrors();
        String data = processAllErrors(allErrors).stream().collect(Collectors.joining(" // "));
        return new ErrorMessage(false, data, ex.getClass().getName(),  request.getDescription(false));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage exception(Exception exception, WebRequest request) {
        return new ErrorMessage(false, exception.getMessage(), exception.getClass().getName(),
                request.getDescription(false));
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage expenseNotFoundException(ExpenseNotFoundException exception, WebRequest request) {
        return new ErrorMessage(false, exception.getMessage(), exception.getClass().getName(),
                 request.getDescription(false));
    }
}
