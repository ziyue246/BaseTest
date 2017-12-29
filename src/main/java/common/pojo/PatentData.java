package common.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ziyue on 2017/12/22.
 */
public class PatentData {

    private int id;
    private String patentName;
    private String patentCode;
    private String category;
    private List<String> keywords;

//    private int ralationId;
//    private int ralationWeight;

    private Map<Integer,Integer> relationMap = new HashMap<>();


    public Map<Integer, Integer> getRelationMap() {
        return relationMap;
    }

    public void setRelationMap(Map<Integer, Integer> relationMap) {
        this.relationMap = relationMap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatentName() {
        return patentName;
    }

    public void setPatentName(String patentName) {
        this.patentName = patentName;
    }

    public String getPatentCode() {
        return patentCode;
    }

    public void setPatentCode(String patentCode) {
        this.patentCode = patentCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }


    @Override
    public String toString() {
        return "PatentData{" +
                "id=" + id +
                ", patentName='" + patentName + '\'' +
                ", patentCode='" + patentCode + '\'' +
                ", category='" + category + '\'' +
                ", keywords=" + keywords +
                ", relationMap=" + relationMap +
                '}';
    }
}
