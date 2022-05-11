package com.revature.dao;

import com.revature.models.Reimbursement;
import com.revature.utils.DaoUtilities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReimbursementDaoJDBC implements IReimbursementDao{
    Connection conn = null;
    PreparedStatement ps = null;


    @Override
    public void createReimbursement(Reimbursement reimbursement) {
        String sql = "insert into reimbursement(amount, submitted_date, resolved_date, description, reimbursement_author, reimbursement_resolver, reimbursement_type, reimbursement_status) values(?,?,?,?,?,?,?,?)";

        try {
            conn = DaoUtilities.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setDouble(1, reimbursement.getAmount());
            ps.setDate(2, Date.valueOf(java.time.LocalDate.now()));
            ps.setDate(3, null);
            ps.setString(4, reimbursement.getDescription());
            ps.setInt(5, reimbursement.getReimbursementAuthor());
            ps.setInt(6, reimbursement.getReimbursementResolver());
            ps.setInt(7, reimbursement.getReimbursementType());
            ps.setInt(8, reimbursement.getReimbursementStatus());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
