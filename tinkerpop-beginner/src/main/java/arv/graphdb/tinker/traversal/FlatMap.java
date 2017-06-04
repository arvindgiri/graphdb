package arv.graphdb.tinker.traversal;

import org.apache.tinkerpop.gremlin.process.traversal.Traverser;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerVertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;

/**
 * Created by arvind on 3/6/17.
 */
public class FlatMap {
    public static void main(String[] args) {
        //TinkerGraph graph = TinkerFactory.createModern();
        Graph graph = TinkerGraph.open();
        Vertex marko = graph.addVertex("name", "Prince Arora", "age", 29);
        Vertex lop = graph.addVertex("name", "Raj Kamal", "lang", "java");
        marko.addEdge("created", lop, "weight", 0.6d);

        GraphTraversalSource g = graph.traversal();
        GraphTraversal gt = g.V();

        Function f = new ArvMapFunctionImpl();

        GraphTraversal gt2 = g.V().flatMap(t -> {
            ArrayList<String> a = new ArrayList<String>();
            a.add("hi");
            a.add("hello");
            return a.iterator();
        });
        System.out.println(gt2.toList());
        //Use Map
        System.out.println(g.V().flatMap(f).toList());
    }


    static class ArvMapFunctionImpl implements Function<Traverser<TinkerVertex>, Iterator<String>> {
        public Iterator<String> apply(Traverser<TinkerVertex> source) {
            TinkerVertex v = source.get();

            return Arrays.asList(v.property("name").toString().split(" ")).iterator();
        }
    }
}
