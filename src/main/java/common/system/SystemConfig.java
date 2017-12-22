package common.system;

import common.service.HotSearchService;


public class Systemconfig {


    public static HotSearchService hotSearchService;
    public void initial(){

    }


    public HotSearchService getHotSearchService() {
        return hotSearchService;
    }

    public void setHotSearchService(HotSearchService hotSearchService) {
        Systemconfig.hotSearchService = hotSearchService;
    }
}
