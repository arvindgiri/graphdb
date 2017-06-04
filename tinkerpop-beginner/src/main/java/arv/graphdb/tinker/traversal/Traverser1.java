package arv.graphdb.tinker.traversal;

import org.apache.tinkerpop.gremlin.process.traversal.Traverser;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

import java.util.function.Consumer;

/**
 * Created by arvind on 3/6/17.
 */
public class Traverser1 {
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
        System.out.println(g.V().toList());
        //map(Function)
        Consumer f = new ArvMapFunctionImpl();
        Object result = g.V().out().out().sideEffect(f).toList();
        System.out.println(result);
        result = g.V().sideEffect(__.outE().count().store("o")).
                sideEffect(__.inE().count().store("i")).cap("o","i").valueMap();
        System.out.println(result);
    }


    static class ArvMapFunctionImpl implements Consumer<Traverser> {
        public void accept(Traverser source) {
            System.out.println(source.path().toString());
        }
    }
}
