package com.mjc.school.controllertest;

import java.util.List;

public interface BaseController<T, R, K> {

    List<R> readAll();

    R readById(K id);

    R create(T createRequest);

    R update(T updateRequest);

    boolean deleteById(K id);
}
