package repo.user;

import entity.user.UserTranslate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTranslateRepo extends JpaRepository<UserTranslate, Long> {

    UserTranslate findByFullName(String name);

    UserTranslate findByUserId(Long id);
}
