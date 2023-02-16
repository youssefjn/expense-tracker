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
        expenseRepository.save(expense);
        return expenseMapper.mapToDto(expense).getId();
    }

    public ExpenseDto getExpenseById(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException(expenseId.toString()));
        return expenseMapper.mapToDto(expense);
    }

    public List<ExpenseDto> getAllExpenses() {
        return expenseRepository.findAll().stream().map(expenseMapper::mapToDto).toList();
    }

    public void deleteExpense(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException(expenseId.toString()));
        expenseRepository.delete(expense);
    }

    public ExpenseDto updateExpense(Long expenseId, ExpenseDto expenseDto) {

        Expense savedExpense = expenseRepository.findById(expenseId).orElseThrow(() -> new ExpenseNotFoundException(
                expenseId.toString()));
        if (expenseDto.getExpenseName() != null && expenseDto.getExpenseName().length() > 0) {
            savedExpense.setExpenseName(expenseDto.getExpenseName());
        }
        if (expenseDto.getExpenseCategory() != null) {
            savedExpense.setExpenseCategory(expenseDto.getExpenseCategory());
        }
        if (expenseDto.getExpenseAmount() != null) {
            savedExpense.setExpenseAmount(expenseDto.getExpenseAmount());
        }
        expenseRepository.save(savedExpense);
        return expenseMapper.mapToDto(savedExpense);
    }
}
