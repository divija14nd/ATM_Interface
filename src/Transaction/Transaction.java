/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Transaction;

import db.Database;
import db.User;
import java.sql.SQLException;

/**
 *
 * @author mitrajeet
 */
public class Transaction {

    /**
     * Updates the bank balance by adding the @code{depositAmt} to the current
     * bank balance
     * 
     * @param depositAmt    : int   - The amount to be added in the bank balance
     * @param u             : User  - The instance of the user to be updated
     * 
     * @throws SQLException
     */
    public void deposit(int depositAmt, User u) throws SQLException {
        Database db = new Database();
            db.connect();
            db.updateBankBalance(
                    u.getId(),
                    u.getBankBalance(),
                    depositAmt,
                    false
            );
        u.setBankBalance(u.getBankBalance() + depositAmt);
        db.closeConnection();
    }
    
    /**
     * Updates the bank balance by subtracting the @code{withdrawAmt} to the current
     * bank balance
     * 
     * @param withdrawAmt   : int   - The amount to be reduced from the bank balance
     * @param u             : User  - The instance of the user to be updated
     * @throws SQLException 
     */
    public void withdraw(int withdrawAmt, User u) throws SQLException {
        Database db = new Database();
        db.connect();

        int bal = u.getBankBalance();

        if (bal >= withdrawAmt) {
            db.updateBankBalance(
                    u.getId(),
                    u.getBankBalance(),
                    withdrawAmt,
                    true
            );
            u.setBankBalance(u.getBankBalance() - withdrawAmt);
            db.closeConnection();
        } else {
            throw new RuntimeException("Insufficient bank balance");
        }
    }
}
