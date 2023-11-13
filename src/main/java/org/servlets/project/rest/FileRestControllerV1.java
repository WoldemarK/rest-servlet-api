package org.servlets.project.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.servlets.project.model.File;
import org.servlets.project.model.Users;
import org.servlets.project.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/v1/files")
public class FileRestControllerV1 extends HttpServlet {
    private FileService fileService;

    @Override
    public void init() {
        fileService = new FileService();
    }

    // TODO http://localhost:8080/api/v1/files?id=1
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if (id == null) {
            List<File> files = fileService.getAllFile();
            resp.setContentType("application/json");
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(files.toString());
            printWriter.flush();
        } else {
            File file = fileService.getByIdFile(Long.valueOf(id));
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(file.getFilePath());
            printWriter.flush();
        }
    }

    // TODO http://localhost:8080/api/v1/files?id=15
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        File file = fileService.getByIdFile(Long.valueOf(id));
        fileService.deleteById(file.getId());
        resp.setContentType("application/json");
        PrintWriter printWriter = resp.getWriter();
        printWriter.flush();
    }

    // TODO http://localhost:8080/api/v1/files
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File file = new ObjectMapper().readValue(req.getReader(), File.class);
        fileService.createNewFile(file);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.flush();
    }

    // TODO http://localhost:8080/api/v1/files?id=22&name=some
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String filePath = req.getParameter("name");
        File fileId = fileService.getByIdFile(Long.valueOf(id));
        fileId.setFilePath(filePath);
        Long ids = fileId.getId();
        fileService.updateFileById(fileId,ids);
        resp.setContentType("application/json");
        PrintWriter printWriter = resp.getWriter();
        printWriter.flush();
    }
}
