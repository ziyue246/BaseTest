package common.mapper;


import common.pojo.WosExportData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ziyue on 2018/05/29.
 */
public interface WosExportMapper {

    public void insertWosExportPaper(WosExportData wosExportData);
    public void insertWosExportPaperByBatch(@Param("wosExportDatas") List<WosExportData> wosExportDatas);

    public List<WosExportData> getWosExportPapers( );


}
