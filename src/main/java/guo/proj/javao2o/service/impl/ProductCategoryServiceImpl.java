package guo.proj.javao2o.service.impl;

import guo.proj.javao2o.dao.ProductCategoryDao;
import guo.proj.javao2o.dto.ProductCategoryExecution;
import guo.proj.javao2o.entity.ProductCategory;
import guo.proj.javao2o.enums.ProductCategoryStateEnum;
import guo.proj.javao2o.exceptions.ProductCategoryOperationException;
import guo.proj.javao2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Override
    @Transactional
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effectedNum <= 0) {
                    throw new ProductCategoryOperationException("Failed to create product category");
                } else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationException("batch Insert Product Category error: " + e.getMessage());
            }
        } else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }

    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {
        // TO DO : set product_category_id of products belong to this category to be 0
        try {
            int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
            if (effectedNum <= 0) {
                throw new ProductCategoryOperationException("Failed to delete product category");
            } else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("Failed to delete product category:" + e.getMessage());
        }
    }
}
