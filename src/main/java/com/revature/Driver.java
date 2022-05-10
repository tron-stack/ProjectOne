package com.revature;

import com.revature.dao.IUserDao;
import com.revature.dao.UserDaoJDBC;
import com.revature.models.User;
import io.javalin.Javalin;

public class Driver {

    public static void main(String[] args){

        IUserDao icd = new UserDaoJDBC();

        Javalin server = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            // config.addStaticFiles("/public", Location.CLASSPATH);
        });
        // checking update
        // User user = new User("cpalden","password","chime","palden","cpalden@gmail.com",2);
        // icd.createUser(user);
        // checking delete
        icd.deleteUser(3);
        server.start(8000);
    }
}
