package common.pojo;

/**
 * Created by ziyue on 2018/4/23.
 */
public class IntelligentTyresData {

    private String id;
    private String remove_reason;
    private String new_category_reason;
    private String title;
    private String download;
    private String author;
    private String category_db;
    private String journal;
    private String summary;
    private String keywords;
    private String address;
    private String refer_num;
    private String cite_num;
    private String down_num;
    private String lib_category;
    private String en_author;
    private String fund;
    private String searchkey;
    private String application_num;
    private String applicant;
    private String application_time;
    private String pubtime;
    private String inventor;
    private String excel_id;
    private String category;
    private String language;
    private DBSELECT dbselect;

    public enum DBSELECT {
        PAPER,PATENT
    }


    public DBSELECT getDbselect() {
        return dbselect;
    }

    public void setDbselect(DBSELECT dbselect) {
        this.dbselect = dbselect;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRemove_reason() {
        return remove_reason;
    }

    public void setRemove_reason(String remove_reason) {
        this.remove_reason = remove_reason;
    }

    public String getNew_category_reason() {
        return new_category_reason;
    }

    public void setNew_category_reason(String new_category_reason) {
        this.new_category_reason = new_category_reason;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory_db() {
        return category_db;
    }

    public void setCategory_db(String category_db) {
        this.category_db = category_db;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCite_num() {
        return cite_num;
    }

    public void setCite_num(String cite_num) {
        this.cite_num = cite_num;
    }

    public String getDown_num() {
        return down_num;
    }

    public void setDown_num(String down_num) {
        this.down_num = down_num;
    }

    public String getLib_category() {
        return lib_category;
    }

    public void setLib_category(String lib_category) {
        this.lib_category = lib_category;
    }

    public String getEn_author() {
        return en_author;
    }

    public void setEn_author(String en_author) {
        this.en_author = en_author;
    }


    public String getRefer_num() {
        return refer_num;
    }

    public void setRefer_num(String refer_num) {
        this.refer_num = refer_num;
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }

    public String getSearchkey() {
        return searchkey;
    }

    public void setSearchkey(String searchkey) {
        this.searchkey = searchkey;
    }

    public String getApplication_num() {
        return application_num;
    }

    public void setApplication_num(String application_num) {
        this.application_num = application_num;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getApplication_time() {
        return application_time;
    }

    public void setApplication_time(String application_time) {
        this.application_time = application_time;
    }

    public String getPubtime() {
        return pubtime;
    }

    public void setPubtime(String pubtime) {
        this.pubtime = pubtime;
    }

    public String getInventor() {
        return inventor;
    }

    public void setInventor(String inventor) {
        this.inventor = inventor;
    }

    public String getExcel_id() {
        return excel_id;
    }

    public void setExcel_id(String excel_id) {
        this.excel_id = excel_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
