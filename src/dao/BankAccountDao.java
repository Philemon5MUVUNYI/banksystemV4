package dao;

import model.BankAccount;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
}
