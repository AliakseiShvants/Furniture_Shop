package service.product;

import entity.user.UserTranslate;

public interface UserTranslateService {

    UserTranslate findByUserId(Long id);

    UserTranslate findByFullName(String name);
}
