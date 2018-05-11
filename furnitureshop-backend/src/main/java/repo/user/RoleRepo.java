package repo.user;

import entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Access interface to {@link Role} entity in database.
 */
@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findRoleByTitle(String title);
}
