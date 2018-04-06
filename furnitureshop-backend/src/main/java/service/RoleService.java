package service;

import domain.customer.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.RoleRepo;

import java.util.List;

/**
 * <p>Service class for {@link Role} entity for working with repository interface
 */
@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public Role getRoleByTitle(String title){
        return roleRepo.findRoleByTitle(title);
    }

    public List<Role> getAll(){
        return (List<Role>) roleRepo.findAll();
    }
}
