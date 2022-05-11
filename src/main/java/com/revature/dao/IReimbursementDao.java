package com.revature.dao;

import com.revature.models.Reimbursement;

import java.util.List;

public interface IReimbursementDao {

    public void createReimbursement(Reimbursement reimbursement);

    public List<Reimbursement> readAllRequestsByStatus(int userId, int statusId);

    public List<Reimbursement> readAllRequestsById(int userId);

}
