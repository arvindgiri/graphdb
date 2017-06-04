import com.adobe.ids.cjass.service.dataload.*;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

import java.util.Date;

/**
 * Created by arvind on 3/6/17.
 */
public class IdsGraph {

    private Graph graph;

    private IDataLoaderService dataLoaderService;

    public IdsGraph(Graph graph) {
        this.graph = graph;
        System.out.println(" ID Suported: " + graph.features().vertex().supportsAnyIds() + graph.features().vertex().supportsCustomIds());
        dataLoaderService = DataLoaderFactory.getInstance().getDataLoaderService(graph);
        addUsers();
        addEvents();

    }

    public static void main(String[] args) throws Exception {
        Graph graph = TinkerGraph.open();
        IdsGraph idsGraph = new IdsGraph(graph);

    }

    private void addUsers() {
        IUserInfo userInfo = new UserInfo("1", "Arvind");
        dataLoaderService.updateUserInfo(userInfo);
        userInfo = new UserInfo("2", "Prince");
        dataLoaderService.updateUserInfo(userInfo);
        userInfo = new UserInfo("3", "Raj");
        dataLoaderService.updateUserInfo(userInfo);
    }

    private void addEvents() {
        addEventsArvind("1");
        addEventsPrince("2");
        addEventsRaj("3");
    }

    private void addEventsArvind(String userID) {
        IEvent event = new Event("AD1", userID, "download", new Date());
        event.setProperty("product", "photoshop");
        dataLoaderService.addEvent(event);

        event = new Event("AD2", userID, "download", new Date());
        event.setProperty("product", "illustrator");
        dataLoaderService.addEvent(event);

        event = new Event("AD3", userID, "download", new Date());
        event.setProperty("product", "acrobat");
        dataLoaderService.addEvent(event);

        event = new Event("AD4", userID, "f2p", new Date());
        dataLoaderService.addEvent(event);
    }

    private void addEventsPrince(String userID) {
        IEvent event = new Event("PD1", userID, "download", new Date());
        event.setProperty("product", "photoshop");
        dataLoaderService.addEvent(event);

        event = new Event("PD2", userID, "download", new Date());
        event.setProperty("product", "illustrator");
        dataLoaderService.addEvent(event);

        event = new Event("PD3", userID, "download", new Date());
        event.setProperty("product", "acrobat");
        dataLoaderService.addEvent(event);

    }

    private void addEventsRaj(String userID) {
        IEvent event = new Event("RD1", userID, "download", new Date());
        event.setProperty("product", "dreamweaver");
        dataLoaderService.addEvent(event);

        event = new Event("RD2", userID, "download", new Date());
        event.setProperty("product", "muse");
        dataLoaderService.addEvent(event);
    }


}
