package common.service;

import common.mapper.IntelligentTyresMapper;
import common.mapper.WosExportMapper;
import common.pojo.IntelligentTyresData;
import common.pojo.WosExportData;
import org.apache.log4j.Logger;

import java.util.List;


public class WosExportService {



    private static Logger logger = Logger.getLogger(WosExportService.class);
    private WosExportMapper wosExportMapper;


    public WosExportMapper getWosExportMapper() {
        return wosExportMapper;
    }

    public void setWosExportMapper(WosExportMapper wosExportMapper) {
        this.wosExportMapper = wosExportMapper;
    }

    public void saveDate(WosExportData wosExportData ){

        wosExportMapper.insertWosExportPaper(wosExportData);
    }

    public void saveDates(List<WosExportData> wosExportDatas ){

        wosExportMapper.insertWosExportPaperByBatch(wosExportDatas);
    }
}
