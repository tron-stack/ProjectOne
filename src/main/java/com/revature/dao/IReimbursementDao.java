package com.revature.dao;

import com.revature.models.Reimbursement;

import java.util.List;

public interface IReimbursementDao {

	public List<Reimbursement> getAllReimbursements();
	public List<Reimbursement> getAllPendingReimbursements();
	public List<Reimbursement> getAllResolvedReimbursements();

	public List<Reimbursement> getAllReimbursementsById(int id);

	public void approvePendingReimbursement(int id);
	public void denyPendingReimbursement(int id);

}
