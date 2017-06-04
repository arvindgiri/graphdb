package arv.graphdb.tinker.traversal;

import org.apache.tinkerpop.gremlin.process.traversal.Traverser;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

import java.util.function.Predicate;

/**
 * Created by arvind on 3/6/17.
 */
public class Filters {
    public static void main(String[] args) {
        //TinkerGraph graph = TinkerFactory.createModern();
        Graph graph = TinkerGraph.open();
        Vertex marko = graph.addVertex("name", "Prince Arora", "age", 29);
        Vertex lop = graph.addVertex("name", "Raj Kamal", "lang", "java");
        marko.addEdge("created", lop, "weight", 0.6d);

        GraphTraversalSource g = graph.traversal();
        GraphTraversal gt = g.V();

        Predicate p = new ArvPredicateImpl();

        GraphTraversal gt2 = g.V().filter(t -> t.get().property("name").toString().contains("Raj"));
        System.out.println(gt2.valueMap().toList());
        //Use Map
        System.out.println(g.V().filter(p).valueMap().toList());
    }


    static class ArvPredicateImpl implements Predicate<Traverser<Element>> {
        public boolean test(Traverser<Element> t) {
            return t.get().property("name").toString().contains("Prince");
        }
    }
}
