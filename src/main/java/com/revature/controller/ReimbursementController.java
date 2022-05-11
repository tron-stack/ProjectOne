package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.RegisterReimbObject;
import com.revature.services.ReimbursementService;
import io.javalin.http.Handler;

public class ReimbursementController {
    private ReimbursementService rs;
    private ObjectMapper om;

    public ReimbursementController(ReimbursementService rs){
        this.rs = rs;
        this.om = new ObjectMapper();
    }

    public Handler handleRegister = (ctx) -> {
        RegisterReimbObject rro = om.readValue(ctx.body(), RegisterReimbObject.class);

        rs.registerReimbursement(rro.amount,rro.dateSubmitted,rro.dateResolved,rro.description,rro.reimbursementAuthor,rro.reimbursementResolver, rro.reimbursementType, rro.reimbursementStatus);
        ctx.status(201);
        ctx.result("Reimbursement Registered");
    };
}
