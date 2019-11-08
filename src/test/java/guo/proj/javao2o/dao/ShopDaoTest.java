package guo.proj.javao2o.dao;

import guo.proj.javao2o.BaseTest;
import guo.proj.javao2o.entity.Area;
import guo.proj.javao2o.entity.PersonInfo;
import guo.proj.javao2o.entity.Shop;
import guo.proj.javao2o.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;
    @Test
    @Ignore
    public void testInsertShop(){
        Shop shop=new Shop();
        PersonInfo owner=new PersonInfo();
        Area area=new Area();
        ShopCategory shopCategory=new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setAdvice("In review");
        shop.setCreateTime(new Date());
        shop.setShopAddr("test");
        shop.setEnableStatus(1);
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setShopDesc("test");
        shop.setShopName("testShop");
        int affectedNum=shopDao.insertShop(shop);
        assertEquals(1,affectedNum);
    }
    @Test
    public void testUpdateShop(){
        Shop shop=new Shop();

        shop.setShopId(36L);

        shop.setLastEditTime(new Date());
        shop.setShopAddr("updated addr");

        shop.setShopDesc("updated desc");

        int affectedNum=shopDao.updateShop(shop);
        assertEquals(1,affectedNum);
    }
}
