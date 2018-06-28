package common.main.google;

public class GoogleScholarXpath {

    public static String title="//H3[@class='gs_rt']";
    public static String yearsCite;// example : 2015:20;2016:18;2017:22;2018:16
    public static String pubYear = "//DIV[@class='gs_a']";
    public static String citeNum="//DIV[@class='gs_fl']//A[contains(@href,'cites=')]";
    public static String paperInfo="//DIV[@class='gs_r gs_or gs_scl']";
    public static String authors="//DIV[@class='gs_a']";
    public static String authorsUrl="//DIV[@class='gs_a']/A";
    public static String brief="//DIV[@class='gs_rs']";
    public static String citeNumUrl="//DIV[@class='gs_fl']//A[contains(@href,'cites=')]/@href";
    public static String nextURL= "//TR[@align='center']//TD[@align='left']/A/@href";

}
