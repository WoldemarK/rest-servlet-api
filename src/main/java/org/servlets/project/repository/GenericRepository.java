package org.servlets.project.repository;

import java.util.List;

public interface GenericRepository <T, Long>{
    T save(T target);

    T update(T target, Long id);

    T getId(Long id);

    List<T> getAll();

    boolean deleteById(Long id);
}
