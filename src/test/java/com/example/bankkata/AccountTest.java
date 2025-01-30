package com.example.bankkata;

import com.example.bankkata.bank.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AccountTest {
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private StatementPrinter statementPrinter;

    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        accountService = new Account(transactionRepository, statementPrinter);
    }

    @Test
    public void should_register_transaction_for_a_valid_deposit() {
        accountService.deposit(100);
        verify(transactionRepository).addTransaction(any(Transaction.class));
    }

    @Test
    public void should_throw_exception_when_depositing_negative_amount() {
        assertThrows(IllegalArgumentException.class, () -> accountService.deposit(-100));
    }

    @Test
    public void should_register_transaction_for_a_valid_withdraw() {
        when(transactionRepository.getAllTransactions()).thenReturn(List.of(new Transaction("01/02/2024", 200, 200)));
        accountService.withdraw(100);
        verify(transactionRepository).addTransaction(any(Transaction.class));
    }

    @Test
    public void should_throw_exception_when_withdrawing_more_than_balance() {
        when(transactionRepository.getAllTransactions()).thenReturn(List.of(new Transaction("01/02/2024", 100, 100)));
        assertThrows(IllegalArgumentException.class, () -> accountService.withdraw(200));
    }

    @Test
    public void should_print_empty_statement_when_no_transactions_exist() {
        when(transactionRepository.getAllTransactions()).thenReturn(List.of());
        accountService.printStatement();
        verify(statementPrinter).printLine("Date       | Amount | Balance");
        verifyNoMoreInteractions(statementPrinter);
    }

}
