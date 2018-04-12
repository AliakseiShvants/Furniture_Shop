package config;

import domain.product.Category;
import domain.shop.Order;
import domain.shop.Requisite;
import domain.user.Role;
import domain.user.User;
import dto.product.CategoryDTO;
import dto.shop.OrderDTO;
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
                mapping(Category.class, CategoryDTO.class);
                mapping(User.class, UserDTO.class);
                mapping(Requisite.class, RequisiteDTO.class);
                mapping(Order.class, OrderDTO.class);
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
