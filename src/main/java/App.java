import Dao.Sql2oUser;
import exceptions.ApiException;
import models.User;

import static spark.Spark.*;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Sql2oUser userDao = new Sql2oUser();
        Gson gson = new Gson();
        post("/logIn", "application/json", (request, response) -> {
            User user = gson.fromJson(request.body(), User.class);
            User foundUser = userDao.getUserByEmail(user.getEmail());
            String res = "";
            if (foundUser == null) {
                throw new ApiException(404, String.format("User not found"));
            }
            if (foundUser.checkPassword(user.getPassword())) {
                res = "Successful";
            } else {
                throw new ApiException(400, String.format("Invalid password"));
            }
            response.status(201);
            return gson.toJson(res);
        });

        post("/signUp", "application/json", (request, response) -> {
            if (!request.body().contains("email") || !request.body().contains("password") || !request.body().contains("userName")) {
                throw new ApiException(400, String.format("Either email or password or userName missing"));
            }
            User user = gson.fromJson(request.body(), User.class);
            if (userDao.getUserByEmail(user.getEmail()).getId() > 0) {
                throw new ApiException(400, String.format("Email already exists "));
            }
            userDao.saveUser(user);
            response.status(201);
            return gson.toJson(user);
        });

        get("/users", "application/json", (request, response) -> {
            response.status(201);
            return gson.toJson(userDao.getAllUsers());
        });

        get("/users/:id", "application/json", (request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            User user = userDao.getUserById(id);
            if (user == null) {
                throw new ApiException(404, String.format("User not found"));
            }
            response.status(201);
            return gson.toJson(user);
        });

        put("/users/:id", "application/json", (request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            User user;
            if (request.body().contains("typingProficiency")) {
                User user1 = gson.fromJson(request.body(), User.class);
                userDao.setTypingProficiency(id, user1.getTypingProficiency());
                user = user1;
            } else if (request.body().contains("wordsPerMinute")) {
                User user1 = gson.fromJson(request.body(), User.class);
                userDao.wordsPerMinute(id, user1.getWordsPerMinute());
                user = user1;
            } else {
                throw new ApiException(400, String.format("Invalid parameters"));
            }
            user = userDao.getUserById(id);
            if (user == null) {
                throw new ApiException(404, String.format("User not found"));
            }
            response.status(201);
            return gson.toJson(user);
        });

        delete("/users", "application/json", (request, response) -> {
            userDao.clearAllUsers();
            return gson.toJson("successful");
        });

        after(((request, response) -> {
            response.type("application/json");
        }));

        exception(ApiException.class, (exc, req, res) -> {
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });
    }
}
