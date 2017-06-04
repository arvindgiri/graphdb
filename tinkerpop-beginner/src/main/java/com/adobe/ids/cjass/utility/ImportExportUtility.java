package com.adobe.ids.cjass.utility;

import com.adobe.ids.cjass.constant.CjaasConst;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.io.IoCore;
import org.apache.tinkerpop.gremlin.structure.io.Mapper;
import org.apache.tinkerpop.gremlin.structure.io.graphson.GraphSONVersion;
import org.apache.tinkerpop.gremlin.structure.io.graphson.TypeInfo;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.System.out;

/**
 * Created by arvind on 3/6/17.
 */
public class ImportExportUtility {

    public static void main(String[] args) {
        final Graph graph = loadGraphJson(CjaasConst.GRAPH_FILE_LOCATION);
    }

    public static void saveGraphJson(Graph graph, String filePath) {
        try {
            graph.io(IoCore.graphson()).writeGraph(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while saving graph to file: " + filePath, e);
        }

    }

    public static Graph loadGraphJson(String filePath) {
        final Graph graph = TinkerGraph.open();
        try {
            graph.io(IoCore.graphson()).readGraph(CjaasConst.GRAPH_FILE_LOCATION);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while reading graph from json file");
        }
        out.println("Graph loaded. Total vertex:" + graph.traversal().V().toList().size());
        return graph;
    }


    public static void saveGraphJson2(Graph graph, String filePath) {
        try {
            Mapper mapper = graph.io(IoCore.graphson()).mapper().
                    version(GraphSONVersion.V2_0).
                    typeInfo(TypeInfo.NO_TYPES).create();
            File file = new File(filePath);
            out.println(file.getAbsolutePath());
            file.createNewFile();
            FileOutputStream f = new FileOutputStream(file);
            graph.io(IoCore.graphson()).writer().mapper(mapper).create().writeObject(f, graph);
            f.close();
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while saving graph to file: " + filePath, e);
        }
    }

}
