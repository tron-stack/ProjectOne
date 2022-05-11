package com.revature.dao;

import com.revature.models.Reimbursement;
import com.revature.utils.DaoUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDaoJDBC implements IReimbursementDao {

	Connection conn = null;
	PreparedStatement ps = null;

	@Override
	public List<Reimbursement> getAllReimbursements() {
		String sql = "select * from reimbursement";
		try {
			Connection conn = DaoUtilities.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> list = new ArrayList<>();
			Reimbursement reimb = null;
			while(rs.next()){
				reimb = new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
				list.add(reimb);
			}
			return list;

		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Reimbursement> getAllPendingReimbursements() {
		String sql = "select * from reimbursement where reimbursement_status = 1";
		try {
			Connection conn = DaoUtilities.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> list = new ArrayList<>();
			Reimbursement reimb = null;
			while(rs.next()){
				reimb = new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
				list.add(reimb);
			}
			return list;

		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Reimbursement> getAllResolvedReimbursements() {
		String sql = "select * from reimbursement where reimbursement_status != 1";
		try {
			Connection conn = DaoUtilities.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> list = new ArrayList<>();
			Reimbursement reimb = null;
			while(rs.next()){
				reimb = new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
				list.add(reimb);
			}
			return list;

		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Reimbursement> getAllReimbursementsById(int id) {
		String sql = "select * from reimbursement where reimbursement_author = ?";
		try {
			Connection conn = DaoUtilities.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> list = new ArrayList<>();
			Reimbursement reimb = null;
			while(rs.next()){
				reimb = new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
				list.add(reimb);
			}
			return list;

		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void approvePendingReimbursement(int id) {
		String sql = "update reimbursement set reimbursement_status = 2 where reimbursement_id = ?";

		try {
			Connection conn = DaoUtilities.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();

		} catch(SQLException e){
			e.printStackTrace();
		}


	}

	@Override
	public void denyPendingReimbursement(int id) {
		String sql = "update reimbursement set reimbursement_status = 3 where reimbursement_id = ?";

		try {
			Connection conn = DaoUtilities.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();

		} catch(SQLException e){
			e.printStackTrace();
		}
	}


}
