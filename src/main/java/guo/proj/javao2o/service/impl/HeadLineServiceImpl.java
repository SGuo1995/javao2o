package guo.proj.javao2o.service.impl;

import guo.proj.javao2o.dao.HeadLineDao;
import guo.proj.javao2o.entity.HeadLine;
import guo.proj.javao2o.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class HeadLineServiceImpl implements HeadLineService {

    @Autowired
    private HeadLineDao headLineDao;

    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
        return headLineDao.queryHeadLine(headLineCondition);
    }
}
