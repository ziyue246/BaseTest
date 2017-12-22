package common.pojo;

import java.util.Date;

/**
 * Created by ziyue on 2017/12/20.
 */
public class NewsData {


    private int ID;
    private String TITLE;
    private String AUTHOR;
    private int SITE_ID;
    private Date PUBTIME;
    private String URL;
    private String SEARCH_KEYWORD;
    private String SOURCE;
    private String CATEGORY_CODE;
    private Date INSERTTIME;
    private String MD5;
    private String CONTENT;
    private String BRIEF;
    private String IMG_URL;
    private int SAME_NUM;
    private String SAME_URL;
    private int OLD_ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getAUTHOR() {
        return AUTHOR;
    }

    public void setAUTHOR(String AUTHOR) {
        this.AUTHOR = AUTHOR;
    }

    public int getSITE_ID() {
        return SITE_ID;
    }

    public void setSITE_ID(int SITE_ID) {
        this.SITE_ID = SITE_ID;
    }

    public Date getPUBTIME() {
        return PUBTIME;
    }

    public void setPUBTIME(Date PUBTIME) {
        this.PUBTIME = PUBTIME;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getSEARCH_KEYWORD() {
        return SEARCH_KEYWORD;
    }

    public void setSEARCH_KEYWORD(String SEARCH_KEYWORD) {
        this.SEARCH_KEYWORD = SEARCH_KEYWORD;
    }

    public String getSOURCE() {
        return SOURCE;
    }

    public void setSOURCE(String SOURCE) {
        this.SOURCE = SOURCE;
    }

    public String getCATEGORY_CODE() {
        return CATEGORY_CODE;
    }

    public void setCATEGORY_CODE(String CATEGORY_CODE) {
        this.CATEGORY_CODE = CATEGORY_CODE;
    }

    public Date getINSERTTIME() {
        return INSERTTIME;
    }

    public void setINSERTTIME(Date INSERTTIME) {
        this.INSERTTIME = INSERTTIME;
    }

    public String getMD5() {
        return MD5;
    }

    public void setMD5(String MD5) {
        this.MD5 = MD5;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public String getBRIEF() {
        return BRIEF;
    }

    public void setBRIEF(String BRIEF) {
        this.BRIEF = BRIEF;
    }

    public String getIMG_URL() {
        return IMG_URL;
    }

    public void setIMG_URL(String IMG_URL) {
        this.IMG_URL = IMG_URL;
    }

    public int getSAME_NUM() {
        return SAME_NUM;
    }

    public void setSAME_NUM(int SAME_NUM) {
        this.SAME_NUM = SAME_NUM;
    }

    public String getSAME_URL() {
        return SAME_URL;
    }

    public void setSAME_URL(String SAME_URL) {
        this.SAME_URL = SAME_URL;
    }

    public int getOLD_ID() {
        return OLD_ID;
    }

    public void setOLD_ID(int OLD_ID) {
        this.OLD_ID = OLD_ID;
    }


    @Override
    public String toString() {
        return "NewsData{" +
                "ID=" + ID +
                ", TITLE='" + TITLE + '\'' +
                ", AUTHOR='" + AUTHOR + '\'' +
                ", SITE_ID=" + SITE_ID +
                ", PUBTIME=" + PUBTIME +
                ", URL='" + URL + '\'' +
                ", SEARCH_KEYWORD='" + SEARCH_KEYWORD + '\'' +
                ", SOURCE='" + SOURCE + '\'' +
                ", CATEGORY_CODE='" + CATEGORY_CODE + '\'' +
                ", INSERTTIME=" + INSERTTIME +
                ", MD5='" + MD5 + '\'' +
                ", CONTENT='" + CONTENT + '\'' +
                ", BRIEF='" + BRIEF + '\'' +
                ", IMG_URL='" + IMG_URL + '\'' +
                ", SAME_NUM=" + SAME_NUM +
                ", SAME_URL='" + SAME_URL + '\'' +
                ", OLD_ID=" + OLD_ID +
                '}';
    }
}
