package service.user;

import entity.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.user.RoleRepo;

import java.util.List;

/**
 * A service interface for {@link Role} entity for working with repository interface
 */
public interface RoleService {

    /**
     * A method that returns a {@link Role} entity with title equals @param.
     * @param title field of {@link Role} entity
     * @return a {@link Role} entity
     */
    Role findRoleByTitle(String title);

    /**
     * A method that returns all {@link Role} entities.
     * @return a list of {@link Role} entities
     */
    List<Role> findAllRoles();

    /**
     * A method that returns a {@link Role} entity with title equals @param.
     * @param id field of {@link Role} entity
     * @return a {@link Role} entity
     */
    Role getRoleById(Long id);
}
