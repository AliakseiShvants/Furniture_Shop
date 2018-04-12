package service.user;

import domain.user.Role;
import dto.user.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.user.RoleRepo;

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
        return roleRepo.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepo.findById(id).get();
    }
}
