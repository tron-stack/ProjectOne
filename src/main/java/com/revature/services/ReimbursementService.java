package com.revature.services;

import com.revature.dao.IReimbursementDao;
import com.revature.models.Reimbursement;

import java.util.List;

public class ReimbursementService {
	private IReimbursementDao ird;
	public ReimbursementService(IReimbursementDao ird){
		this.ird = ird;
	}

	public List<Reimbursement> getAllReimbursements() {
		return ird.getAllReimbursements();
	}
	public List<Reimbursement> getAllPendingReimbursement() {
		return ird.getAllPendingReimbursements();
	}
	public List<Reimbursement> getAllResolvedReimbursements() {
		return ird.getAllResolvedReimbursements();
	}

	public List<Reimbursement> getAllReimbursementsById(int id){
		return ird.getAllReimbursementsById(id);
	}

	public void approvePending(int id){
		ird.approvePendingReimbursement(id);
	}

	public void denyPending(int id){
		ird.denyPendingReimbursement(id);
	}
}
