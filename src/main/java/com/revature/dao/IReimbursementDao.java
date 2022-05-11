package com.revature.dao;

import com.revature.models.Reimbursement;

import java.util.List;

public interface IReimbursementDao {

    public void createReimbursement(Reimbursement reimbursement);

    public List<Reimbursement> readAllPendingRequests(int statusId);

}
