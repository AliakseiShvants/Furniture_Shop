package config;

import domain.product.Image;
import domain.product.Manufacturer;
import domain.product.Product;
import domain.shop.*;
import domain.user.Role;
import domain.user.User;
import dto.product.ImageDTO;
import dto.product.ManufacturerDTO;
import dto.product.ProductDTO;
import dto.shop.*;
import dto.user.RoleDTO;
import dto.user.UserDTO;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * A Java Bean mapper that recursively copies data from one object to another.
 * Supports simple property mapping, complex type mapping, bi-directional mapping, implicit-explicit mapping, as
 * well as recursive mapping. This includes mapping collection attributes that also need mapping at the element level.
 */
@Configuration
public class MappingConfig {

    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(Role.class, RoleDTO.class);
                mapping(User.class, UserDTO.class);
                mapping(Requisite.class, RequisiteDTO.class);
                mapping(Order.class, OrderDTO.class)
                        .fields("manager.fullName", "manager")
                        .fields("status.status", "status");
                mapping(OrderItem.class, OrderItemDTO.class);
                mapping(Manufacturer.class, ManufacturerDTO.class);
                mapping(Product.class, ProductDTO.class);
                mapping(BasketItem.class, BasketItemDTO.class);
                mapping(Image.class, ImageDTO.class);
                mapping(Storage.class, StorageItemDTO.class);
            }
        };
    }

    @Bean
    public DozerBeanMapper beanMapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.addMapping(beanMappingBuilder());

        List<String> customConverters = new ArrayList();
        customConverters.add("dozerJdk8Converters.xml");
        dozerBeanMapper.setMappingFiles(customConverters);

        return dozerBeanMapper;
    }
}
