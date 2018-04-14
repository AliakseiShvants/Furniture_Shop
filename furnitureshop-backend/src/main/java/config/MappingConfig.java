package config;

import domain.product.Image;
import domain.product.Manufacturer;
import domain.product.Product;
import domain.shop.BasketItem;
import domain.shop.Order;
import domain.shop.OrderDetails;
import domain.shop.Requisite;
import domain.user.Role;
import domain.user.User;
import dto.product.ImageDTO;
import dto.product.ManufacturerDTO;
import dto.product.ProductDTO;
import dto.shop.BasketItemDTO;
import dto.shop.OrderDTO;
import dto.shop.OrderDetailsDTO;
import dto.shop.RequisiteDTO;
import dto.user.RoleDTO;
import dto.user.UserDTO;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfig {

    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(Role.class, RoleDTO.class);
//                mapping(Category.class, CategoryDTO.class);
                mapping(User.class, UserDTO.class);
                mapping(Requisite.class, RequisiteDTO.class);
                mapping(Order.class, OrderDTO.class)
                        .fields("manager.fullName", "manager")
                        .fields("status.status", "status");
                mapping(OrderDetails.class, OrderDetailsDTO.class);
                mapping(Manufacturer.class, ManufacturerDTO.class);
                mapping(Product.class, ProductDTO.class)
                        .fields("category.title", "category")
                        .fields("manufacturer.requisite.country", "manufacturer");
                mapping(BasketItem.class, BasketItemDTO.class);
                mapping(Image.class, ImageDTO.class);
            }
        };
    }

    @Bean
    public DozerBeanMapper beanMapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.addMapping(beanMappingBuilder());
        return dozerBeanMapper;
    }
}
