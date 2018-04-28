package common.service;

import common.mapper.AssociationConferenceMapper;
import common.mapper.CommonMapper;
import common.pojo.AssociationConferenceAllCountData;
import common.pojo.AssociationConferenceData;
import common.pojo.NameTest;
import org.apache.log4j.Logger;

import java.util.List;


public class AssociationConferenceService {



    private static Logger logger = Logger.getLogger(AssociationConferenceService.class);
    private AssociationConferenceMapper associationConferenceMapper;

    public AssociationConferenceMapper getAssociationConferenceMapper() {
        return associationConferenceMapper;
    }

    public void setAssociationConferenceMapper(AssociationConferenceMapper associationConferenceMapper) {
        this.associationConferenceMapper = associationConferenceMapper;
    }



    public void saveAssociationConferenceDate(AssociationConferenceData associationConferenceData, AssociationConferenceData.DBSELECT dbSelect){

        switch (dbSelect){
            case NATIONAL:
                associationConferenceMapper.insertNationalConference(associationConferenceData);
                break;
            case INTERNATIONAL:
                associationConferenceMapper.insertInternationalConference(associationConferenceData);
                break;
            default:
        }
    }

    public void saveAssociationConferenceAllCountDate(
            AssociationConferenceAllCountData associationConferenceAllCountData){

        associationConferenceMapper.insertAllCountConference(associationConferenceAllCountData);

    }
}
