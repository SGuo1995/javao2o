package guo.proj.javao2o.service;

import guo.proj.javao2o.BaseTest;
import guo.proj.javao2o.entity.Area;
import guo.proj.javao2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopInitialTest extends BaseTest {
    @Autowired
    private ShopCategoryService shopCategoryService;

    @Test
    public void testCategory() {
        List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
        assertEquals(1, shopCategoryList.size());
        System.out.println(shopCategoryList.get(0).getShopCategoryName());
    }
}
