package guo.proj.javao2o.service.impl;

import guo.proj.javao2o.dao.ShopDao;
import guo.proj.javao2o.dto.ShopExecution;
import guo.proj.javao2o.entity.Shop;
import guo.proj.javao2o.enums.ShopStateEnum;
import guo.proj.javao2o.exceptions.ShopOperationException;
import guo.proj.javao2o.service.ShopService;
import guo.proj.javao2o.util.ImageUtil;
import guo.proj.javao2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
        //check if null
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //set initial value of shop
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //insert shop into database
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                throw new ShopOperationException("Fail to add Shop");
            } else {
                if (shopImgInputStream != null) {
                    //save img
                    try {
                        addShopImg(shop, shopImgInputStream, fileName);
                    } catch (Exception e) {
                        throw new ShopOperationException("add Img error" + e.getMessage());
                    }
                    //update img address of the shop
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("fail to update img address");
                    }
                }

            }

        } catch (Exception e) {
            throw new ShopOperationException("add shop error" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);


    }

    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
        //get relative path of the img fold
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);
        shop.setShopImg(shopImgAddr);
    }

}
