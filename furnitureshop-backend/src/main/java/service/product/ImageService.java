package service.product;

import domain.product.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.product.ImageRepo;

import java.util.List;

@Service
@Transactional
public class ImageService {

    @Autowired
    private ImageRepo imageRepo;

    public List<Image> getProductImages(Long id) {
        return imageRepo.findAllByProduct_Id(id);
    }

    public Image getImageByProductId(Long id) {
        return imageRepo.findByProduct_Id(id);
    }
}
