package guo.proj.javao2o.dao;

import guo.proj.javao2o.BaseTest;
import guo.proj.javao2o.entity.PersonInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PersonInfoDaoTest extends BaseTest {
    @Autowired
    private PersonInfoDao personInfoDao;

    @Test
    public void testAInsertPersonInfo() throws Exception {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName("Goat RW");
        personInfo.setGender("Male");
        personInfo.setCreateTime(new Date());
        personInfo.setUserType(1);
        personInfo.setLastEditTime(new Date());
        personInfo.setEnableStatus(1);
        int effectedNum = personInfoDao.insertPersonInfo(personInfo);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testBQueryPersonInfo() {
        long userId = 1L;
        PersonInfo personInfo = personInfoDao.queryPersonInfoById(userId);
        System.out.println(personInfo.getName());
    }
}
