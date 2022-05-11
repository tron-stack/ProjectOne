package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.RegisterReimbObject;
import com.revature.services.ReimbursementService;
import io.javalin.http.Handler;

import java.util.Objects;

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

    public Handler handleGetAllRequestsByStatus = (ctx) -> {
        //
        if(!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("roleId")),"1")){
            ctx.status(401);
            ctx.result("You must login as an Employee to see your requests");
        }else {
            int statusId = Integer.parseInt(ctx.pathParam("id"));
            int userId = Integer.parseInt((String) ctx.req.getSession().getAttribute("userId"));
            ctx.result(om.writeValueAsString(rs.getAllRequestsByStatus(userId, statusId)));
            ctx.status(200);
        }
    };

    public Handler handleGetAllRequestsByUserId = (ctx) -> {
        if(!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("roleId")),"1")){
            ctx.status(401);
            ctx.result("You must login as an Employee to see your requests");
        }else {
            int userId = Integer.parseInt((String) ctx.req.getSession().getAttribute("userId"));
            ctx.result(om.writeValueAsString(rs.getAllRequestsByUserId(userId)));
            ctx.status(200);
        }
    };
}
