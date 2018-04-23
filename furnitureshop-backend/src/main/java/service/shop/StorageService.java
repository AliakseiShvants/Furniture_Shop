package service.shop;

import domain.shop.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.shop.StorageRepo;

import java.util.List;

@Service
@Transactional
public class StorageService {
    @Autowired
    private StorageRepo storageRepo;

    public Storage getStorageItemByProductId(Long id){
        return storageRepo.findByProduct_Id(id);
    }

    public boolean isItemExists(Long productId) {
        return storageRepo.existsByProduct_Id(productId);
    }

    public boolean isItemAvailable(Long productId, int quantity) {
        return storageRepo.findByProduct_Id(productId).getQuantity() >= quantity;
    }

    public List<Storage> getStorageItemsByCategory(String category) {
        return storageRepo.findAllByCategory(category);
    }

    public List<Storage> getStorageItemsByManager(Long managerId) {
        return storageRepo.findAllByManager_Id(managerId);
    }
}
