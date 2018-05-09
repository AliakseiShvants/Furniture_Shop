package service.shop;

import entity.shop.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.shop.StatusRepo;

import java.util.List;

@Service
public class StatusService {

    @Autowired
    private StatusRepo statusRepo;

    public Status findById(Long id){
        return statusRepo.findById(id).get();
    }

    public List<Status> getAll() {
        return statusRepo.findAll();
    }
}
