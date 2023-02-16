package expense.tracker.expensetracker.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class ExpenseNotFoundException extends RuntimeException {
   
    private String id;
    public ExpenseNotFoundException(String id) {
        super(String.format(" expense with id %s is not found ",id));
        this.id = id;
    }
}
