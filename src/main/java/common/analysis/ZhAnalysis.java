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

        Vector<String> vector = new Vector<String>();//��������зִ�

        try {

            StringReader reader = new StringReader(str);
            IKSegmenter ik = new IKSegmenter(reader, true);//��Ϊtrueʱ���ִ����������ʳ��з�
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
     * �������Ҷ���������ƶ�
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

            //T1��T2�Ĳ���T
            String index = null ;
            for ( int i = 0 ; i < size ; i++ ) {
                index = T1.get(i) ;
                if( index != null){
                    double[] c = T.get(index);
                    c = new double[2];
                    c[0] = 1;	//T1���������Ci
                    c[1] = YUZHI;//T2���������Ci
                    T.put( index, c );
                }
            }

            for ( int i = 0; i < size2 ; i++ ) {
                index = T2.get(i) ;
                if( index != null ){
                    double[] c = T.get( index );
                    if( c != null && c.length == 2 ){
                        c[1] = 1; //T2��Ҳ���ڣ�T2���������=1
                    }else {
                        c = new double[2];
                        c[0] = YUZHI; //T1���������Ci
                        c[1] = 1; //T2���������Ci
                        T.put( index , c );
                    }
                }
            }
            //��ʼ���㣬�ٷֱ�
            Iterator<String> it = T.keySet().iterator();
            double s1 = 0 , s2 = 0, Ssum = 0;  //S1��S2
            while( it.hasNext() ){
                double[] c = T.get( it.next() );
                Ssum += c[0]*c[1];
                s1 += c[0]*c[0];
                s2 += c[1]*c[1];
            }
            //�ٷֱ�
            return Ssum / Math.sqrt( s1*s2 );
        } else {
            logger.error("������������⣡");
        }
        return 0;
    }
}
