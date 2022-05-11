package com.revature.dao;

import com.revature.models.Reimbursement;
import com.revature.utils.DaoUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Reimbursement> readAllRequestsByStatus(int statusId) {
        String sql = "Select * from reimbursement where reimbursement_status = ?";

        try{
            conn = DaoUtilities.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,statusId);

            ResultSet rs = ps.executeQuery();
            List<Reimbursement> pendingList = new ArrayList<>();
            while(rs.next()){
                Reimbursement reqest = new Reimbursement(rs.getInt(1), rs.getDouble(2),rs.getDate(3), rs.getDate(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9));

                pendingList.add(reqest);
            }
            return pendingList;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
