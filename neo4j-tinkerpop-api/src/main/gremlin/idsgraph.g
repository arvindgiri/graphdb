graph = TinkerGraph.open()
graph.io(IoCore.graphson()).readGraph("/home/arvind/Workspace/github/arvindgiri/graphdb/tinkerpop-beginner/src/main/resources/idsGraph.json")
g=graph.traversal() // Get traversal object
g.V().toList().size() // Print count of vertex
g.V().valueMap() // Get all vertex
g.V("1").valueMap() //get vertex by ID


//Neo4j
:install org.apache.tinkerpop neo4j-gremlin 3.2.4
(restart)
:plugin use tinkerpop.neo4j

config = new BaseConfiguration()
config.setProperty("gremlin.neo4j.directory", "/home/arvind/apps/neo4j-community-3.2.0/data/databases/ids.db")
//config.setProperty("gremlin.neo4j.conf.dbms.allow_format_migration", "true")
//config.setProperty("dbms.allow_format_migration", "true")

graph = Neo4jGraph.open(config)
g=graph.traversal() // Get traversal object

g.V().toList().size() // Print count of vertex
g.V().valueMap() // Get all vertex
g.V("1").valueMap() //get vertex by ID
g.V().groupCount('a').by(label).cap('a') //Get count of all vertex
g.V().group().by(bothE().count()) // Get count of vertex by number of edges
g.V().hasLabel('user').out("does").out("next").path().by('id') //Prints path up to two initial downloads
inject(1,2).map {it.get() + 1} // If you don't have a graph to play with

//Group products by user Id where user is free to paid
g.V().hasLabel('user').match(__.as('a').out('does').has('name','F2P')).select('a').out('does').has('category','download').group().by('userId').by('product')

//Get user count by downloaded product set
g.V().hasLabel('user').local(out('does').has('category','download').order().by('product').fold('|') {a,b -> a + b.property('product').value() + '|'}).groupCount()

// For free to paid users: get user count by downloaded product set
g.V().hasLabel('user').match(__.as('a').out('does').has('name','F2P')).select('a').local(out('does').has('category','download').order().by('product').fold('|') {a,b -> a + b.property('product').value() + '|'}.as('p_name')).groupCount()

// For (not) free to paid users: get user count by downloaded product set
g.V().hasLabel('user').match(__.as('a').not(__.as('b').out('does').has('name','F2P'))).select('a').local(out('does').has('category','download').order().by('product').fold('|') {a,b -> a + b.property('product').value() + '|'}.as('p_name')).groupCount()

//Test
g.V().hasLabel('user').match(__.as('a').out('does').has('name','F2P')).out('does').has('category','download').order().by('product').fold('|') {a,b -> a + b.property('product').value() + '|'}.as('p_name'))

//Get count by product list for free to paid users
g.V().hasLabel('user').local(out('does').has('category','download').order().by('product').fold('|') {a,b -> a.property('product') + b.property('product') + '|'}


