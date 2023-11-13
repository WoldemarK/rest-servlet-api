package org.servlets.project.repository.aipRepository;

import org.hibernate.Session;
import org.servlets.project.model.File;
import org.servlets.project.repository.FileRepository;

import java.util.List;

import static org.servlets.project.util.HibernateSessionFactoryUtil.session;

public class FileRepositoryImpl implements FileRepository {
    @Override
    public File save(File target) {
        try (Session session = session()) {
            session.beginTransaction();
            target = File.builder()
                    .filePath(target.getFilePath())
                    .build();
            File merge = (File) session.merge(target);
            session.getTransaction().commit();
            return merge;
        }
    }

    @Override
    public File update(File target, Long id) {
        try (Session session = session()) {
            session.beginTransaction();
            String request = "update File set filePath=:filePath where id=:id";
            session.createQuery(request)
                    .setParameter("id", id)
                    .setParameter("filePath", target.getFilePath())
                    .executeUpdate();
            session.getTransaction().commit();
            return target;
        }
    }

    @Override
    public File getId(Long id) {
        try (Session session = session()) {
            return session.get(File.class, id);
        }
    }

    @Override
    public List<File> getAll() {
        try (Session session = session()) {
            return session.createQuery("FROM File ", File.class).list();
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Session session = session()) {
            session.beginTransaction();
            session.createQuery("delete from File where id=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        }
    }
}
