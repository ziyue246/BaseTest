package common.system;

public class SystemConfig {


    private static String runModel;
    private static boolean crawlImage = false;

    private static boolean httpMark = true;

    public static void setHttpMark(boolean httpMark) {
        synchronized (SystemConfig.class) {
            SystemConfig.httpMark = httpMark;
        }
    }

    public static boolean isHttpMark() {
        synchronized (SystemConfig.class) {
            return httpMark;
        }
    }

    public static void setCrawlImage(boolean crawlerImage) {
        synchronized (SystemConfig.class){
            SystemConfig.crawlImage = crawlerImage;
        }
    }

    public static void setRunModel(String runModel) {
        synchronized (SystemConfig.class) {
            SystemConfig.runModel = runModel;
        }
    }

    public static String getRunModel() {
        synchronized (SystemConfig.class) {
            return runModel;
        }
    }

    public static boolean isCrawlImage() {
        synchronized (SystemConfig.class){
            return crawlImage;
        }
    }
}
