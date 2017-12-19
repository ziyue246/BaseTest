package common.system;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ziyue on 2017/4/5.
 */
public class Proxy {
    private static List<String> proxys = new ArrayList<String>();
    private static final Logger logger= Logger.getLogger(Proxy.class);

    public static String randomeGetProxy(){
        synchronized(Proxy.class) {
            if(proxys.size()==0) return null;
            int selectProxy = (int) (Math.random() * proxys.size()) - 1;
            selectProxy = selectProxy <= 0 ? 0 : selectProxy;
            logger.info("randomeGetProxy proxys length:"+proxys.size());
            return proxys.get(selectProxy);
        }
    }
    public static List<String> getProxys(){
        synchronized(Proxy.class) {
            return proxys;
        }
    }
    public static void addProxy(String p){
        synchronized(Proxy.class) {
            if(p!=null) {
                //proxys.clear();
                if(!proxys.contains(p)) {
                    proxys.add(p);
                }
            }else{
                proxys = new ArrayList<String>();
                proxys.add(p);
            }
        }
    }
    public static void addProxys(List<String> p){
        synchronized(Proxy.class) {
            if(p!=null) {
                //proxys.clear();
                proxys.addAll(p);
            }else{
                proxys = p;
            }
        }
    }
    public static void setProxys(List<String> p){
        synchronized(Proxy.class) {
            if(p!=null) {
                proxys.clear();
                proxys.addAll(p);
            }else{
                proxys = p;
            }
        }
    }
    public static void removeIndexFromProxys(int index){
        synchronized(Proxy.class) {
            if (proxys.size() - 1 > index&&index>0) {
                proxys.remove(index);
            }
        }

    }
    public static void removeObjFromProxys(String proxy){
        synchronized(Proxy.class) {
            if (proxy!=null&&proxys.contains(proxy)) {
                proxys.remove(proxy);
            }
            logger.info("removeObjFromProxys proxys length:"+proxys.size());
        }
    }

}
