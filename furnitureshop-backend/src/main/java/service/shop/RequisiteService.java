package service.shop;

import entity.shop.Requisite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.shop.RequisiteRepo;

@Service
@Transactional
public class RequisiteService {

    @Autowired
    private RequisiteRepo requisiteRepo;

    public Requisite getRequisiteByUserId(Long id){
        return requisiteRepo.findRequisiteByUserId(id);
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
