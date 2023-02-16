package expense.tracker.expensetracker.error;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
    private Boolean success;
    private String cause;
    private String data;
    private String path;
    private String timestamp;

    public ErrorMessage(Boolean success, String cause, String data, String path) {
        this.timestamp = Instant.now().toString();
        this.success = success;
        this.cause = cause;
        this.data = data;
        this.path = path;
    }

}
