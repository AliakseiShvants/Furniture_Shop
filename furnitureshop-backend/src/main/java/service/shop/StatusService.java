package service.shop;

import domain.shop.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.shop.StatusRepo;

import java.util.List;

@Service
public class StatusService {

    @Autowired
    private StatusRepo statusRepo;

    public Status getStatus(String status){
        return statusRepo.findByStatus(status);
    }

    public List<Status> getAll() {
        return statusRepo.findAll();
    }
}
