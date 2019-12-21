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
import java.util.List;

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
    @Ignore
    public void testUpdateShop(){
        Shop shop=new Shop();

        shop.setShopId(36L);

        shop.setLastEditTime(new Date());
        shop.setShopAddr("updated addr");

        shop.setShopDesc("updated desc");

        int affectedNum=shopDao.updateShop(shop);
        assertEquals(1,affectedNum);
    }

    @Test
    @Ignore
    public void testQueryByShopId() {
        long shopId = 36;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println("areaId" + shop.getArea().getAreaId());
        System.out.println("areaName" + shop.getArea().getAreaName());
    }

    @Test
    public void testQueryShopList() {
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shopCondition.setOwner(owner);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
        int count = shopDao.queryShopCount(shopCondition);

        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(3L);
        shopCondition.setShopCategory(sc);
        shopList = shopDao.queryShopList(shopCondition, 0, 2);
        count = shopDao.queryShopCount(shopCondition);
        System.out.println("Results list size : " + shopList.size());
        System.out.println("Results count :" + count);
    }
}
