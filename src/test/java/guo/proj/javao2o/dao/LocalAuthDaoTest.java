package guo.proj.javao2o.dao;

import guo.proj.javao2o.BaseTest;
import guo.proj.javao2o.entity.LocalAuth;
import guo.proj.javao2o.entity.PersonInfo;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthDaoTest extends BaseTest {
    @Autowired
    private LocalAuthDao localAuthDao;
    private static final String username = "testusername";
    private static final String password = "testpassword";

    @Test
    public void testAInsertLocalAuth() throws Exception {
        LocalAuth localAuth = new LocalAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        localAuth.setCreateTime(new Date());
        localAuth.setUsername(username);
        localAuth.setPassword(password);
        localAuth.setPersonInfo(personInfo);
        int effectedNum = localAuthDao.insertLocalAuth(localAuth);
        assertEquals(1, effectedNum);

    }

    @Test
    public void testBQueryLocalByUsernameAndPwd() throws Exception {
        LocalAuth localAuth = localAuthDao.queryLocalByUserNameAndPwd(username, password);
        assertEquals("test", localAuth.getPersonInfo().getName());
    }

    @Test
    public void testCQueryLocalByUserId() throws Exception {
        LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
        assertEquals("test", localAuth.getPersonInfo().getName());
    }

    @Test
    public void testDUpdateLocalAuth() throws Exception {
        String newPassword = "newpassword";
        Date newDate = new Date();
        int effectNum = localAuthDao.updateLocalAuth(1L, username, password, newPassword, newDate);
        LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
        assertEquals("newpassword", localAuth.getPassword());
    }
}
