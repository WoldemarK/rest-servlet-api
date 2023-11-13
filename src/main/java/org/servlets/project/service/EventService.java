package org.servlets.project.service;

import org.servlets.project.model.Event;
import org.servlets.project.repository.EventRepository;
import org.servlets.project.repository.aipRepository.EventRepositoryImpl;

import java.util.List;

public class EventService {

    private final EventRepository repository;
    public EventService() {
        repository = new EventRepositoryImpl();
    }
    public List<Event> getALLEvent() {
        return repository.getAll();
    }
    public Event getById(Long id) {
        return repository.getId(id);
    }
    public boolean deleteById(Long id) {
        repository.deleteById(id);
        return true;
    }
    public Event createEvents(Event event){
        return repository.save(event);
    }
    public Event updateEventById(Event event, Long id){
        return repository.update(event,id);
    }
}
