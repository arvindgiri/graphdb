package arv.graphdb.tinker.traversal;

import org.apache.tinkerpop.gremlin.process.traversal.Traverser;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

import java.util.function.Function;

import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.values;

/**
 * Created by arvind on 3/6/17.
 */
public class Map {
    public static void main(String[] args) {
        //TinkerGraph graph = TinkerFactory.createModern();
        Graph graph = TinkerGraph.open();
        Vertex prince = graph.addVertex("name", "Prince", "age", 29);
        Vertex raj = graph.addVertex("name", "Raj", "lang", "java");
        Vertex rocky = graph.addVertex("name", "Rocky", "lang", "java");
        Vertex vivek = graph.addVertex("name", "Vivek", "lang", "java");
        prince.addEdge("knows", raj, "weight", 0.6d);
        raj.addEdge("knows", vivek, "weight", 0.6d);
        raj.addEdge("knows", rocky, "weight", 0.6d);


        GraphTraversalSource g = graph.traversal();
        GraphTraversal gt = g.V();

        //map(Function)
        Function f = new ArvMapFunctionImpl();

        GraphTraversal gt2 = g.V().map(v -> {
            return "1";
        });
        System.out.println(gt2.toList());
        //Use Map
        System.out.println(g.V().map(f).toList());

        //map(Traversal)
        System.out.println("*************************");
        System.out.println(g.V().map(values("name")).toList());
    }


    static class ArvMapFunctionImpl implements Function<Traverser, String> {
        public String apply(Traverser source) {
            return "1";
        }
    }
}
