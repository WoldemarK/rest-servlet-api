package org.servlets.project.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.servlets.project.model.Users;
import org.servlets.project.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/v1/users")
public class UserRestControllerV1 extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {
        userService = new UserService();
    }

    // TODO http://localhost:8080/api/v1/users?id=5
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        if (id == null) {
            List<Users> usersList = userService.getAllUsers();
            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(usersList.toString());
            printWriter.flush();
        } else {
            Users users = userService.getById(Long.valueOf(id));
            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(users.toString());
            printWriter.flush();
        }
    }

    // TODO http://localhost:8080/api/v1/users?id=15
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Users users = userService.getById(Long.valueOf(id));
        userService.deleteById(users.getId());
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.flush();
    }

    // TODO http://localhost:8080/api/v1/users
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Users users = new ObjectMapper().readValue(req.getReader(), Users.class);
        userService.save(users);
        resp.setContentType("application/json");
        PrintWriter printWriter = resp.getWriter();
        printWriter.flush();

    }

    // TODO http://localhost:8080/api/v1/users?id=22&name=some
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        Users users = userService.getById(Long.valueOf(id));
        users.setName(name);
        Long ids = users.getId();
        userService.updateUserById(users, ids);
        resp.setContentType("application/json");
        PrintWriter printWriter = resp.getWriter();
        printWriter.flush();
    }
}
