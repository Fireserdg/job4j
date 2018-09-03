package ru.job4j.cash;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Non blocking cash.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 03.09.2018.
 */
public class Cash {

    /**
     * Container for models.
     *
     */
    private final ConcurrentHashMap<Integer, Base> container;

    /**
     * Constructor.
     *
     */
    public Cash() {
        this.container = new ConcurrentHashMap<>();
    }

    /**
     * Add new model.
     *
     * @param model new model.
     * @return result: true or false.
     */
    public boolean add(Base model) {
        return container.putIfAbsent(model.getId(), model) == null;
    }

    /**
     * Update model.
     *
     * @param model model.
     * @return result: true or false.
     */
    public boolean update(Base model) {
        Base result = this.container.computeIfPresent(model.getId(), (key, oldModel) -> {
                    if (oldModel.getVersion() != model.getVersion()) {
                        throw new OptimisticException("Optimistic Exception");
                    }
                    model.increment();
                    return model;
                });
        return result != null;
    }

    /**
     * Delete model from container.
     *
     * @param model model.
     * @return result: true or false.
     */
    public boolean delete(Base model) {
        return container.remove(model.getId()) != null;
    }

    /**
     * Get model for id.
     *
     * @param id id of model.
     * @return model
     */
    public Base get(int id) {
        return this.container.get(id);
    }

    /**
     * Get size cash.
     *
     * @return size.
     */
    public int size() {
        return this.container.size();
    }
}