package repo.shop;

import domain.shop.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StatusRepo extends JpaRepository<Status, Long> {

    Status findByStatus(String status);
}
