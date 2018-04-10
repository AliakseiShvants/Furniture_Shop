package controller;

import domain.customer.Role;
import domain.shop.Requisite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repo.RequisiteRepo;
import repo.RoleRepo;

@RestController
public class TestController {

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private RequisiteRepo requisiteRepo;

    @RequestMapping("/role/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Long id){
        Role role = roleRepo.getOne(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @RequestMapping("/requisite/{id}")
    public ResponseEntity<Requisite> getRequisite(@PathVariable Long id){
        Requisite requisite = requisiteRepo.findById(id).get();
        return new ResponseEntity<>(requisite, HttpStatus.OK);
    }


}
