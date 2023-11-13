package org.servlets.project.repository.aipRepository;

import org.hibernate.Session;
import org.servlets.project.model.Event;
import org.servlets.project.repository.EventRepository;
import java.util.List;

import static org.servlets.project.util.HibernateSessionFactoryUtil.session;

public class EventRepositoryImpl implements EventRepository {
    @Override
    public Event save(Event target) {
        try (Session session = session()) {
            session.beginTransaction();
            session.merge(target);
            session.getTransaction().commit();
            return target;
        }
    }
    @Override
    public Event update(Event target, Long id) {
        try (Session session = session()) {
            String request = "update Event set Event.users.id=:id, Event.file.id=:id where id=:id";
            session.createQuery(request)
                    .setParameter("id", id)
                    .setParameter("id", target.getUsers().getId())
                    .setParameter("id", target.getFile().getId())
                    .executeUpdate();
            session.getTransaction().commit();
            return target;
        }
    }
    @Override
    public Event getId(Long id) {
        try (Session session = session()) {
            return session.get(Event.class, id);
        }
    }

    @Override
    public List<Event> getAll() {
        try (Session session = session()) {
            return session.createQuery("FROM Event ", Event.class).list();
        }
    }
    @Override
    public boolean deleteById(Long id) {
        try (Session session = session()) {
            session.beginTransaction();
            session.createQuery("delete from Event where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        }
    }
}
