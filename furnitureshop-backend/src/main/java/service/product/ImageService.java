package service.product;

import domain.product.Image;
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

    public List<String> getProductImagesUrl(Long id) {
        return imageRepo.findAllByProduct_Id(id).stream()
                .map(Image::getUrl)
                .collect(Collectors.toList());
    }
}