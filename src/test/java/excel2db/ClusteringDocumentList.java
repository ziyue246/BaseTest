
/*
 * Carrot2 project.
 *
 * Copyright (C) 2002-2017, Dawid Weiss, Stanisław Osiński.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the repository checkout or at:
 * http://www.carrot2.org/carrot2.LICENSE
 */

package excel2db;

import java.util.ArrayList;
import java.util.List;

import common.system.FileOperation;
import org.apache.log4j.Logger;
import org.carrot2.clustering.lingo.LingoClusteringAlgorithm;
import org.carrot2.clustering.synthetic.ByUrlClusteringAlgorithm;
import org.carrot2.core.Cluster;
import org.carrot2.core.Controller;
import org.carrot2.core.ControllerFactory;
import org.carrot2.core.Document;
import org.carrot2.core.IDocumentSource;
import org.carrot2.core.ProcessingResult;
import org.carrot2.source.xml.XmlDocumentSource;
import org.junit.Test;


/**
 * This example shows how to cluster a set of documents available as an {@link ArrayList}.
 * This setting is particularly useful for quick experiments with custom data for which
 * there is no corresponding {@link IDocumentSource} implementation. For production use,
 * it's better to implement a {@link IDocumentSource} for the custom document source, so
 * that e.g., the {@link Controller} can cache its results, if needed.
 *
 */
public class ClusteringDocumentList
{

    public static Logger logger = Logger.getLogger(ClusteringDocumentList.class);


    @Test
    public void test()
    {

        {

            String internationalPath = "file/excel/international_everyConference.txt";
            String nationalPath = "file/excel/national_everyConference.txt";

            String content = FileOperation.read(internationalPath);

            /* Prepare Carrot2 documents */
            final ArrayList<Document> documents = new ArrayList<Document>();
            for (String line : content.split("\n"))
            {
                if(line.contains("##")) {
                    documents.add(new Document(line.split("##")[0], line.split("##")[1]));// row[2],
                }else{
                    logger.info(line);
                }
            }

            /* A controller to manage the processing pipeline. */
            final Controller controller = ControllerFactory.createSimple();

            /*
             * Perform clustering by topic using the Lingo algorithm. Lingo can 
             * take advantage of the original query, so we provide it along with the documents.
             */
            final ProcessingResult byTopicClusters = controller.process(documents, "data mining",
                LingoClusteringAlgorithm.class);
            final List<Cluster> clustersByTopic = byTopicClusters.getClusters();
            
            /* Perform clustering by domain. In this case query is not useful, hence it is null. */
            final ProcessingResult byDomainClusters = controller.process(documents, null,
                ByUrlClusteringAlgorithm.class);
            final List<Cluster> clustersByDomain = byDomainClusters.getClusters();
            // [[[end:clustering-document-list]]]

            ConsoleFormatter.displayClusters(clustersByTopic);
            ConsoleFormatter.displayClusters(clustersByDomain);
       }
    }
}
