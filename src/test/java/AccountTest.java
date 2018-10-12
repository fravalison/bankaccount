import org.junit.Test;

import java.time.LocalDate;

import static junit.framework.Assert.assertEquals;

public class AccountTest {

    @Test
    public void balance_should_be_empty_when_no_operation_in_my_account() throws Exception {

        Account account = new Account();

        assertEquals(0.0, account.getBalance(LocalDate.now()));
    }

    @Test
    public void balance_should_be_equals_to_50_when_I_credit_to_50() throws Exception {
        LocalDate now = LocalDate.now();
        Account account = new Account();
        account.makeADeposit(50, now);

        assertEquals(50.0, account.getBalance(now));
    }

    @Test
    public void balance_should_be_equals_to_minus_50_when_I_debit_to_50() throws Exception {
        LocalDate now = LocalDate.now();
        Account account = new Account();
        account.makeAWithdrawal(50, now);

        assertEquals(-50.0, account.getBalance(now));
    }

    @Test
    public void balance_should_be_equals_to_30_when_I_credit_to_80_and_debit_to_50() throws Exception {
        LocalDate now = LocalDate.now();
        Account account = new Account();
        account.makeADeposit(80, now);
        account.makeAWithdrawal(50, now);

        assertEquals(30.0, account.getBalance(now));
    }

}