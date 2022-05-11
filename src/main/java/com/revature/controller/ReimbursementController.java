package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
			ctx.result("You must login as manager to view User data");
		} else {
			rs.getAllReimbursements();
			om.writeValueAsString(rs.getAllReimbursements());
		}
	};
	public Handler handleAllResolvedReimbursements = ctx -> {
		if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("roleId")), "2")) {
			ctx.status(401);
			ctx.result("You must login as manager to view User data");
		} else {
			rs.getAllResolvedReimbursements();
			om.writeValueAsString(rs.getAllResolvedReimbursements());
		}
	};
	public Handler handleAllPendingReimbursements = ctx-> {
		if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("roleId")), "2")) {
			ctx.status(401);
			ctx.result("You must login as manager to view User data");
		} else {
			rs.getAllPendingReimbursement();
			om.writeValueAsString(rs.getAllReimbursements());
		}
	};

	public Handler handleGetReimbursementsById = ctx-> {
		if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("roleId")), "2")) {
			ctx.status(401);
			ctx.result("You must login as manager to view User data");
		} else {
			int id = Integer.parseInt(ctx.pathParam("id"));
			rs.getAllReimbursementsById(id);
			om.writeValueAsString(rs.getAllReimbursementsById(id));
		}
	};

	public Handler handleApproveReimbursementsById = ctx-> {
		if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("roleId")), "2")) {
			ctx.status(401);
			ctx.result("You must login as manager to view User data");
		} else {
			int id = Integer.parseInt(ctx.pathParam("id"));
			rs.approvePending(id);
			om.writeValueAsString(rs.getAllResolvedReimbursements());
		}
	};

	public Handler handleDenyReimbursementsById = ctx-> {
		if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("roleId")), "2")) {
			ctx.status(401);
			ctx.result("You must login as manager to view User data");
		} else {
			int id = Integer.parseInt(ctx.pathParam("id"));
			rs.denyPending(id);
			om.writeValueAsString(rs.getAllResolvedReimbursements());
		}
	};

}
