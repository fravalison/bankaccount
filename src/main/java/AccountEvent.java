import java.time.LocalDate;

public class AccountEvent {

    enum AccountEventType {
        DEBIT, CREDIT;
    }

    private AccountEventType type;

    private double amount;

    private LocalDate eventDate;

    public AccountEvent(AccountEventType type, double amount, LocalDate eventDate) {
        this.type = type;
        this.amount = amount;
        this.eventDate = eventDate;
    }

    public AccountEventType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    @Override
    public String toString() {
        return "AccountEvent{" +
                "type=" + type +
                ", amount=" + amount +
                ", eventDate=" + eventDate +
                '}';
    }
}
