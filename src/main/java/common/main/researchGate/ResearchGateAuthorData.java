package common.main.researchGate;

public class ResearchGateAuthorData {


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



    public ResearchGateAuthorData() {
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
