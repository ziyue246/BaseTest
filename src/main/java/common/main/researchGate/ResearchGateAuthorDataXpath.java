package common.main.researchGate;

public class ResearchGateAuthorDataXpath {

    public static String name="//H1/DIV/SPAN[@class='fn']";
    public static String url="//DIV[@class='js-items']/UL[@class='search-results clearfix']//DIV[@class='name']/A/@href";
    public static String authorRgScore="//DIV[@title='RG Score']";
    public static String institution="//H1/DIV/SPAN[@class='org']";

    //Current positionPresident
    public static String currPosition="//DIV[@class='nova-v-institution-item__stack-item'][contains(., 'Current position')]";
    public static String skills="//DIV[@class='profile-skills-list-new__list']//SPAN";
    public static String researchItemsNum="//DIV[@class='nova-o-grid__column nova-o-grid__column--width-4/12@s-up'][contains(., 'Research items')]";
    public static String readsNum="//DIV[@class='nova-o-grid__column nova-o-grid__column--width-4/12@s-up'][contains(., 'Reads')]";
    public static String citationsNun="//DIV[@class='nova-o-grid__column nova-o-grid__column--width-4/12@s-up'][contains(., 'Citations')]";
    public static String followersNum="followersNum";
    public static String followingNum="followingNum";
//
}
