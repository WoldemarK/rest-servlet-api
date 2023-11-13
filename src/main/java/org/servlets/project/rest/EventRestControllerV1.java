package org.servlets.project.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.servlets.project.model.Event;
import org.servlets.project.model.Users;
import org.servlets.project.service.EventService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/v1/events")
public class EventRestControllerV1 extends HttpServlet {
    private EventService eventService;
    @Override
    public void init() {
        eventService = new EventService();
    }

    // TODO http://localhost:8080/api/v1/events/1
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if (id == null) {
            List<Event> eventList = eventService.getALLEvent();
            resp.setContentType("application/json");
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(eventList.toString());
            printWriter.flush();
        } else {
            Event event = eventService.getById(Long.valueOf(id));
            resp.setContentType("application/json");
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(String.valueOf(event));
            printWriter.flush();
        }
    }

    // TODO http://localhost:8080/api/v1/events?id=1
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Event event = eventService.getById(Long.valueOf(id));
        eventService.deleteById(event.getId());
        resp.setContentType("application/json");
        PrintWriter printWriter = resp.getWriter();
        printWriter.flush();
    }

    // TODO http://localhost:8080/api/v1/events
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Event event = new ObjectMapper().readValue(req.getReader(), Event.class);
        eventService.createEvents(event);
        resp.setContentType("application/json");
        PrintWriter printWriter = resp.getWriter();
        printWriter.flush();
    }

    // TODO http://localhost:8080/api/v1/events?id=1
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Event event = new ObjectMapper().readValue(req.getReader(), Event.class);
        Long id = event.getId();
        eventService.updateEventById(event, id);
        resp.setContentType("application/json");
        PrintWriter printWriter = resp.getWriter();
        printWriter.flush();
    }
}
