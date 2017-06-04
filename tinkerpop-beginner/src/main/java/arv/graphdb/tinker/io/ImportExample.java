package arv.graphdb.tinker.io;

import com.adobe.ids.cjass.constant.CjaasConst;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.io.IoCore;
import org.apache.tinkerpop.gremlin.structure.io.graphml.GraphMLIo;
import org.apache.tinkerpop.gremlin.structure.io.graphson.GraphSONIo;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

/**
 * Created by arvind on 3/6/17.
 */
public class ImportExample {

    // 3 users
    // Arvind downloads Photoshop, Illustrator and Acrobat
    // Prince downloads Photoshop, Illustrator and Acrobat
    // Raj downloads Dreamweaver, Muse

    public static Graph loadGraph() throws Exception {
        Graph graph = TinkerGraph.open();

        GraphSONIo graphIo = graph.io(IoCore.graphson());
        graphIo.readGraph(CjaasConst.GRAPH_FILE_LOCATION);
        System.out.println("Done");
        return graph;
    }

    public static void main(String[] args) throws Exception {
        Graph graph = loadGraph();
    }


}
