class BankAccount {
    private double balance;
    private static int lastAccountDigits=0;
    private int accountDigits; //unique
    private String accountNumber;

    BankAccount(){ //default constructor //LV93HABA0551031375321
        accountDigits=++lastAccountDigits;
        accountNumber=String.format("LV93HABA055%08d", accountDigits); //%08 is "8 zeros" if needed in-between
        balance=0; //when you first register, the balance is 0
    }
    BankAccount(double balance){
        this.balance=balance; //setting balance
    } //constructor with a parameter

    public void deposit(double amount){
        balance+=amount;
    }
    public void withdraw(double amount){
        balance-=amount;
    }
    public String getAccountNumber(){
        return accountNumber;
    }
    public void printBalance(){
        System.out.println("Current balance "+balance);
    }
    public void transfer(double amount, BankAccount accountTo){
      //  this.balance=balance-amount;
        withdraw(amount);
        accountTo.deposit(amount);
    }
    String getinfo(){
        return "\naccount number: "+this.accountNumber+"\nbalance: "+this.balance+"\n";
    }
}
public class Main {
    public static void main(String[] args) {
        BankAccount user0=new BankAccount();
        BankAccount user1=new BankAccount();
        System.out.print(user0.getinfo());
        System.out.println(user1.getinfo());
        user0.deposit(250);
        System.out.print("Added 250 Euros to account 1: "+user0.getinfo());
        System.out.println(user1.getinfo());
        user0.transfer(110, user1);
        System.out.println("Transferred 110 Euros to account 2: "+user0.getinfo()+user1.getinfo());

    }
}