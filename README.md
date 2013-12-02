VRRDL
=================
VRRDL (Vehicle Related Road Debris Locator) is a software system designed to detect and notify users when they are approaching debris on the road.

[![Build Status](https://buildhive.cloudbees.com/job/x86Azul/job/vrrdl-ws/badge/icon)](https://buildhive.cloudbees.com/job/x86Azul/job/vrrdl-ws/)

VRRDL Web Service
=================
VRRDL-WS is a Java based RESTful web service built using [Jersey](http://jersey.java.net) a REST JAX-RS framework. JSON is used as the data interchange mechanism using [Jackson](http://jackson.codehaus.org).


RESTful API Summary
===================

|Restful Endpoint|HTTP Method|HTTP Status Code on Success|HTTP Status Code on Error|
| ------------- |-------------|-----|-------|
|/|GET|200|NA|
|/debris|PUT|201 for new data. 303 for existing data |400|
|/debris/geohash/{geohash}|GET|200|404|
|/debris/geohash/{geohash}|DELETE|200|404|
|/debris/bulk|PUT|200|400|
|/distance/latitude/{lat1}/longitude/{lng1}/latitude2/{lat2}/longitude2/{lng2}|GET|200|400|
|/proximity/latitude/{latitude}/longitude/{longitude}/radius/{radius}|GET|200|400|






