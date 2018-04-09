package repo;

import domain.shop.Requisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RequisiteRepo extends JpaRepository<Requisite, Long> {

    @Query(value = "SELECT r.requisite_id, zip, country, city, address\n" +
            "FROM furniture_shop.requisite r\n" +
            "JOIN furniture_shop.customer c ON r.requisite_id = c.requisite_id\n" +
            "WHERE c.customer_id = :id", nativeQuery = true)
    Requisite findRequisiteByCustomerId(@Param("id") Long id);

}
