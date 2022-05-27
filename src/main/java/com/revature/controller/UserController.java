package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginObject;
import com.revature.models.User;
import com.revature.services.UserService;
import io.javalin.http.Handler;

import java.util.Objects;

public class UserController {
	private UserService us;
	private ObjectMapper om;
	public UserController(UserService us) {
		this.us = us;
		this.om = new ObjectMapper();
	}
	public Handler handleRegister = (ctx) -> {
		User u = om.readValue(ctx.body(), User.class);
		//System.out.println(ro);
				us.registerUser(u);
				ctx.status(201);
				ctx.result("Create user");
				ctx.result(om.writeValueAsString(us.getUserByUsername(u.getUserName())));
	};
	public Handler handleLogin = (ctx) -> {
		LoginObject lo = om.readValue(ctx.body(), LoginObject.class);
		User u = us.loginUser(lo.userName, lo.password);
		if(u == null){
			ctx.status(403);
			ctx.result("Username or password was incorrect");
		} else {
			//We could also, if the user is logged in successfully, set up a session for them
			ctx.req.getSession().setAttribute("loggedIn", ""+u.getUserName());
			ctx.req.getSession().setAttribute("roleId", ""+u.getUserRole());
			ctx.req.getSession().setAttribute("userId", ""+u.getUserId());
			ctx.result(om.writeValueAsString(u));
		}
	};


	public Handler handleUpdateUser = (ctx) -> {




				User user = om.readValue(ctx.body(), User.class);
				us.updateUser(user);
				ctx.status(201);
				ctx.result("User updated");



	};
	public Handler handleAllUsers = (ctx) -> {
		if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("roleId")), "2")) {
			ctx.status(401);
			ctx.result("You must login as manager to view User data");
		} else {
			us.getAllUser();
			ctx.result(om.writeValueAsString(us.getAllUser()));
		}
	};
	public Handler handleGetUserByUsername = (ctx) -> {
		if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("roleId")), "2")) {
			ctx.status(401);
			ctx.result("You must login as manager to view User data");
		} else {
			String username = ctx.pathParam("username");
			us.getUserByUsername(username);
			ctx.result(om.writeValueAsString(us.getUserByUsername(username)));
		}
	};
	public Handler handleGetCurrentUser = (ctx) -> {
		if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("roleId")), "1")) {
			ctx.status(401);
			ctx.result("You must login as User to view User data");
		} else {
			int id = Integer.parseInt((String) ctx.req.getSession().getAttribute("userId"));
			ctx.result(om.writeValueAsString(us.getUserById(id)));
			ctx.status(200);
		}
	};
	public Handler handleLogout = (ctx) -> {
		ctx.req.getSession().invalidate();
		ctx.result("You logged out");
	};
}