package service.shop;

import domain.shop.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.shop.StorageRepo;

@Service
@Transactional
public class StorageService {
    @Autowired
    private StorageRepo storageRepo;

    public Storage getStorageByProductId(Long id){
        return storageRepo.findByProduct_Id(id);
    }
}
