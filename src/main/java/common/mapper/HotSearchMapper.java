package common.mapper;

import common.pojo.BbsData;
import common.pojo.NewsData;
import common.pojo.WeiboData;

import java.util.Date;
import java.util.List;

/**
 * Created by ziyue on 2017/12/20.
 */
public interface HotSearchMapper {

    public List<WeiboData> getWeiboDates();
    public List<NewsData> getNewsDates();
    public List<BbsData> getBbsDates();

    public List<WeiboData> getWeiboDatesBySplitPage(int index);
    public List<NewsData> getNewsDatesBySplitPage(int index);
    public List<BbsData> getBbsDatesBySplitPage(int index);




}
