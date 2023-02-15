package expense.tracker.expensetracker.service;

import java.util.List;
import org.springframework.stereotype.Service;
import expense.tracker.expensetracker.dto.ExpenseDto;
import expense.tracker.expensetracker.error.ExpenseNotFoundException;
import expense.tracker.expensetracker.mapper.ExpenseMapper;
import expense.tracker.expensetracker.model.Expense;
import expense.tracker.expensetracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public Long addExpense(ExpenseDto expenseDto) {
        Expense expense = expenseMapper.mapFromDto(expenseDto);
        return expenseRepository.save(expense).getId();
    }

    public ExpenseDto getExpenseById(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException("no expense found with id " + expenseId));
        return expenseMapper.mapToDto(expense);
    }

    public List<ExpenseDto> getAllExpenses() {
        return expenseRepository.findAll().stream().map(expenseMapper::mapToDto).toList();
    }

    public void deleteExpense(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException("no expense found with id " + expenseId));
        expenseRepository.delete(expense);
    }

    public ExpenseDto updateExpense(Long expenseId, ExpenseDto expenseDto) {

        Expense savedExpense = expenseRepository.findById(expenseId).orElseThrow(() -> new ExpenseNotFoundException(
                String.format("Cannot Find Expense by ID %s", expenseId)));
        savedExpense.setExpenseName(expenseDto.getExpenseName());
        savedExpense.setExpenseCategory(expenseDto.getExpenseCategory());
        savedExpense.setExpenseAmount(expenseDto.getExpenseAmount());

        expenseRepository.save(savedExpense);
        return expenseMapper.mapToDto(savedExpense);
    }
}
