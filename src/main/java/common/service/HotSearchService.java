package common.service;

import common.mapper.HotSearchMapper;
import common.pojo.BbsData;
import common.pojo.NewsData;
import common.pojo.WeiboData;

import java.util.List;

public class HotSearchService {

    private HotSearchMapper hotSearchMapper;

    public HotSearchMapper getHotSearchMapper() {
        return hotSearchMapper;
    }

    public void setHotSearchMapper(HotSearchMapper hotSearchMapper) {
        this.hotSearchMapper = hotSearchMapper;
    }

    public List<WeiboData> getWeiboDates(){
        return hotSearchMapper.getWeiboDates();
    }
    public List<NewsData> getNewsDates(){
        return hotSearchMapper.getNewsDates();
    }
    public List<BbsData> getBbsDates(){
        return hotSearchMapper.getBbsDates();
    }


    public List<WeiboData> getWeiboDatesBySplitPage(int index ){
        return hotSearchMapper.getWeiboDatesBySplitPage(index );
    }
    public List<NewsData> getNewsDatesBySplitPage(int index){
        return hotSearchMapper.getNewsDatesBySplitPage(index);
    }
    public List<BbsData> getBbsDatesBySplitPage(int index){
        return hotSearchMapper.getBbsDatesBySplitPage(index);
    }
    public List<WeiboData> getWeiboDatesByIdSplitPage(int index ){
        return hotSearchMapper.getWeiboDatesByIdSplitPage(index );
    }
    public List<NewsData> getNewsDatesByIdSplitPage(int index){
        return hotSearchMapper.getNewsDatesByIdSplitPage(index);
    }
    public List<BbsData> getBbsDatesByIdSplitPage(int index){
        return hotSearchMapper.getBbsDatesByIdSplitPage(index);
    }

}
