package repo;

import entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Access interface to {@link Language} entity in database.
 */
@Repository
public interface LanguageRepo extends JpaRepository<Language, Long> {

    Language findByName(String name);
}
