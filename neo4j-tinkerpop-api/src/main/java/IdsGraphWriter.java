import com.adobe.ids.cjass.service.dataload.*;
import org.apache.tinkerpop.gremlin.neo4j.structure.Neo4jGraph;
import org.apache.tinkerpop.gremlin.structure.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

/**
 * Created by arvind on 3/6/17.
 */
public class IdsGraphWriter {

    private Graph graph;

    private IDataLoaderService dataLoaderService;

    public IdsGraphWriter(Graph graph) {
        this.graph = graph;
        System.out.println(" ID Suported: " + graph.features().vertex().supportsAnyIds() + graph.features().vertex().supportsCustomIds());
        dataLoaderService = DataLoaderFactory.getInstance().getDataLoaderService(graph);
        addUsers();
        addEvents();

    }

    public static void main(String[] args) throws Exception {
        String neo4jGraphLocation = "/home/arvind/apps/neo4j-community-3.2.0/data/databases/ids.db";
        File file = new File(neo4jGraphLocation);
        delete(file);
        Graph graph = Neo4jGraph.open(neo4jGraphLocation);
        IdsGraphWriter idsGraph = new IdsGraphWriter(graph);
        graph.close();
    }

    private static void delete(File f) throws IOException {
        if (f.isDirectory()) {
            for (File c : f.listFiles())
                delete(c);
        }
        if (!f.delete())
            throw new FileNotFoundException("Failed to delete file: " + f);
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
        event.setProperty("name", "F2P");
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
