package dao;

import model.BankAccount;
import view.CoreBanking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
