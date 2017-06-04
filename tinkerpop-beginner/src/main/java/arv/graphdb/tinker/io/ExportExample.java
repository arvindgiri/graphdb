package arv.graphdb.tinker.io;

import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.io.IoCore;
import org.apache.tinkerpop.gremlin.structure.io.Mapper;
import org.apache.tinkerpop.gremlin.structure.io.graphson.GraphSONVersion;
import org.apache.tinkerpop.gremlin.structure.io.graphson.TypeInfo;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by arvind on 3/6/17.
 */
public class ExportExample {

    // 3 users
    // Arvind downloads Photoshop, Illustrator and Acrobat
    // Prince downloads Photoshop, Illustrator and Acrobat
    // Raj downloads Dreamweaver, Muse

    public static void saveIdsGraph() throws Exception {
        Graph graph = TinkerGraph.open();
        //Add users
        Vertex arvind = graph.addVertex("label", "user", "userguid", "AAD134ASDFASD", "name", "Arvind", "geo", "India");
        Vertex prince = graph.addVertex("label", "user", "userguid", "AAD134ASDFASD", "name", "Prince", "geo", "India");
        Vertex raj = graph.addVertex("label", "user", "userguid", "AAD134ASDFASD", "name", "Raj", "geo", "India");

        //Add downloads
        //Arvind
        Vertex v = graph.addVertex("label", "Event", "event_category", "download", "event_dts", "2017-01-01", "product", "photoshop");
        arvind.addEdge("downloads", v, "event_dts", "2017-01-01");


    }

    public static void main(String[] args) throws Exception {
        saveIdsGraph();
    }

    public static void saveGraph(Graph graph, String filePath) throws Exception {
        Mapper mapper = graph.io(IoCore.graphson()).mapper().
                version(GraphSONVersion.V2_0).
                typeInfo(TypeInfo.NO_TYPES).create();
        File file = new File(filePath);
        System.out.println(file.getAbsolutePath());
        file.createNewFile();
        FileOutputStream f = new FileOutputStream(file);
        graph.io(IoCore.graphson()).writer().mapper(mapper).create().writeObject(f, graph);
        f.close();
    }
}
