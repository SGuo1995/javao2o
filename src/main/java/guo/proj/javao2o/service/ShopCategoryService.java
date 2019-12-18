package guo.proj.javao2o.service;

import guo.proj.javao2o.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryService {
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
