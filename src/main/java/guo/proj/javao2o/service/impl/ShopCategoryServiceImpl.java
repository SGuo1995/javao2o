package guo.proj.javao2o.service.impl;

import guo.proj.javao2o.dao.ShopCategoryDao;
import guo.proj.javao2o.entity.Shop;
import guo.proj.javao2o.entity.ShopCategory;
import guo.proj.javao2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {

        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }
}
