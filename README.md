VRRDL
=================
VRRDL (Vehicle Related Road Debris Locator) is a software system designed to detect and notify users when they are approaching debris on the road.

VRRDL Web Service
=================
VRRDL-WS is a Java based RESTful web service built using [Jersey](http://jersey.java.net) a REST JAX-RS framework. JSON is used the data interchange mechanism using using [Jackson](http://jackson.codehaus.org).


Installing VRRDL Web Service
============================
VRRDL-WS can be built using [Maven 3.x](http://maven.apache.org). A WAR will be generated in your local ~/.m2 cache and can be deployed in any Servlet 2.x compliant Java application server - (e.g. [Tomcat](http://tomcat.apache.org), [Jetty](http://jetty.codehaus.org) )

Using VRRDL Web Service
=======================
After the vvrdl-ws has been deployed you can create/read/delete records as follows: 

*/debris

curl -X PUT -HContent-type:application/json --data @debris.json http://<BASE_URI>:<PORT>/<CONTEXT-NAME>/debris

Where debris.json is a file containing the following JSON structure: 
```json
{"timestamp":"2013-02-18T22:38:20.185-08:00","latitude":"37.174872","longitude":"-178.532039","speed":"40.0","uid":"1234ABCD"}
```
A successful response will return HTTP 201 followed by a location header with the URL to the document created containing the geohash - 
Location: http://<BASE_URI>:<PORT>/<CONTEXT-NAME>/debris/geohash/{geohash}

*/debris/geohash/{geohash}

curl http://<BASE_URI>:<PORT>/<CONTEXT-NAME>/debris/geohash/j8hu5vegdxgk
```json
{"latitude":37.174872,"longitude":-178.532039,"speed":40.0,"uid":"123312231","timestamp":"2013-02-19T06:38:20.185+0000"}
```

*/debris/bulk

curl -X PUT -HContent-type:application/json --data @debrisArray.json http://<BASE_URI>:<PORT>/<CONTEXT-NAME>/debris/bulk

Where debrisArray.json is a file containing the following JSON array of Debris objects: 
```json
[{"timestamp":"2013-02-18T22:38:20.185-08:00","latitude":"37.174872","longitude":"-178.532039","speed":"40.0","uid":"123312231"},{"latitude":39.174872,"longitude":-
178.532039,"speed":40.0,"uid":"123312231","timestamp":"2013-02-19T06:38:20.185+0000"},{"latitude":39.174872,"longitude":-178.532039,"speed":40.0,"uid":"123312231","
timestamp":"2013-02-19T06:38:20.185+0000"}]
```

*/proximity/latitude/{lat}/longitude/{lng}/radius/{radius}

curl http://<BASE_URI>:<PORT>/<CONTEXT-NAME>/proximity/latitude/36.174872/longitude/-178.532039/radius/1000.0/
```json
[{"timestamp":"2013-02-18T22:38:20.185-08:00","latitude":"37.174872","longitude":"-178.532039","speed":"40.0","uid":"123312231"},{"latitude":39.174872,"longitude":-
178.532039,"speed":40.0,"uid":"123312231","timestamp":"2013-02-19T06:38:20.185+0000"},{"latitude":39.174872,"longitude":-178.532039,"speed":40.0,"uid":"123312231","
timestamp":"2013-02-19T06:38:20.185+0000"}]
```

*/distance/latitude/{lat1}/longitude/{lng1}/latitude2/{lat2}/longitude2/{lng2}

curl http://<BASE_URI>:<PORT>/<CONTEXT-NAME>/distance/latitude/47.174872/longitude/-178.532039/latitude2/37.174872/longitude2/-178.532039

```json
{"distance":1111.950837241914}
```
