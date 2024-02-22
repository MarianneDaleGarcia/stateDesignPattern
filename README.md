# stateDesignPattern

BACKUP DI AKO MARUNONG MAGSAVE POTANGENA✨

// AccountState interface
public interface AccountState {
    void deposit(Account account, double amount);

    void withdraw(Account account, double amount);

    void suspend(Account account);

    void activate(Account account);

    void close(Account account);
}

// ActiveState class
class ActiveState implements AccountState {
    @Override
    public void deposit(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        System.out.println(account.toString());
    }

    @Override
    public void withdraw(Account account, double amount) {
        account.setBalance(account.getBalance() - amount);
        System.out.println(account.toString());
    }

    @Override
    public void suspend(Account account) {
        account.setState(new SuspendedState());
        System.out.println("Account is suspended!");
    }

    @Override
    public void activate(Account account) {
        System.out.println("Account is already activated!");
    }

    @Override
    public void close(Account account) {
        account.setState(new ClosedState());
        System.out.println("Account is closed!");
    }
}

// SuspendedState class
class SuspendedState implements AccountState {
    @Override
    public void deposit(Account account, double amount) {
        System.out.println("No deposits allowed on suspended account!");
    }

    @Override
    public void withdraw(Account account, double amount) {
        System.out.println("No withdrawals allowed on suspended account!");
    }

    @Override
    public void suspend(Account account) {
        System.out.println("Account is already suspended!");
    }

    @Override
    public void activate(Account account) {
        account.setState(new ActiveState());
        System.out.println("Account is activated!");
    }

    @Override
    public void close(Account account) {
        account.setState(new ClosedState());
        System.out.println("Account is closed!");
    }
}

// ClosedState class
class ClosedState implements AccountState {
    @Override
    public void deposit(Account account, double amount) {
        System.out.println("You cannot deposit on a closed account!");
    }

    @Override
    public void withdraw(Account account, double amount) {
        System.out.println("You cannot withdraw on a closed account!");
    }

    @Override
    public void suspend(Account account) {
        System.out.println("You cannot suspend a closed account!");
    }

    @Override
    public void activate(Account account) {
        System.out.println("You cannot activate a closed account!");
    }

    @Override
    public void close(Account account) {
        System.out.println("Account is already closed!");
    }
}

// Account class
class Account {
    private String accountNumber;
    private double balance;
    private AccountState state;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.state = new ActiveState();
    }

    public void deposit(double amount) {
        state.deposit(this, amount);
    }

    public void withdraw(double amount) {
        state.withdraw(this, amount);
    }

    public void suspend() {
        state.suspend(this);
    }

    public void activate() {
        state.activate(this);
    }

    public void close() {
        state.close(this);
    }

    public void setState(AccountState state) {
        this.state = state;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}

// AccountTest class
public class AccountTest {
    public static void main(String[] args) {
        Account myAccount = new Account("1234", 10000.0);

        myAccount.activate(); // displays "Account is already activated!"

        myAccount.suspend(); // displays "Account is suspended!"

        myAccount.activate(); // displays "Account is activated!"

        myAccount.deposit(1000.0); // updates balance and displays account number and current balance.

        myAccount.withdraw(100.0); // updates balance and displays account number and current balance.

        myAccount.close(); // displays "Account is closed!"

        myAccount.activate(); // displays "You cannot activate a closed account!"

        myAccount.suspend(); // displays "You cannot suspend a closed account!"

        myAccount.withdraw(500.0); // shows message "You cannot withdraw on a closed account!". Call the toString() to show current balance and account number.

        myAccount.deposit(1000.0); // shows message "You cannot deposit on a closed account!". Call the toString() to show current balance and account number.
    }
}