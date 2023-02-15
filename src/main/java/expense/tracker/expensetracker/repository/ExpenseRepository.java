package expense.tracker.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import expense.tracker.expensetracker.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    
}
