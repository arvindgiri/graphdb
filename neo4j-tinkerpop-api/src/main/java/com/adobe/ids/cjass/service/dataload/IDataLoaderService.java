package com.adobe.ids.cjass.service.dataload;

import com.adobe.ids.cjass.service.common.IService;
import org.apache.tinkerpop.gremlin.structure.Graph;

/**
 * Created by arvind on 3/6/17.
 */
public interface IDataLoaderService extends IService {

    void addEvent(IEvent event);

    void updateUserInfo(IUserInfo userInfo);

    void setGraph(Graph graph);

}
