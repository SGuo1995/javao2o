package guo.proj.javao2o.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import guo.proj.javao2o.BaseTest;
import guo.proj.javao2o.dto.LocalAuthExecution;
import guo.proj.javao2o.entity.LocalAuth;
import guo.proj.javao2o.entity.PersonInfo;
import guo.proj.javao2o.enums.LocalAuthStateEnum;
import guo.proj.javao2o.enums.WechatAuthStateEnum;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthServiceTest extends BaseTest {

    @Autowired
    private LocalAuthService localAuthService;

    @Test
    @Ignore
    public void testABindLocalAuth() {
        // 新增一条平台帐号
        LocalAuth localAuth = new LocalAuth();
        PersonInfo personInfo = new PersonInfo();
        String username = "testusername";
        String password = "testpassword";
        // 给平台帐号设置上用户信息
        // 给用户设置上用户Id,标明是某个用户创建的帐号
        personInfo.setUserId(1L);
        // 给平台帐号设置用户信息,标明是与哪个用户绑定
        localAuth.setPersonInfo(personInfo);
        // 设置帐号
        localAuth.setUsername(username);
        // 设置密码
        localAuth.setPassword(password);
        // 绑定帐号
        LocalAuthExecution lae = localAuthService.bindLocalAuth(localAuth);
        assertEquals(WechatAuthStateEnum.SUCCESS.getState(), lae.getState());
        // 通过userId找到新增的localAuth
        localAuth = localAuthService.getLocalAuthByUserId(personInfo.getUserId());
        // 打印用户名字和帐号密码看看跟预期是否相符
        System.out.println("Username：" + localAuth.getPersonInfo().getName());
        System.out.println("Password：" + localAuth.getPassword());
    }

    @Test
    public void testBModifyLocalAuth() {
        long userId = 1;
        String username = "testusername";
        String password = "testpassword";
        String newPassword = "newpassword";
        LocalAuthExecution lae = localAuthService.modifyLocalAuth(userId, username, password, newPassword);
        assertEquals(lae.getState(), LocalAuthStateEnum.SUCCESS.getState());
        LocalAuth localAuth = localAuthService.getLocalAuthByUserId(userId);
        System.out.println(localAuth.getPassword());
    }
}
