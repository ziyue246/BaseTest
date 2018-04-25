package common.service;

import common.mapper.AssociationConferenceMapper;
import common.mapper.IntelligentTyresMapper;
import common.pojo.AssociationConferenceAllCountData;
import common.pojo.AssociationConferenceData;
import common.pojo.IntelligentTyresData;
import org.apache.log4j.Logger;


public class IntelligentTyresService {



    private static Logger logger = Logger.getLogger(IntelligentTyresService.class);
    private IntelligentTyresMapper intelligentTyresMapper;


    public IntelligentTyresMapper getIntelligentTyresMapper() {
        return intelligentTyresMapper;
    }

    public void setIntelligentTyresMapper(IntelligentTyresMapper intelligentTyresMapper) {
        this.intelligentTyresMapper = intelligentTyresMapper;
    }


    public void saveDate(IntelligentTyresData intelligentTyresData ){
        switch (intelligentTyresData.getDbselect()){
            case PAPER:
                intelligentTyresMapper.insertIntelligentTyresPaper(intelligentTyresData);
                //logger.info("insert IntelligentTyres Paper");
                break;
            case PATENT:
                intelligentTyresMapper.insertIntelligentTyresPatent(intelligentTyresData);
                //logger.info("insert IntelligentTyres Patent");
                break;
            default:
                logger.warn("insert not get this category");
                break;
        }

    }
}
