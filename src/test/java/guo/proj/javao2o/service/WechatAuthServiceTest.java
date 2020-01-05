package guo.proj.javao2o.service;

import guo.proj.javao2o.BaseTest;
import guo.proj.javao2o.dto.WechatAuthExecution;
import guo.proj.javao2o.entity.PersonInfo;
import guo.proj.javao2o.entity.WechatAuth;
import guo.proj.javao2o.enums.WechatAuthStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class WechatAuthServiceTest extends BaseTest {

    @Autowired
    private WechatAuthService wechatAuthService;

    @Test
    public void testRegister() {
        WechatAuth wechatAuth = new WechatAuth();
        wechatAuth.setOpenId("veegxcgd");
        wechatAuth.setCreateTime(new Date());
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserType(1);
        personInfo.setName("test of register");
        personInfo.setCreateTime(new Date());
        wechatAuth.setPersonInfo(personInfo);
        WechatAuthExecution wae = wechatAuthService.register(wechatAuth);
        assertEquals(wae.getState(), WechatAuthStateEnum.SUCCESS.getState());
        wechatAuth = wechatAuthService.getWechatAuthByOpenId("veegxcgd");
        System.out.println(wechatAuth.getPersonInfo().getName());
    }
}
