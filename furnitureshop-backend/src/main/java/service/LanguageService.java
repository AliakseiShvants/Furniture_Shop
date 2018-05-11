package service;

import entity.Language;

/**
 * A service interface for {@link Language} entity for working with repository interface
 */
public interface LanguageService {

    /**
     * A method that returns a {@link Language} entity with name equals @param.
     * @param name field of {@link Language} entity
     * @return a {@link Language} entity
     */
    Language findByName(String name);
}
