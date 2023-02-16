package expense.tracker.expensetracker.dto;

import java.math.BigDecimal;

import expense.tracker.expensetracker.model.ExpenseCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExpenseDto {
    
    private Long id;
    @NotBlank
    private String expenseName;
    @NotNull
    private ExpenseCategory expenseCategory;
    @Min(value = 0)
    @NotNull
    private BigDecimal expenseAmount;
}
