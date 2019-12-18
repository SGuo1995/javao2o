package guo.proj.javao2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import guo.proj.javao2o.dto.ShopExecution;
import guo.proj.javao2o.entity.Area;
import guo.proj.javao2o.entity.PersonInfo;
import guo.proj.javao2o.entity.Shop;
import guo.proj.javao2o.entity.ShopCategory;
import guo.proj.javao2o.enums.ShopStateEnum;
import guo.proj.javao2o.exceptions.ShopOperationException;
import guo.proj.javao2o.service.AreaService;
import guo.proj.javao2o.service.ShopCategoryService;
import guo.proj.javao2o.service.ShopService;
import guo.proj.javao2o.util.HttpServletRequestUtil;
import guo.proj.javao2o.util.ImageUtil;
import guo.proj.javao2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopInitInfo() {
        Map<String, Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        List<Area> areaList = new ArrayList<>();
        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;

    }
    @RequestMapping(value = "/registerShop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        //1.接受并转化相应参数，包括店铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "Image is required");
            return modelMap;
        }
        //2.注册店铺
        if (shop != null && shopImg != null) {
            PersonInfo owner = new PersonInfo();
            owner.setUserId(1L);
            //Session to do
            shop.setOwner(owner);


            ShopExecution se = null;
            try {
                se = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (ShopOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());

            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }

            return modelMap;

        } else {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "Please fill shop's information");
            return modelMap;
        }
        //3.返回结果
    }
//    private static void inputStreamToFile(InputStream ins, File file) {
//        FileOutputStream os = null;
//        try {
//            os = new FileOutputStream(file);
//            int bytesRead = 0;
//            byte[] buffer = new byte[1024];
//            while((bytesRead = ins.read(buffer)) != -1){
//                os.write(buffer, 0, bytesRead);
//
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Error Calling inputstreamTofile: " + e.getMessage());
//        } finally {
//            try {
//                if (os != null) {
//                    os.close();
//                }
//                if (ins != null) {
//                    ins.close();
//                }
//            } catch (IOException e) {
//                throw new RuntimeException("InputStreamTOFIle closing io error " + e.getMessage());
//            }
//        }
//    }
}
