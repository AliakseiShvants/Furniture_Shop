package repo.user;

import domain.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Access interface to {@link Role} entity in database
 */
@Repository
@Transactional
public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findRoleByTitle(String title);

}
