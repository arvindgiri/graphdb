You may play with graph DB with below commands to start with for below graph.



1. Go to Titan machine ('Maddy')
            ssh graph@Maddy
            password: graph

2. Go to Titan directory
            cd /home/graph/apps/titan-1.0.0-hadoop1
            or cd apps/titan-1.0.0-hadoop1

3. Check and Start Titan

            Do jps, if CassandraDaemon, Elasticsearch and GremlinServer are running then skip step#3.2

            3.1
                        graph@Maddy:~$ jps
                        1156 CassandraDaemon
                        2069 Jps
                        1533 Elasticsearch
                        1663 GremlinServer

            3.2       bin/titan.sh start


4. Graph traversal
            4.1       bin/gremlin.sh
            4.2       graph =TitanFactory.open('conf/titan-cassandra-es.properties')
            4.3      GraphOfTheGodsFactory.load(graph)
            4.4       g = graph.traversal()
            4.5       saturn = g.V().has('name', 'saturn').next()
            4.6       g.V(saturn).valueMap()
            4.7           g.V(saturn).in('father').in('father').values('name')

Source:http://s3.thinkaurelius.com/docs/titan/1.0.0/getting-started.html
