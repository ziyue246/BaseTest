package common.system;

import common.service.AssociationConferenceService;
import common.service.CommonService;
import common.service.HotSearchService;


public class Systemconfig {


    public static HotSearchService hotSearchService;

    public static CommonService commonService;


    public static AssociationConferenceService associationConferenceService;

    public void initial(){

    }


    public HotSearchService getHotSearchService() {
        return hotSearchService;
    }

    public void setHotSearchService(HotSearchService hotSearchService) {
        Systemconfig.hotSearchService = hotSearchService;
    }


    public CommonService getCommonService() {
        return commonService;
    }

    public void setCommonService(CommonService commonService) {
        Systemconfig.commonService = commonService;
    }


    public AssociationConferenceService getAssociationConferenceService() {
        return associationConferenceService;
    }

    public void setAssociationConferenceService(AssociationConferenceService associationConferenceService) {
        Systemconfig.associationConferenceService = associationConferenceService;
    }
}
