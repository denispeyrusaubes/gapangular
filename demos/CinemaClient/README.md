Cinema Client

## Configuration

Configure gapangular correctly in _index.html_
set the defaultRoot field, depending on where you have deployed 
the Cinema server.

Note: endPoints are deployed on a different webserver than the client
application (if they are both deployed on locahost, there are 2 servers
running differents ports)

## Run the webserver

```
npm install
bower install
grunt serve
```

[Cinema Application](http://localhost:9000/)