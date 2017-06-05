package com.adobe.ids.cjass.service.dataload;

import com.adobe.ids.cjass.constant.CjaasConst;
import com.adobe.ids.cjass.constant.E;
import com.adobe.ids.cjass.constant.P;
import com.adobe.ids.cjass.utility.ImportExportUtility;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;

import java.util.Iterator;
import java.util.Map;

import static java.lang.System.out;

//import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.has; //Don't delete it. Needed for reference

/**
 * Created by arvind on 3/6/17.
 */
public class DataLoaderService implements IDataLoaderService {

    private Graph graph;

    private boolean supportsUserSuppliedIDs;

    DataLoaderService(Graph graph) {
        this.graph = graph;
        supportsUserSuppliedIDs = graph.features().vertex().supportsUserSuppliedIds();
    }

    @Override
    public void updateUserInfo(IUserInfo userInfo) {
        Vertex userVertex = createVertex(CjaasConst.LABEL_USER, userInfo.getId());
        //userVertex.property(P.ID, userInfo.getId());
        addProperty(userVertex, P.NAME, userInfo.getName());
        addProperty(userVertex, P.JOINING_DTS, userInfo.getUserJoiningDate());
        Iterator<Map.Entry<String, String>> iterator = userInfo.getProperties();
        while (iterator.hasNext()) {
            Map.Entry<String, String> mapRecord = iterator.next();
            addProperty(userVertex, mapRecord.getKey(), mapRecord.getValue());
        }
        commitTransaction();
    }

    /**
     * Algorithm
     * Get the last event node, traverse back and add at right position according to eventDts
     *
     * @param event
     */
    @Override
    public void addEvent(IEvent event) {
        Vertex eventVertex = getEventVertex(event);
        GraphTraversalSource g = graph.traversal();
        Vertex userVertex = getVertex(CjaasConst.LABEL_USER, event.getUserId());
        userVertex.addEdge(E.DOES, eventVertex);
        //TODO Create the user if doesn't exist
        // Find the last event, if it doesn't exist then create one.
        // TODO Change the LAST_INSERTED_EVENT with LAST_DTS_EVENT
        Edge lastEventEdge = getLastEventEdge(userVertex);
        if (lastEventEdge == null) {
            //This is first event
            lastEventEdge = userVertex.addEdge(E.LAST_INSERTED_EVENT, eventVertex);
        } else {
            insertEvent(userVertex, eventVertex, lastEventEdge);
        }
        commitTransaction();
        //TODO Implement edge to next instance of same event
    }

    private Edge getLastEventEdge(Vertex userVertex) {
        Edge lastEventEdge = null;
        Iterator<Edge> itr = userVertex.edges(Direction.OUT, E.LAST_INSERTED_EVENT);
        if (itr.hasNext()) {
            lastEventEdge = itr.next();
        }
        if (itr.hasNext()) {
            out.println("User (" + userVertex.id() + ") is having more than one lastEventEdge, while there should be only one");
        }
        return lastEventEdge;
    }

    /**
     * Inserts event vertex in the graph. User vertex is part of graph but event vertex is yet to be inserted
     *
     * @param userVertex
     * @param eventVertex
     * @param lastEventEdge
     */
    private void insertEvent(Vertex userVertex, Vertex eventVertex, Edge lastEventEdge) {
        //TODO find the position and update the pointers
        // For time being I am just inserting the record at the end without checking event dts
        Vertex lastEventVertex = lastEventEdge.inVertex();
        lastEventEdge.remove(); // Can we simply replace the new event as
        lastEventVertex.addEdge(E.NEXT, eventVertex);
        userVertex.addEdge(E.LAST_INSERTED_EVENT, eventVertex);
    }

    private Vertex getEventVertex(IEvent event) {
        Vertex eventVertex = createVertex(CjaasConst.LABEL_EVENT, event.getEventGuid());
        addProperty(eventVertex, P.USER_ID, event.getUserId());
        addProperty(eventVertex, P.CATEGORY, event.getCategory());
        addProperty(eventVertex, P.EVENT_DTS, event.getEventDts().toString());

        Iterator<Map.Entry<String, String>> iterator = event.getProperties();
        while (iterator.hasNext()) {
            Map.Entry<String, String> mapRecord = iterator.next();
            addProperty(eventVertex, mapRecord.getKey(), mapRecord.getValue());
        }
        return eventVertex;
    }

    private Vertex createVertex(String label, String id) {
        Vertex vertex;
        if (supportsUserSuppliedIDs) {
            vertex = graph.addVertex(T.label, label, T.id, id);
        } else {
            vertex = graph.addVertex(T.label, label);
            vertex.property(P.ID, id);
        }
        return vertex;
    }

    private Vertex getVertex(String label, String id) {
        Vertex vertex;
        if (supportsUserSuppliedIDs) {
            vertex = graph.traversal().V(id).hasLabel(label).next();
        } else {
            //Not tested
            vertex = graph.traversal().V().has(P.ID, id).hasLabel(label).next();
        }
        return vertex;

    }

    private void addProperty(Element element, String key, Object value) {
        if (key != null && value != null) {
            element.property(key, value);
        } else {
            out.println("Either key or value is null. Element: " + element
                    + ", (" + key + ":" + value + ")");
        }
    }

    private void commitTransaction() {
        if (graph.features().graph().supportsTransactions()) {
            graph.tx().commit();
        }
        //For testing
        ImportExportUtility.saveGraphJson(graph, CjaasConst.GRAPH_FILE_LOCATION);
    }

    @Override
    public void setGraph(Graph graph) {
        this.graph = graph;
    }
}
