package config;

import domain.product.Category;
import domain.product.Image;
import domain.product.Product;
import domain.shop.Basket;
import domain.shop.Order;
import domain.shop.OrderDetails;
import domain.shop.Requisite;
import domain.user.Role;
import domain.user.User;
import dto.product.CategoryDTO;
import dto.product.ImageDTO;
import dto.product.ProductDTO;
import dto.shop.BasketDTO;
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
                mapping(Order.class, OrderDTO.class);
                mapping(OrderDetails.class, OrderDetailsDTO.class);
                mapping(Product.class, ProductDTO.class)
                        .fields("category.title", "category")
                        .fields("manufacturer.title", "manufacturer");
                mapping(Basket.class, BasketDTO.class);
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
