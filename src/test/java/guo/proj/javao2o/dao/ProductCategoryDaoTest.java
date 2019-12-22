package guo.proj.javao2o.dao;

import guo.proj.javao2o.BaseTest;
import guo.proj.javao2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test

    public void testQueryByShopId() throws Exception {
        long shopId = 36;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId = shopId);
        System.out.println("The amount of product categories of this shop is : " + productCategoryList.size());
    }

    @Test
    public void testDeleteProductCategory() throws Exception {
        long shopId = 48L;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        for (ProductCategory pc : productCategoryList) {
            int effectedNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), shopId);
            assertEquals(1, effectedNum);
        }
    }

    @Test
    public void testABatchInsertProductCategory() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryName("Product Category 4");
        productCategory.setPriority(1);
        productCategory.setCreateTime(new Date());
        productCategory.setShopId(48L);
        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("Product Category 5");
        productCategory2.setPriority(2);
        productCategory2.setCreateTime(new Date());
        productCategory2.setShopId(48L);
        List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
        productCategoryList.add(productCategory);
        productCategoryList.add(productCategory2);
        int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
        assertEquals(2, effectedNum);
    }
}
