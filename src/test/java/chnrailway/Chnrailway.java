package chnrailway;


import common.http.HtmlInfo;
import common.http.SimpleHttp;
import common.system.FileOperation;
import common.system.SystemCommon;
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

        String url = "http://www.chnrailway.com/baike/tlcs/index.html";
        html.setOrignUrl(url);
        String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";
        html.setUa(ua);

        html.setEncode("gbk");
        while(true)
        {
            String nextUrl = listProcess(html,http);

            if(nextUrl.startsWith("http")){
                html.setOrignUrl(nextUrl);
                System.out.println(nextUrl);
            }else{
                break;
            }
            SystemCommon.sleepsSecond(5);

        }

    }
    private String listProcess(HtmlInfo html,SimpleHttp http){

        http.simpleGet(html);

        String content = html.getContent();

        Node node  = DomTree.getNode(content,"gbk");
        String xpath = "//DIV[@class='newslist']/DIV[@class='tit']/A/@href";

        List<String> urlList =  DomTree.parserNodeByXpathGetList(node,xpath);

        for(String currUrl : urlList){
            currUrl="http://www.chnrailway.com/"+currUrl;
            html.setOrignUrl(currUrl);
            http.simpleGet(html);

            retailProcess(html,http);


        }

//        <p id="pages">
//        <a href="http://www.chnrailway.com/baike/tlcs/index_2.shtml">下一页</a>
        String nextPageXpath = "//P[@id='pages']/A[contains(.,'下一页')]/@href";
        String prefix="http://www.chnrailway.com";
        return DomTree.parserNodeByXpathGetStringSplitByMarkWithPrefix(node,nextPageXpath,"",prefix);
        //
    }

    private void retailProcess(HtmlInfo html,SimpleHttp http){
        http.simpleGet(html);

        String contentHtml = html.getContent();

        String prefix="http://www.chnrailway.com";

        String titleXpath="//TITLE";
            //<div id="content">
        String contentXpath="//DIV[@id='content']";
        //<img border="0" alt="\" src=
        String imgsXpath="//DIV[@id='content']//IMG/@src";
        Node node  = DomTree.getNode(contentHtml,"gbk");
        String title =  DomTree.parserNodeByXpathGetStringSplitByMark(node,titleXpath,";");
        String content =  DomTree.parserNodeByXpathGetStringSplitByMark(node,contentXpath,"\n");
        String imgs =  DomTree.parserNodeByXpathGetStringSplitByMarkWithPrefix(node,contentXpath,";",prefix);


        String title_tmp = title.replaceAll("[:|/|?|*|“|”|\"|<|>|\\|]","");
        String path = "file/railWay/"+title_tmp+".txt";
        content = "title:"+title+"\nimgs:"+imgs+"\ncontent:"+content;
        FileOperation.write(content,path);
        SystemCommon.sleepsSecond(25);


    }

    @Test
    public  void test(){
        String str = ": ：:/ ? * “ \" < > |######？";

        System.out.println(str.replaceAll("[:|/|?|*|“|”|\"|<|>|\\|]",""));
    }




}
