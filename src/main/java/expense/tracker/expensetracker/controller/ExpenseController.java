package expense.tracker.expensetracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import expense.tracker.expensetracker.dto.ExpenseDto;
import expense.tracker.expensetracker.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @Operation(summary = "Add an Expense and return its body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Expense is created successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid/Bad request", content = @Content) })
    @PostMapping
    public ResponseEntity<ExpenseDto> addExpense(@RequestBody @Valid ExpenseDto expenseDto) {
        expenseService.addExpense(expenseDto);
        return new ResponseEntity<ExpenseDto>(expenseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Get an Expense by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "expense is fetched successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Expense not found", content = @Content) })
    @GetMapping("/{expenseId}")
    public ResponseEntity<ExpenseDto> getExpense(@PathVariable Long expenseId) {
        return new ResponseEntity<ExpenseDto>(expenseService.getExpenseById(expenseId), HttpStatus.OK);
    }

    @Operation(summary = "Get a list of all Expenses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All expenses are fetched successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Expenses not found", content = @Content) })
    @GetMapping("/all")
    public ResponseEntity<?> getAllExpense() {
        List<ExpenseDto> expenses = expenseService.getAllExpenses();
        if (expenses.isEmpty()) {
                return new ResponseEntity<>("no expenses found", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ExpenseDto>>(expenses, HttpStatus.OK);
    }

    @Operation(summary = "Delete an Expense by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "expense deleted successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Expense not found", content = @Content) })
    @DeleteMapping("/{expenseId}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long expenseId) {
        expenseService.deleteExpense(expenseId);
        return new ResponseEntity<>(String.format("expense with id %s deleted successfully", expenseId), HttpStatus.OK);
    }

    @Operation(summary = "Update an Expense by id and given body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "expense updated successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid/Bad Request", content = @Content) })
    @PutMapping("/{expenseId}")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable Long expenseId,@RequestBody ExpenseDto expenseDto) {
        expenseService.updateExpense(expenseId,expenseDto);
        return new ResponseEntity<>(expenseDto, HttpStatus.OK);
    }
}