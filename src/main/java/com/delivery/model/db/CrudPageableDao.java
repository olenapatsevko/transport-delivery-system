package com.delivery.model.db;

import com.delivery.model.domain.Page;

import com.delivery.model.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface CrudPageableDao<E> extends CrudDao<E> {

    Optional<E> findById(Integer id);

    List<E> findAll(int page, int itemsPerPage);

    default Pageable<E> findAll(Page page) {
        return null;
    }


    void update(E entity);
}