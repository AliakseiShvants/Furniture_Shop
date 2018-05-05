package repo.shop;

import entity.shop.Requisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RequisiteRepo extends JpaRepository<Requisite, Long> {

    @Query(value = "SELECT r.requisite_id, zip, country, city, address\n" +
            "FROM furniture_shop.requisite r\n" +
            "JOIN furniture_shop.users u ON r.requisite_id = u.requisite_id\n" +
            "WHERE u.user_id = :id", nativeQuery = true)
    Requisite findRequisiteByUserId(@Param("id") Long id);

}
