package common.analysis;

import org.apache.log4j.Logger;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class ZhAnalysis {


    private static Logger logger = Logger.getLogger(ZhAnalysis.class);

    private static double YUZHI=0.4;


    private static Vector<String> participle( String str ) {

        Vector<String> vector = new Vector<String>();//对输入进行分词

        try {

            StringReader reader = new StringReader(str);
            IKSegmenter ik = new IKSegmenter(reader, true);//当为true时，分词器进行最大词长切分
            Lexeme lexeme = null;

            while ((lexeme = ik.next()) != null) {
                vector.add(lexeme.getLexemeText());
            }

            if (vector.size() == 0) {
                return null;
            }

        } catch (Exception e1) {
            logger.error(e1.getMessage());
        }
        return vector;
    }


    /**
     * 采用余弦定理计算相似度
     * @param str1
     * @param str2
     * @return
     */
    public static double getSimilarity(String str1,String str2) {

        Vector<String> T1=participle(str1);
        Vector<String> T2=participle(str2);

        int size = 0 , size2 = 0 ;
        if ( T1 != null && ( size = T1.size() ) > 0 && T2 != null && ( size2 = T2.size() ) > 0 ) {

            Map<String, double[]> T = new HashMap<String, double[]>();

            //T1和T2的并集T
            String index = null ;
            for ( int i = 0 ; i < size ; i++ ) {
                index = T1.get(i) ;
                if( index != null){
                    double[] c = T.get(index);
                    c = new double[2];
                    c[0] = 1;	//T1的语义分数Ci
                    c[1] = YUZHI;//T2的语义分数Ci
                    T.put( index, c );
                }
            }

            for ( int i = 0; i < size2 ; i++ ) {
                index = T2.get(i) ;
                if( index != null ){
                    double[] c = T.get( index );
                    if( c != null && c.length == 2 ){
                        c[1] = 1; //T2中也存在，T2的语义分数=1
                    }else {
                        c = new double[2];
                        c[0] = YUZHI; //T1的语义分数Ci
                        c[1] = 1; //T2的语义分数Ci
                        T.put( index , c );
                    }
                }
            }
            //开始计算，百分比
            Iterator<String> it = T.keySet().iterator();
            double s1 = 0 , s2 = 0, Ssum = 0;  //S1、S2
            while( it.hasNext() ){
                double[] c = T.get( it.next() );
                Ssum += c[0]*c[1];
                s1 += c[0]*c[0];
                s2 += c[1]*c[1];
            }
            //百分比
            return Ssum / Math.sqrt( s1*s2 );
        } else {
            logger.error("传入参数有问题！");
        }
        return 0;
    }
}
