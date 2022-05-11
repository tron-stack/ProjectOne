package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.RegisterReimbObject;
import com.revature.services.ReimbursementService;
import io.javalin.http.Handler;

import java.util.Objects;

public class ReimbursementController {
	private ReimbursementService rs;
	private ObjectMapper om;
	public ReimbursementController(ReimbursementService rs) {
		this.rs = rs;
		this.om = new ObjectMapper();
	}

	public Handler handleAllReimbursements = ctx -> {
		if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("roleId")), "2")) {
			ctx.status(401);
			ctx.result("You must login as manager to handle User data");
		} else {
			rs.getAllReimbursements();
			ctx.result(om.writeValueAsString(rs.getAllReimbursements()));
		}
	};
	public Handler handleAllResolvedReimbursements = ctx -> {
		if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("roleId")), "2")) {
			ctx.status(401);
			ctx.result("You must login as manager to handle User data");
		} else {
			rs.getAllResolvedReimbursements();
			ctx.result(om.writeValueAsString(rs.getAllResolvedReimbursements()));
		}
	};
	public Handler handleAllPendingReimbursements = ctx-> {
		if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("roleId")), "2")) {
			ctx.status(401);
			ctx.result("You must login as manager to handle User data");
		} else {
			rs.getAllPendingReimbursement();
			ctx.result(om.writeValueAsString(rs.getAllPendingReimbursement()));
		}
	};

	public Handler handleGetReimbursementsById = ctx-> {
		if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("roleId")), "2")) {
			ctx.status(401);
			ctx.result("You must login as manager to handle User data");
		} else {
			int id = Integer.parseInt(ctx.pathParam("id"));
			rs.getAllReimbursementsById(id);
			ctx.result(om.writeValueAsString(rs.getAllReimbursementsById(id)));
		}
	};

	public Handler handleApproveReimbursementsById = ctx-> {
		if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("roleId")), "2")) {
			ctx.status(401);
			ctx.result("You must login as manager to handle User data");
		} else {
			int id = Integer.parseInt(ctx.pathParam("id"));
			rs.approvePending(id);
			ctx.result(om.writeValueAsString(rs.getAllResolvedReimbursements()));
		}
	};

	public Handler handleDenyReimbursementsById = ctx-> {
		if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("roleId")), "2")) {
			ctx.status(401);
			ctx.result("You must login as manager to handle User data");
		} else {
			int id = Integer.parseInt(ctx.pathParam("id"));
			rs.denyPending(id);
			ctx.result(om.writeValueAsString(rs.getAllResolvedReimbursements()));
		}
	};
	public Handler handleRegisterReimbursement = (ctx) -> {

		if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("roleId")), "1")) {
			ctx.status(401);
			ctx.result("You must login as User to create Reimbursement");
		} else {
			RegisterReimbObject rro = om.readValue(ctx.body(), RegisterReimbObject.class);

			rs.registerReimbursement(rro.amount, rro.dateSubmitted, rro.dateResolved, rro.description, rro.reimbursementAuthor, rro.reimbursementResolver, rro.reimbursementType);
			ctx.status(201);
			ctx.result("Reimbursement Registered");
		}
	};

	public Handler handleGetAllPendingRequests = (ctx) -> {
		if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("roleId")), "1")) {
			ctx.status(401);
			ctx.result("You must login as User to view User pending requests");
		} else {
			int id = Integer.parseInt(ctx.pathParam("id"));
			ctx.result(om.writeValueAsString(rs.getAllPendingRequests(id)));
			ctx.status(200);
		}
	};

}
