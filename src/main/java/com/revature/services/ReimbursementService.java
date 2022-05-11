package com.revature.services;

import com.revature.dao.IReimbursementDao;
import com.revature.models.Reimbursement;

import java.util.Date;
import java.util.List;

public class ReimbursementService {
    private IReimbursementDao ird;

    public ReimbursementService(IReimbursementDao ird){
        this.ird = ird;
    }

    public void registerReimbursement(double amount, Date dateSubmitted, Date dateResolved, String description, int author, int resolver, int type, int status){
        Reimbursement reimbursement = new Reimbursement(0,amount, dateSubmitted, dateResolved,description,author,resolver,type,status);
        ird.createReimbursement(reimbursement);
    }

    public List<Reimbursement> getAllPendingRequests(int statusId){
        return ird.readAllPendingRequests(statusId);
    }

}
