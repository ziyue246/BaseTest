package common.main.researchGate;

public class ResearchGatePaperData {


    private int id;

    private int orderId;

    private String name;
    private String url;         //  title url ,内容页url
    private float authorRgScore;
    private String institution;
    private String currPosition;
    private String skills;
    private int researchItemsNum; //author index-h

    private int readsNum;
    private int CitationsNun;
    private int followersNum;   //
    private int followingNum;       //

    private String title;
    private String doi;

    private String authors_rg;
    private int rg_reads;
    private int recommendations_rg;
    private int followers_rg;
    private int comments_rg;
    private int cite_num_rg;
    private int relatedResearch_rg;
    private int rg_update;


    public ResearchGatePaperData() {
        this.orderId    = 0;
        this.name = "";
        this.url= "";
        this.authorRgScore= 0;
        this.institution= "";//
        this.currPosition= "";
        this.skills= "";
        this.researchItemsNum= 0;
        this.readsNum= 0;
        this.CitationsNun= 0;
        this.followersNum= 0;
        this.followingNum= 0;
        this.rg_reads=0;
        this.recommendations_rg=0;
        this.followers_rg=0;
        this.comments_rg=0;
        this.cite_num_rg=0;
        this.relatedResearch_rg=0;
        this.rg_update=0;
        this.authors_rg="";
    }

    public String getAuthors_rg() {
        return authors_rg;
    }

    public void setAuthors_rg(String authors_rg) {
        this.authors_rg = authors_rg;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRg_reads() {
        return rg_reads;
    }

    public void setRg_reads(int rg_reads) {
        this.rg_reads = rg_reads;
    }

    public int getRecommendations_rg() {
        return recommendations_rg;
    }

    public void setRecommendations_rg(int recommendations_rg) {
        this.recommendations_rg = recommendations_rg;
    }

    public int getFollowers_rg() {
        return followers_rg;
    }

    public void setFollowers_rg(int followers_rg) {
        this.followers_rg = followers_rg;
    }

    public int getComments_rg() {
        return comments_rg;
    }

    public void setComments_rg(int comments_rg) {
        this.comments_rg = comments_rg;
    }

    public int getCite_num_rg() {
        return cite_num_rg;
    }

    public void setCite_num_rg(int cite_num_rg) {
        this.cite_num_rg = cite_num_rg;
    }

    public int getRelatedResearch_rg() {
        return relatedResearch_rg;
    }

    public void setRelatedResearch_rg(int relatedResearch_rg) {
        this.relatedResearch_rg = relatedResearch_rg;
    }

    public int getRg_update() {
        return rg_update;
    }

    public void setRg_update(int rg_update) {
        this.rg_update = rg_update;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getAuthorRgScore() {
        return authorRgScore;
    }

    public void setAuthorRgScore(float authorRgScore) {
        this.authorRgScore = authorRgScore;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getCurrPosition() {
        return currPosition;
    }

    public void setCurrPosition(String currPosition) {
        this.currPosition = currPosition;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public int getResearchItemsNum() {
        return researchItemsNum;
    }

    public void setResearchItemsNum(int researchItemsNum) {
        this.researchItemsNum = researchItemsNum;
    }

    public int getReadsNum() {
        return readsNum;
    }

    public void setReadsNum(int readsNum) {
        this.readsNum = readsNum;
    }

    public int getCitationsNun() {
        return CitationsNun;
    }

    public void setCitationsNun(int citationsNun) {
        CitationsNun = citationsNun;
    }

    public int getFollowersNum() {
        return followersNum;
    }

    public void setFollowersNum(int followersNum) {
        this.followersNum = followersNum;
    }

    public int getFollowingNum() {
        return followingNum;
    }

    public void setFollowingNum(int followingNum) {
        this.followingNum = followingNum;
    }
}
