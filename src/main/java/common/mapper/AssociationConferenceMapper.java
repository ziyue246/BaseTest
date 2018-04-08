package common.mapper;

import common.pojo.AssociationConferenceData;
import common.pojo.NameTest;

import java.util.List;

/**
 * Created by ziyue on 2017/12/20.
 */
public interface AssociationConferenceMapper {



    public void insertInternationalConference(AssociationConferenceData associationConferenceData);

    public void insertNationalConference(AssociationConferenceData associationConferenceData);


}
