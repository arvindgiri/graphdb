package arv.graphdb.tinker.traversal;

import org.apache.tinkerpop.gremlin.process.traversal.Traverser;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

import java.util.function.Consumer;

/**
 * Created by arvind on 3/6/17.
 */
public class Branch {
    public static void main(String[] args) {
        //TinkerGraph graph = TinkerFactory.createModern();
        Graph graph = TinkerGraph.open();
        Vertex marko = graph.addVertex("name", "Prince Arora", "age", 29);
        Vertex lop = graph.addVertex("name", "Raj Kamal", "lang", "java");
        marko.addEdge("created", lop, "weight", 0.6d);

        GraphTraversalSource g = graph.traversal();
        GraphTraversal gt = g.V();

        Consumer c = new ArvConsumerImpl();

        //GraphTraversal gt2 = g.V().sideEffect(t -> System.out.println(t.get().property("name")));
        //System.out.println(gt2.valueMap().toList());
        //Use Map
        //System.out.println(g.V().branch(c).valueMap().toList());
    }

    /*

    public default <M, E2> GraphTraversal<S, E2> branch(final Function<Traverser<E>, M> function) {
        this.asAdmin().getBytecode().addStep(GraphTraversal.Symbols.branch, function);
        final BranchStep<E, E2, M> branchStep = new BranchStep<>(this.asAdmin());
        branchStep.setBranchTraversal((Traversal.Admin<E, M>) __.map(function));
        return this.asAdmin().addStep(branchStep);
    }

    public default <E2> GraphTraversal<S, E2> map(final Function<Traverser<E>, E2> function) {
        this.asAdmin().getBytecode().addStep(GraphTraversal.Symbols.map, function);
        return this.asAdmin().addStep(new LambdaMapStep<>(this.asAdmin(), function));
    }
    */

    static class ArvConsumerImpl implements Consumer<Traverser<Element>> {
        public void accept(Traverser<Element> t) {
            System.out.println(t.get().property("name"));
        }
    }
}
