package common.main.google;

public class GooglePaperData {


    private int id;
    private int orderId;

    private String title;
    private String url;  //  title url ,内容页url
    private String authors;
    private String authorsUrl; //authorName1,authorUrl1;authorName2,authorUrl2;

    private String authorsIndexH; //author index-h

    private String brief;
    private int pubYear;
    private String searchKeyword;   //
    private String paperInfo;       //
    private String yearsCite;// example : 2015:20;2016:18;2017:22;2018:16
    private int citeNum;
    private String citeNumUrl;
    private int update;
    public GooglePaperData() {
        this.orderId    = 0;
        this.title      = "";
        this.authors    = "";
        this.authorsUrl = "";
        this.authorsIndexH = "";
        this.brief      = "";
        this.pubYear    = 0;
        this.searchKeyword = "";
        this.paperInfo  = "";
        this.yearsCite  = "";
        this.citeNum = 0;
        this.citeNumUrl="";
    }


    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCiteNumUrl() {
        return citeNumUrl;
    }

    public void setCiteNumUrl(String citeNumUrl) {
        this.citeNumUrl = citeNumUrl;
    }

    public int getPubYear() {
        return pubYear;
    }

    public void setPubYear(int pubYear) {
        this.pubYear = pubYear;
    }

    public int getCiteNum() {
        return citeNum;
    }

    public void setCiteNum(int citeNum) {
        this.citeNum = citeNum;
    }

    public String getAuthorsUrl() {
        return authorsUrl;
    }

    public void setAuthorsUrl(String authorsUrl) {
        this.authorsUrl = authorsUrl;
    }

    public String getYearsCite() {
        return yearsCite;
    }

    public void setYearsCite(String yearsCite) {
        this.yearsCite = yearsCite;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getAuthorsIndexH() {
        return authorsIndexH;
    }

    public void setAuthorsIndexH(String authorsIndexH) {
        this.authorsIndexH = authorsIndexH;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }



    public String getPaperInfo() {
        return paperInfo;
    }

    public void setPaperInfo(String paperInfo) {
        this.paperInfo = paperInfo;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
}
