package repo.product;

import domain.product.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ImageRepo extends JpaRepository<Image, Long>{

    List<Image> findAllByProduct_Id(Long id);

    Image findByProduct_Id(Long id);
}
