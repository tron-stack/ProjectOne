package com.revature;

import com.revature.dao.IUserDao;
import com.revature.dao.UserDaoJDBC;
import com.revature.models.User;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Driver {

    public static void main(String[] args){

        IUserDao icd = new UserDaoJDBC();

        Javalin server = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            // config.addStaticFiles("/public", Location.CLASSPATH);
        });

        User user = new User(1,"cp","pass","chime","palden","cp@gmail.com",1);
        icd.updateUser(user);
        server.start(8000);
    }
}
