package common.mapper;

import common.pojo.BbsData;
import common.pojo.NameTest;
import common.pojo.NewsData;
import common.pojo.WeiboData;

import java.util.List;

/**
 * Created by ziyue on 2017/12/20.
 */
public interface CommonMapper {

    public List<String> testExecuteTime();

    public List<String> getNames();

    public void testInsertUpdateId(NameTest name);

}
