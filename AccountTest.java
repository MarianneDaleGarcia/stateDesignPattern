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