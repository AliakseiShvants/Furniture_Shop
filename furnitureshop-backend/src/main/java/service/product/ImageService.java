package service.product;

import domain.product.Image;
import domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.product.ImageRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ImageService {

    @Autowired
    private ImageRepo imageRepo;

    public List<Image> getProductImages(Long id) {
        return imageRepo.findAllByProduct_Id(id);
    }

    public String getProductImageUrl(Long id) {
        Image image = imageRepo.findByProduct_Id(id);
        return image != null ? image.getUrl() : "";
    }

    public Image addImage(Product product, String url) {
        Image image = new Image(product, url);
        return imageRepo.save(image);
    }
}
