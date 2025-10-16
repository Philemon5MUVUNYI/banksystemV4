package view;

import dao.BankAccountDao;
import model.BankAccount;

import java.util.Scanner;

public class CoreBanking {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String accountName;
        String accountNumber;
        String accountType;
        double amount;

        boolean condition = true;
        while (condition) {
                System.out.println("------------------------------------");
                System.out.println("===Menu===");
                System.out.println("1. Register account.");
                System.out.println("2. Display all accounts.");
                System.out.println("3. Update accounts.");
                System.out.println("4. Search account.");
                System.out.println("5. Delete account.");
                System.out.println("0 .Exit.");
                System.out.println("======================================");
                System.out.print("Enter your choice: ");
                Integer choice = input.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("=======================================");
                        System.out.print("Create your account number: ");
                        accountNumber = input.next();
                        System.out.print("Enter your name: ");
                        accountName = input.next();
                        System.out.print("Enter the type of account: ");
                        accountType = input.next();
                        System.out.print("Enter the amount: ");
                        amount = input.nextDouble();

                        //shifting data to model
                        BankAccount account_info = new BankAccount();
                        account_info.setAccountNumber(accountNumber);
                        account_info.setAccountName(accountName);
                        account_info.setAccountType(accountType);
                        account_info.setAmount(amount);

                        //Putting the data to dao
                        BankAccountDao dao_registry = new BankAccountDao();
                        int rowsAffected = dao_registry.registerDetails(account_info);
                        if (rowsAffected > 0) {
                            System.out.println("===========================");
                            System.out.println("Data saved successfully.");
                        } else {
                            System.out.println("Nothing happened");
                        }
                        break;
                    case 2:
                        System.out.println("========All accounts========");
                        //getting data from the data base and display them
                        BankAccountDao dao_display = new BankAccountDao();
                        dao_display.displayAllData();
                        System.out.print("Do you want to continue with the program Y/N: ");
                        String answer = input.next();
                        if (answer.equalsIgnoreCase("Y")){
                            condition = true;
                        }else {
                            System.out.println("\n");
                            System.out.println("Thank you for using the system.");
                            condition = false;
                        }

                        break;
                    case 3:
                        BankAccountDao update = new BankAccountDao();
                        update.updateRecord();
                        break;
                    case 4:
                        System.out.println("");
                        break;
                    case 5:
                        System.out.println("");
                        break;
                    case 0:
                        System.out.println("Thank you for using this system.");
                        System.exit(0);

            }
        }
    }
}

