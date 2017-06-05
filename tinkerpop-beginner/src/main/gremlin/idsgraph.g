graph = TinkerGraph.open()
graph.io(IoCore.graphson()).readGraph("/home/arvind/Workspace/github/arvindgiri/graphdb/tinkerpop-beginner/src/main/resources/idsGraph.json")
g=graph.traversal() // Get traversal object
g.V().toList().size() // Print count of vertex
g.V().valueMap() // Get all vertex
g.V("1").valueMap() //get vertex by ID
g.V().group().by(bothE().count()) // Get count of vertex by number of edges
g.V().hasLabel('user').out("does").out("next").path()
