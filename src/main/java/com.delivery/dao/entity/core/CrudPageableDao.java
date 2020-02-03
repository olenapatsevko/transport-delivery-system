package com.delivery.dao.entity.core;


import com.delivery.dao.Page;
import com.delivery.dao.impl.Pageable;

import java.util.List;

public interface CrudPageableDao<E> extends CrudDao<E> {

    List<E> findAll(int page, int itemsPerPage);

    default Pageable<E> findAll(Page page) {
        return null;
    }


    void update(E entity);
}