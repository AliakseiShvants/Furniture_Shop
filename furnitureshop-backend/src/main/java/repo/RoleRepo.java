package repo;

import domain.customer.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Access interface to {@link Role} entity in database
 */
@Repository
@Transactional
public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findRoleByTitle(String title);

}
