import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.neo4j.structure.Neo4jGraph;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;

import static java.lang.System.out;

/**
 * Created by arvind on 3/6/17.
 */
public class IdsGraphQuery {

    private Graph graph;

    private GraphTraversalSource g;

    public IdsGraphQuery(Graph graph) {
        this.graph = graph;
        g = graph.traversal();
    }

    public static void main(String[] args) throws Exception {
        String neo4jGraphLocation = "/home/arvind/apps/neo4j-community-3.2.0/data/databases/ids.db";

        Configuration config = new BaseConfiguration();
        config.setProperty(Neo4jGraph.CONFIG_DIRECTORY, neo4jGraphLocation);
        config.setProperty("gremlin.neo4j.conf.dbms.allow_format_migration", "true");

        Graph graph = Neo4jGraph.open(config);

        IdsGraphQuery idsGraphQuery = new IdsGraphQuery(graph);
        idsGraphQuery.runQueries();
        graph.close();
    }

    private void runQueries() {
        getVertexCount();
    }

    private void getVertexCount() {
        int count = g.V().toList().size();
        out.println("Vertex count: " + count);
    }
}
