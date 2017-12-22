package common.pojo;

/**
 * Created by ziyue on 2017/12/20.
 */
public class WeiboData {


    private String	id;
    private String url;
    private String pubtime;
    private String insert_time;
    private String md5;
    private String user_id;
    private String comment_count;
    private String rtt_count;
    private String mid;
    private String comment_url;
    private String rtt_url;
    private String author;
    private String author_url;
    private String search_keyword;
    private String category_code;
    private String author_img;
    private String content;
    private String source;
    private String site_id;
    private String img_url;
    private String reliability;
    private String gps;
    private String old_id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPubtime() {
        return pubtime;
    }

    public void setPubtime(String pubtime) {
        this.pubtime = pubtime;
    }

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getRtt_count() {
        return rtt_count;
    }

    public void setRtt_count(String rtt_count) {
        this.rtt_count = rtt_count;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getComment_url() {
        return comment_url;
    }

    public void setComment_url(String comment_url) {
        this.comment_url = comment_url;
    }

    public String getRtt_url() {
        return rtt_url;
    }

    public void setRtt_url(String rtt_url) {
        this.rtt_url = rtt_url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor_url() {
        return author_url;
    }

    public void setAuthor_url(String author_url) {
        this.author_url = author_url;
    }

    public String getSearch_keyword() {
        return search_keyword;
    }

    public void setSearch_keyword(String search_keyword) {
        this.search_keyword = search_keyword;
    }

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }

    public String getAuthor_img() {
        return author_img;
    }

    public void setAuthor_img(String author_img) {
        this.author_img = author_img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getReliability() {
        return reliability;
    }

    public void setReliability(String reliability) {
        this.reliability = reliability;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getOld_id() {
        return old_id;
    }

    public void setOld_id(String old_id) {
        this.old_id = old_id;
    }


    @Override
    public String toString() {
        return "WeiboData{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", pubtime='" + pubtime + '\'' +
                ", insert_time='" + insert_time + '\'' +
                ", md5='" + md5 + '\'' +
                ", user_id='" + user_id + '\'' +
                ", comment_count='" + comment_count + '\'' +
                ", rtt_count='" + rtt_count + '\'' +
                ", mid='" + mid + '\'' +
                ", comment_url='" + comment_url + '\'' +
                ", rtt_url='" + rtt_url + '\'' +
                ", author='" + author + '\'' +
                ", author_url='" + author_url + '\'' +
                ", search_keyword='" + search_keyword + '\'' +
                ", category_code='" + category_code + '\'' +
                ", author_img='" + author_img + '\'' +
                ", content='" + content + '\'' +
                ", source='" + source + '\'' +
                ", site_id='" + site_id + '\'' +
                ", img_url='" + img_url + '\'' +
                ", reliability='" + reliability + '\'' +
                ", gps='" + gps + '\'' +
                ", old_id='" + old_id + '\'' +
                '}';
    }
}
