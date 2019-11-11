package guo.proj.javao2o.service;

import guo.proj.javao2o.BaseTest;
import guo.proj.javao2o.dto.ShopExecution;
import guo.proj.javao2o.entity.Area;
import guo.proj.javao2o.entity.PersonInfo;
import guo.proj.javao2o.entity.Shop;
import guo.proj.javao2o.entity.ShopCategory;
import guo.proj.javao2o.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setAdvice("In review");
        shop.setCreateTime(new Date());
        shop.setShopAddr("test4");
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setPhone("test4");
        shop.setShopDesc("test4");
        shop.setShopName("testShop4");
        File shopImg = new File("C:\\Users\\24965\\Pictures\\Saved Pictures\\cat.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution se = shopService.addShop(shop, is, shopImg.getName());
        assertEquals(se.getState(), ShopStateEnum.CHECK.getState());
    }
}
