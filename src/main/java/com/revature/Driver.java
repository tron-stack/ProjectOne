package com.revature;

import com.revature.controller.ReimbursementController;
import com.revature.controller.UserController;
import com.revature.dao.IReimbursementDao;
import com.revature.dao.IUserDao;
import com.revature.dao.ReimbursementDaoJDBC;
import com.revature.dao.UserDaoJDBC;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Driver {

    public static void main(String[] args){



        IUserDao iud = new UserDaoJDBC();
        IReimbursementDao ird = new ReimbursementDaoJDBC();
        UserService us = new UserService(iud);
        ReimbursementService rs = new ReimbursementService(ird);
        UserController uc = new UserController(us);
        ReimbursementController rc = new ReimbursementController(rs);
        Javalin server = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
        });

        server.before(ctx -> ctx.header("Access-Control-Allow-Credentials", "true"));
        server.before(ctx -> ctx.header("Access-Control-Expose-Headers", "*"));
        server.routes(()-> {
            path("users", () -> {
                post("/register", uc.handleRegister); // create new User

                post("/login", uc.handleLogin); // login

                get("/all", uc.handleAllUsers); // get all Users For Manager

                get("/{username}", uc.handleGetUserByUsername); // get current logged in by username

                put("/logout", uc.handleLogout); // logout

                put("/", uc.handleUpdateUser); // updates USER information

                get("/",uc.handleGetCurrentUser); // gets User by session for User
            });
        });
        server.routes(()-> {
            path("reimbursements", () -> {
                post("/register", rc.handleRegisterReimbursement); // Create Reimbursement for EMPLOYEE

                get("/", rc.handleAllReimbursements); // get all Reimbursements for MANAGER

                get("/resolved", rc.handleAllResolvedReimbursements); // get all Resolved Reimbursements for MANAGER

                get("/pending", rc.handleAllPendingReimbursements); //get all pending reimbursements for MANAGER

                get("/all/{id}", rc.handleGetAllRequests);  //get Reimbursements for each employee by their employee ID for EMPLOYEE

                get("/status/{id}&{status}", rc.handleGetAllRequestsByStatus); // Get All Reimbursements based on their status (1.pending,2.approve,3.denied) for EMPLOYEE

                get("/{id}", rc.handleGetReimbursementsById); //get Reimbursements for MANAGER to get specific user reimbursements

                put("/approve/{id}", rc.handleApproveReimbursementsById); // approve Reimbursements by Reimbursement ID for MANAGER

                put("/deny/{id}", rc.handleDenyReimbursementsById); //Deny Reimbursement by R.ID for MANAGER
            });
        });
        server.start(8000);
    }
}
