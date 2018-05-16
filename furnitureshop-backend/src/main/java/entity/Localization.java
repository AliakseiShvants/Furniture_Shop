package entity;

import dto.shop.ShopDTO;
import entity.product.ManufacturerTranslate;
import entity.product.ProductTranslate;
import org.springframework.beans.factory.annotation.Autowired;
import service.product.ManufacturerTranslateService;
import service.product.ProductTranslateService;

import java.util.List;

public class Localization {

    @Autowired
    private ProductTranslateService productTranslateService;

    @Autowired
    private ManufacturerTranslateService manufacturerTranslateService;

    private static volatile Localization localization;

    private Localization() {
    }

    public static Localization getLocalization(){
        if (localization == null){
            synchronized (Localization.class){
                if (localization == null){
                    localization = new Localization();
                }
            }
        }
        return localization;
    }

//    public List<? extends ShopDTO> makeLocalList(Language language, List<? extends ShopDTO> list) {
//        if (language.getId() > 1){
//            list.forEach(item -> {
//                ManufacturerTranslate manufacturerTranslate = manufacturerTranslateService
//                        .findByManufacturerId(item.getProduct().getManufacturer().getId());
//                ProductTranslate productTranslate = productTranslateService.findByProductId(item.getProduct().getId());
//                item.getProduct().setName(productTranslate.getName());
//                item.getProduct().setDescription(productTranslate.getDescription());
//                item.getProduct().getManufacturer().setTitle(manufacturerTranslate.getTitle());
//            });
//        }
//        return list;
//    }
}
