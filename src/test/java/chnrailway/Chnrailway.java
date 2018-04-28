package chnrailway;


import common.http.HtmlInfo;
import common.http.SimpleHttp;
import common.utils.DomTree;

import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * created by ziyue on 2018/04/28
 */

public class Chnrailway {




    @Test
    public void testMain(){
        HtmlInfo html = new HtmlInfo();

        SimpleHttp http = new SimpleHttp();

        String url = "http://www.chnrailway.com/baike/tlcs/index.shtml";
        html.setUrl(url);
        String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";
        html.setUa(ua);

        List<String> urlList = getListUrls(html,http);

        for(String url_tmp : urlList){
            System.out.println(url_tmp);
        }
        //while(true)
        {


        }

    }
    private List<String> getListUrls(HtmlInfo html,SimpleHttp http){

        http.simpleGet(html);

        String content = html.getContent();

        Node node  = DomTree.getNode(content,"gbk");
        String xpath = "//DIV[@class='newslist']/DIV[@class='tit']/A/@href";

        return DomTree.parserNodeByXpathGetList(node,xpath);



    }



}
