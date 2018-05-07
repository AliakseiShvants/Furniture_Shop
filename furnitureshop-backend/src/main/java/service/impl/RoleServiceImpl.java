package service.impl;

import entity.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.user.RoleRepo;
import service.user.RoleService;

import java.util.List;

/**
 * A implementation of {@link RoleService} interface
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Role findRoleByTitle(String title) {
        return roleRepo.findRoleByTitle(title);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepo.findById(id).get();
    }
}
