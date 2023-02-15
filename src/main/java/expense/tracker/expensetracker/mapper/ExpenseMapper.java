package expense.tracker.expensetracker.mapper;

import org.springframework.stereotype.Component;
import expense.tracker.expensetracker.dto.ExpenseDto;
import expense.tracker.expensetracker.model.Expense;
@Component
public class ExpenseMapper {
    public ExpenseDto mapToDto(Expense expense){
        return ExpenseDto.builder()
        .id(expense.getId())
        .expenseName(expense.getExpenseName())
        .expenseCategory(expense.getExpenseCategory())
        .expenseAmount(expense.getExpenseAmount())
        .build();
    }

    public Expense mapFromDto(ExpenseDto expenseDto){
        return Expense.builder()
        .expenseName(expenseDto.getExpenseName())
        .expenseCategory(expenseDto.getExpenseCategory())
        .expenseAmount(expenseDto.getExpenseAmount())
        .build();  
    } 

}


