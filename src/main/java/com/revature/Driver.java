package com.revature;

import com.revature.controller.UserController;
import com.revature.dao.IUserDao;
import com.revature.dao.UserDaoJDBC;
import com.revature.models.User;
import com.revature.services.UserService;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public class Driver {

    public static void main(String[] args){



        IUserDao iud = new UserDaoJDBC();
        UserService us = new UserService(iud);
        UserController uc = new UserController(us);

        Javalin server = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            // config.addStaticFiles("/public", Location.CLASSPATH);
        });
        server.routes(()-> {
            path("users", () -> {
                post("/register", uc.handleRegister);
                post("/login", uc.handleLogin);
            });
        });
        // checking update
        // User user = new User("cpalden","password","chime","palden","cpalden@gmail.com",2);
        // icd.createUser(user);
        // checking delete
        //iud.deleteUser(3);
        server.start(8000);
    }
}
