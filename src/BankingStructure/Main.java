/* Diana Vigdorova, started @ 27.03.24.
[DONE] Create a new project "Banking" and create a class "BankAccount" with property "balance" (should be decimal number)
[DONE] create an empty default constructor for it
[DONE] create a constructor with parameter for setting balance.
[DONE] create a method "deposit" with one parameter "amount" (decimal number)
[DONE] create a method "withdraw" with one parameter "amount"(decimal number)
[DONE] create a method "printBalance" which displays the current balance to user
[DONE] method to transfer balance from one bank account to another
[DONE] Optionally make the program interactive with user e.g. using Scanner
 +data structure
 +account reports in .txt
*/
// TODO add the username transfer functionality
package BankingStructure;
import java.util.Scanner;
import java.util.*;
import org.javatuples.Triplet;

class BankAccount {
    private static Set<Triplet<String, String, String>> accounts = new HashSet<>();
    private String username;
    private String password;
    private double balance=0;
    private static int lastAccountDigits=0;
    private int accountDigits;
    private String accountNumber;
    BankAccount(){} //empty default constructor //LV93HABA0551031375321
    BankAccount(double balance){
        this.balance=balance;}//constructor with a parameter
    BankAccount(String username, String password){ //this one actually gets used
        this.username=username;
        this.password=password;
        accountDigits=++lastAccountDigits;
        accountNumber=String.format("LV93HABA055%08d", accountDigits); //%08 is "8 zeros" if needed in-between
    }
    public void deposit(double amount){
        balance+=amount;
    }
    public void withdraw(double amount){
        balance-=amount;
    }
    public void printBalance(){
        System.out.println("Current balance "+balance);
    }
    public void transfer(double amount, BankAccount accountTo){
      //  this.balance=balance-amount;
        withdraw(amount);
        accountTo.deposit(amount);
    }
    public String getInfo(){
        return "\nAccount number: "+this.accountNumber+"\nBalance: "+this.balance+"\n";
    }
    public String getUsername() {return username;}
    public String getAccountNumber(){
        return accountNumber;
    }
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
    static void register(Scanner scanner){
        System.out.println("Please enter your desired username");
        String username=scanner.nextLine();
        while(usernameExists(username)) {
            System.out.println("Username already exists, try a different one.");
            username=scanner.nextLine();
        }
        System.out.println("Please enter your desired password");
        String password=scanner.nextLine();
        //optionally can ask to repeat the password
        //  BankAccount user=new BankAccount(username,password);
        BankAccount account=new BankAccount(username,password);

        // account.getAccountNumber();
        accounts.add(Triplet.with(username,password,account.getAccountNumber()));
        System.out.println("Your account has been successfully created! Your account information is:"+account.getInfo());
    }
    private static boolean usernameExists(String username) {
        return accounts.stream().anyMatch(account -> account.getValue0().equals(username));
    }
    public static void login(Scanner scanner, String username) {
        // This is a simplified approach; actual password checking might need more secure handling
        Optional<Triplet<String, String, String>> matchingAccount = accounts.stream()
                .filter(account -> account.getValue0().equals(username))
                .findFirst();

        if (matchingAccount.isPresent()) {
            System.out.println("Enter your password:");
            String password = scanner.nextLine();
            if (matchingAccount.get().getValue1().equals(password)) {
                System.out.println("Logged in successfully. Welcome back, " + username + "!");
            } else {
                System.out.println("Incorrect password.");
            }
        } else {
            System.out.println("Account not found. Please try again.");
        }
    }
}
public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome!\nEnter your username or click 1 to register, click 4 to exit!");
        BankAccount manager = new BankAccount();
        Scanner scanner=new Scanner(System.in);
        String line=scanner.nextLine();
        if("1".equals(line)){
            manager.register(scanner);}
        if("4".equals(line)){
            System.out.println("You have exited your account!");
        }
        else {
            manager.login(scanner,line);

            System.out.println("Welcome back!");
            Byte choice;
            do {
                System.out.println("Select your activity:\nPress: 1 for balance, 2 to transfer, 3 to print account details, 4 to exit");
                choice=scanner.nextByte();
                switch (choice){
                    case 1: {}
                    case 2: {
                        //System.out.println(user.getInfo());
                        }
                    case 3:
                }
            }
            while (choice!=4);
            System.out.println("You have exited your account!");


        }
        scanner.close();
    /*    System.out.print(user0.getInfo());
        System.out.println(user1.getInfo());
        user0.deposit(250);
        System.out.print("Added 250 Euros to account 1: "+user0.getInfo());
        System.out.println(user1.getInfo());
        user0.transfer(110, user1);
        System.out.println("Transferred 110 Euros to account 2: "+user0.getInfo()+user1.getInfo());
*/
    }
}