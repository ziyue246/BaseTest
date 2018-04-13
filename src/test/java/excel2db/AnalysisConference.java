package excel2db;

import org.carrot2.clustering.lingo.LingoClusteringAlgorithm;
import org.carrot2.clustering.synthetic.ByUrlClusteringAlgorithm;
import org.carrot2.core.*;
import org.carrot2.core.attribute.CommonAttributesDescriptor;
import org.carrot2.shaded.guava.common.collect.Maps;
import org.carrot2.text.clustering.MultilingualClusteringDescriptor;
import org.carrot2.core.Cluster;
import org.carrot2.core.Controller;
import org.carrot2.core.ControllerFactory;
import org.carrot2.core.Document;
import org.carrot2.core.IDocumentSource;
import org.carrot2.core.ProcessingResult;



import java.util.List;
import java.util.Map;

/**
 * Created by ziyue on 2018/4/12.
 */
public class AnalysisConference {


    public static void main(String[] args) {

//        final Controller controller = ControllerFactory.createCachingPooling(IDocumentSource.class);
//        //创建需要处理的属性对象
//        final Map<String, Object> attributes = Maps.newHashMap();
//
//        //需要处理的文档集合
//        List<Document> documents = SampleDocumentData.DOCUMENTS_DATA_MINING;
//
//
//        //将文档添加到聚类属性中
//        CommonAttributesDescriptor.attributeBuilder(attributes).documents(documents);
//
//        //设置处理的语言（）
//        MultilingualClusteringDescriptor.attributeBuilder(attributes).defaultLanguage(LanguageCode.CHINESE_SIMPLIFIED);
//
//        //设置需要处理的对象，以及聚类的算法
//        final ProcessingResult englishResult = controller.process(attributes, LingoClusteringAlgorithm.class);
//
//        //获取结果，打印聚类主题及关联信息
//        final List<Cluster> clustersByTopic = englishResult.getClusters();
//        System.out.println("=======聚类主题=====");
//        for(Cluster cluster : clustersByTopic){
//            System.out.println("【主题 】" +cluster.getLabel());
//            List<Document> cDocLst = cluster.getAllDocuments();
//            for(Document doc : cDocLst){
//                System.out.println("--------" + doc.getTitle());
//            }
//        }
//
//        //通过URL进行聚类
//        final ProcessingResult byDomainClusters = controller.process(documents,
//                null,ByUrlClusteringAlgorithm.class);
//        final List<Cluster> clustersByDomain = byDomainClusters.getClusters();
//        System.out.println("=======URL聚类=======");
//        for(Cluster cluster : clustersByDomain){
//            System.out.println("【URL】" +cluster.getLabel());
//            List<Document> cDocLst = cluster.getAllDocuments();
//            for(Document doc : cDocLst){
//                System.out.println("----" + doc.getTitle());
//            }
//        }
    }
}
