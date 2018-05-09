package service.shop;

import entity.product.Product;
import entity.shop.Storage;
import entity.user.User;
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

    public List<Storage> findStorageListByCategory(String category) {
        return storageRepo.findAllByCategory(category);
    }

    public List<Storage> getStorageItemsByManager(Long managerId) {
        return storageRepo.findAllByManager_Id(managerId);
    }

    public Storage addStorageItem(User manager, Product product) {
        Storage storage = new Storage(product, manager);
        return storageRepo.save(storage);
    }

    public Double findPriceByProductId(Long id) {
        return storageRepo.findPriceByProduct_Id(id);
    }
}
