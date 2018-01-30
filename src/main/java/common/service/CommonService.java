package common.service;

import common.mapper.CommonMapper;
import common.mapper.HotSearchMapper;
import common.pojo.BbsData;
import common.pojo.NameTest;
import common.pojo.NewsData;
import common.pojo.WeiboData;
import common.system.Systemconfig;

import java.util.List;

public class CommonService {

    private CommonMapper commonMapper;


    public CommonMapper getCommonMapper() {
        return commonMapper;
    }

    public void setCommonMapper(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }

//    public void testExecuteTime();
//
//    public List<String> getName();

    public Long testExecuteTime(){
        Long start= System.currentTimeMillis();
        commonMapper.testExecuteTime();
        return System.currentTimeMillis()-start;
    }

    public List<String> getNames(){
        return commonMapper.getNames();
    }

    public void testInsertUpdateId(NameTest name){
        commonMapper.testInsertUpdateId(name);
    }
}
