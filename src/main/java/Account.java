import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.time.LocalDate.*;

public class Account {

    private List<AccountEvent> accountEvents = new ArrayList<>();

    public void makeADeposit(double amount, LocalDate date) {
        AccountEvent credit = new AccountEvent(AccountEvent.AccountEventType.CREDIT, amount, date);
        registerAccountEvent(credit);
    }

    public void makeAWithdrawal(double amount, LocalDate date) {
        AccountEvent credit = new AccountEvent(AccountEvent.AccountEventType.DEBIT, amount, date);
        registerAccountEvent(credit);
    }

    public void displayHistory(LocalDate date) {
        accountEvents.stream()
                .filter(isScopedEvent(date))
                .forEach(accountEvent -> System.out.println(accountEvent + " " + getBalance(accountEvent.getEventDate()) ));
    }

    double getBalance(LocalDate date) {
        double debitAmount = accountEvents.stream()
                .filter(accountEvent -> accountEvent.getType() == AccountEvent.AccountEventType.DEBIT)
                .filter(isScopedEvent(date))
                .mapToDouble(AccountEvent::getAmount)
                .sum();
        double creditAmount = accountEvents.stream()
                .filter(accountEvent -> accountEvent.getType() == AccountEvent.AccountEventType.CREDIT)
                .filter(isScopedEvent(date))
                .mapToDouble(AccountEvent::getAmount)
                .sum();
        return creditAmount - debitAmount;
    }

    private void registerAccountEvent(AccountEvent accountEvent) {
        this.accountEvents.add(accountEvent);
    }

    private Predicate<AccountEvent> isScopedEvent(LocalDate date) {
        return accountEvent -> accountEvent.getEventDate().isBefore(date)
                || accountEvent.getEventDate().equals(date) ;
    }

    public static void main(String args[]) {
        Account account = new Account();
        account.makeADeposit(2000, of(2018, 10, 1));
        account.makeAWithdrawal(50, of(2018, 10, 2));
        account.makeAWithdrawal(86, of(2018, 10, 3));
        account.makeAWithdrawal(100, of(2018, 10, 5));
        account.makeAWithdrawal(120, of(2018, 10, 12));

        account.displayHistory(of(2018, 10, 2));
    }

}
