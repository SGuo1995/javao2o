package guo.proj.javao2o.dao;

import guo.proj.javao2o.BaseTest;
import guo.proj.javao2o.entity.PersonInfo;
import guo.proj.javao2o.entity.WechatAuth;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WechatAuthDaoTest extends BaseTest {
    @Autowired
    private WechatAuthDao wechatAuthDao;

    @Test
    public void testAInsertWechatAuth() throws Exception {
        WechatAuth wechatAuth = new WechatAuth();
        wechatAuth.setCreateTime(new Date());
        wechatAuth.setOpenId("szvefg4egs");
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        wechatAuth.setPersonInfo(personInfo);
        int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testBQueryWechatAuthByOpenId() throws Exception {
        WechatAuth wechatAuth = wechatAuthDao.queryWechatInfoByOpenId("szvefg4egs");
        assertEquals("test", wechatAuth.getPersonInfo().getName());
    }
}
