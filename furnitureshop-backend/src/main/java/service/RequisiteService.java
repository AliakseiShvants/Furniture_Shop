package service;

import domain.shop.Requisite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.RequisiteRepo;

@Service
@Transactional
public class RequisiteService {

    @Autowired
    private RequisiteRepo requisiteRepo;

    public Requisite getRequisiteByCustomerId(Long id){
        return requisiteRepo.findRequisiteByCustomerId(id);
    }

    public Requisite updateRequisite(Requisite requisite){
        return requisiteRepo.saveAndFlush(requisite);
    }

    public Requisite addRequisite(Requisite requisite) {
        return requisiteRepo.save(requisite);
    }

    public boolean isRequisiteExist(Long id) {
        return requisiteRepo.existsById(id);
    }
}
