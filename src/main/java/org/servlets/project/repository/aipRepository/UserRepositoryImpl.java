package org.servlets.project.repository.aipRepository;

import org.hibernate.Session;
import org.servlets.project.model.Users;
import org.servlets.project.repository.UserRepository;

import java.util.List;

import static org.servlets.project.util.HibernateSessionFactoryUtil.*;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public List<Users> getAll() {
        try (Session session = session()) {
            return session.createQuery("from Users ", Users.class).list();
        }
    }

    public Users save(Users target) {
        try (Session session = session()) {
            session.beginTransaction();
            target = Users.builder()
                    .name(target.getName())
                    .build();
            Users merge = (Users) session.merge(target);
            session.getTransaction().commit();
            return merge;
        }
    }

    @Override
    public Users update(Users target, Long id) {
        try (Session session = session()) {
            session.beginTransaction();
            String request = "update Users set name=:name where id =:id";
            session.createQuery(request)
                    .setParameter("id", id)
                    .setParameter("name", target.getName())
                            .executeUpdate();
            session.getTransaction().commit();
            return target;
        }
    }

    @Override
    public Users getId(Long id) {
        try (Session session = session()) {
            return session.get(Users.class, id);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Session session = session()) {
            session.beginTransaction();
            session.createQuery("delete from Users where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        }
    }
}
