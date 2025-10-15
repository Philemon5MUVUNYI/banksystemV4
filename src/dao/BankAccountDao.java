package dao;

import com.sun.security.jgss.GSSUtil;
import model.BankAccount;
import view.CoreBanking;

import java.sql.*;
import java.util.Scanner;

public class BankAccountDao {
    private String jdUrl ="jdbc:postgresql://localhost:5432/javathursdayv4";
    private String jdUserName ="postgres";
    private String jdPassword ="root";

    public Integer registerDetails(BankAccount account_dets){
        try {
            Connection con_register = DriverManager.getConnection(jdUrl ,jdUserName ,jdPassword);
            String sql = "INSERT INTO accounts(number ,name ,type ,amount) values (? ,? ,? ,?)";

            PreparedStatement pst_register =con_register.prepareStatement(sql);
            pst_register.setString(1,account_dets.getAccountNumber());
            pst_register.setString(2, account_dets.getAccountName());
            pst_register.setString(3,account_dets.getAccountType());
            pst_register.setDouble(4,account_dets.getAmount());

            int rowsAffected = pst_register.executeUpdate();

            con_register.close();
            return rowsAffected;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }
    public void displayAllData(){

        Scanner input1 = new Scanner(System.in);
        CoreBanking core = new CoreBanking();
        try {
            Connection con_display = DriverManager.getConnection(jdUrl ,jdUserName ,jdPassword);
            String sql = "Select * from accounts";
            PreparedStatement pst_display = con_display.prepareStatement(sql);
            ResultSet rs_display = pst_display.executeQuery();
            int count = 0;
            while (rs_display.next()){
                count++;
                System.out.println("  Account "+count);
                System.out.println("Number: "+rs_display.getString("number"));
                System.out.println("Name  : "+rs_display.getString("name"));
                System.out.println("Type  : "+rs_display.getString("type"));
                System.out.println("Amount: "+rs_display.getString("amount"));
                System.out.println("\n");
            }
            con_display.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public Integer updateRecord(){
        Scanner input_update = new Scanner(System.in);
        System.out.print("Enter the number of the account to update: ");
        String  number_to_update = input_update.next();

        String sql = "SELECT * FROM accounts WHERE number = ?";
        try (Connection con_update0 = DriverManager.getConnection(jdUrl ,jdUserName ,jdPassword);
        PreparedStatement pst_update = con_update0.prepareStatement(sql)) {

            pst_update.setString(1, number_to_update);
            ResultSet rs_update = pst_update.executeQuery();
            if (rs_update.next()) {
                System.out.println("    USER FOUND");
                System.out.println("Number: " + rs_update.getString("number"));
                System.out.println("Name  : " + rs_update.getString("name"));
                System.out.println("Type  : " + rs_update.getString("type"));
                System.out.println("Amount: $" + rs_update.getDouble("amount"));
                System.out.println("====================================================");
                System.out.print("Do you wish to update the whole record(YES/NO)? ");
                String choice_update = input_update.next();
                if (choice_update.equalsIgnoreCase("YES")) {
                    System.out.println("========================================");
                    System.out.print("Enter a new name: ");
                    String yes_Name = input_update.next();
                    System.out.print("Enter a type of account: ");
                    String yes_Type = input_update.next();
                    System.out.print("Enter new amount: ");
                    double yes_Amount = input_update.nextDouble();

                    try {
                        Connection cons5 = DriverManager.getConnection(jdUrl ,jdUserName ,jdPassword);
                        Statement stev = cons5.createStatement();

                        String newAll = String.format("UPDATE accounts SET name = '%s',type = '%s',amount= %f WHERE number = '%s'",yes_Name ,yes_Type ,yes_Amount,number_to_update);
                        int rowsAffected10 = stev .executeUpdate(newAll);
                        if (rowsAffected10 > 0){
                            System.out.println("Records has been updated successfully.");
                        }else {
                            System.out.println("Nothing updated.");
                        }

                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                } else if (choice_update.equalsIgnoreCase("NO")){


                        System.out.print("Which attribute do you want to update(Name/Type/Amount)?");
                        String choice_update1 = input_update.next();

                        switch (choice_update1) {
                            case "Name":
                                System.out.print("Enter the new name: ");
                                String no_new_name = input_update.next();
                                try {
                                    Connection cons = DriverManager.getConnection(jdUrl, jdUserName, jdPassword);
                                    Statement st = cons.createStatement();

                                    String noSqlName = String.format("UPDATE accounts SET name = '%s' WHERE number = '%s'", no_new_name, number_to_update ,number_to_update);
                                    int rowsAffeted = st.executeUpdate(noSqlName);
                                    if (rowsAffeted > 0) {
                                        System.out.println("Data saved successfully");
                                    } else {
                                        System.out.println("Nothing saved.");
                                    }
                                    cons.close();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }

                                break;
                            case "Type":
                                System.out.print("Enter new type: ");
                                String no_new_type = input_update.next();

                                try {
                                    Connection cons1 = DriverManager.getConnection(jdUrl, jdUserName, jdPassword);
                                    Statement state = cons1.createStatement();

                                    String noSqlType = String.format("UPDATE accounts SET type = '%s' WHERE number = '%s'", no_new_type, number_to_update);
                                    int rowsAffect3 = state.executeUpdate(noSqlType);
                                    if (rowsAffect3 > 0) {
                                        System.out.println("Type updated.");
                                    } else {
                                        System.out.println("Not happened.");
                                    }
                                    cons1.close();

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                break;
                            case "Amount":
                                System.out.print("Enter the new amount: ");
                                double no_new_amount = input_update.nextDouble();

                                try {
                                    Connection cons2 = DriverManager.getConnection(jdUrl ,jdUserName ,jdPassword);
                                    Statement stAmount = cons2.createStatement();

                                    String no_sql_amount = String.format("UPDATE accounts SET amount = %f WHERE number = '%s'",no_new_amount ,number_to_update);
                                    int rowsAffected4 = stAmount.executeUpdate(no_sql_amount);
                                    if (rowsAffected4 > 0){
                                        System.out.println("Amount updated.");
                                    }else {
                                        System.out.println("Not saved.");
                                    }
                                    cons2.close();
                                }catch (Exception ex){
                                    ex.printStackTrace();
                                }
                                break;
                            default:
                                System.out.println("Wrong choice");
                        }
                }else {
                    System.out.println("Wrong choice.");
                }
                    con_update0.close();
                }else{
                    System.out.println("No data found.");
                }


            }catch(Exception ex){
                ex.printStackTrace();
            }

        return 0;
    }
}
