package com.example.bankkata;

import com.example.bankkata.bank.*;
import org.junit.jupiter.api.Test;

/**
 * @author $ {USER}
 **/
public class AccountTestIntegration {
    @Test
    public void testAccountIntegration() {

        TransactionRepository repository = new TransactionRepositoryImp();
        StatementPrinter printer = new StatementPrinterImp();
        AccountService account = new Account(repository, printer);

        account.deposit(1000);
        account.deposit(2000);
        account.withdraw(500);
        account.printStatement();
    }
}
