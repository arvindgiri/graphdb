package com.adobe.ids.cjass.service.dataload;

import org.apache.tinkerpop.gremlin.structure.Graph;

/**
 * Created by arvind on 3/6/17.
 */
public class DataLoaderFactory {

    private static DataLoaderFactory ourInstance = new DataLoaderFactory();
    private IDataLoaderService dataLoaderService = null;

    private DataLoaderFactory() {
    }

    public static DataLoaderFactory getInstance() {
        return ourInstance;
    }

    public IDataLoaderService getDataLoaderService(Graph graph) {
        if(dataLoaderService == null) {
            synchronized (DataLoaderFactory.class) {
                if(dataLoaderService == null) {
                    dataLoaderService = new DataLoaderService(graph);
                }
            }
        }
        return dataLoaderService;
    }
}
