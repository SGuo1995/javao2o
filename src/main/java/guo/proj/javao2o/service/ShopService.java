package guo.proj.javao2o.service;

import guo.proj.javao2o.dto.ShopExecution;
import guo.proj.javao2o.entity.Shop;
import guo.proj.javao2o.exceptions.ShopOperationException;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;
}
