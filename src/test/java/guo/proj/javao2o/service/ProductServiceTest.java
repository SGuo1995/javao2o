package guo.proj.javao2o.service;

import guo.proj.javao2o.BaseTest;
import guo.proj.javao2o.dto.ImageHolder;
import guo.proj.javao2o.dto.ProductExecution;
import guo.proj.javao2o.entity.Product;
import guo.proj.javao2o.entity.ProductCategory;
import guo.proj.javao2o.entity.Shop;
import guo.proj.javao2o.enums.ProductStateEnum;
import guo.proj.javao2o.exceptions.ShopOperationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductServiceTest extends BaseTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testAddProduct() throws ShopOperationException, FileNotFoundException {
        // 创建shopId为36且productCategoryId为1的商品实例并给其成员变量赋值
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(36L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(1L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("Test product 1");
        product.setProductDesc("Test product 1");
        product.setPriority(20);
        product.setCreateTime(new Date());
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
        // 创建缩略图文件流
        File thumbnailFile = new File("C:\\Users\\24965\\Pictures\\Saved Pictures/paris.jpg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
        // 创建两个商品详情图文件流并将他们添加到详情图列表中
        File productImg1 = new File("C:\\Users\\24965\\Pictures\\Saved Pictures/paris.jpg");
        InputStream is1 = new FileInputStream(productImg1);
        File productImg2 = new File("C:\\Users\\24965\\Pictures\\Saved Pictures/bolang.jpg");
        InputStream is2 = new FileInputStream(productImg2);
        List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
        productImgList.add(new ImageHolder(productImg1.getName(), is1));
        productImgList.add(new ImageHolder(productImg2.getName(), is2));
        // 添加商品并验证
        ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
    }

    @Test
    public void testModifyProduct() throws ShopOperationException, FileNotFoundException {
        // 创建shopId为36且productCategoryId为2的商品实例并给其成员变量赋值
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(36L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(2L);
        product.setProductId(4L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("Modified Product");
        product.setProductDesc("Modified Product");
        // 创建缩略图文件流
        File thumbnailFile = new File("C:\\Users\\24965\\Pictures\\Saved Pictures/waterfall.jpg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
        // 创建两个商品详情图文件流并将他们添加到详情图列表中
        File productImg1 = new File("C:\\Users\\24965\\Pictures\\Saved Pictures/waterfall.jpg");
        InputStream is1 = new FileInputStream(productImg1);
        File productImg2 = new File("C:\\Users\\24965\\Pictures\\Saved Pictures/wild.jpg");
        InputStream is2 = new FileInputStream(productImg2);
        List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
        productImgList.add(new ImageHolder(productImg1.getName(), is1));
        productImgList.add(new ImageHolder(productImg2.getName(), is2));
        // 添加商品并验证
        ProductExecution pe = productService.modifyProduct(product, thumbnail, productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
    }
}
