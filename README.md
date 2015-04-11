NettyBase
=========
Basic Netty server for fast kick off

Requirements
---------
- JDK 7
- Apache Maven 3

Running from CLI
---------
    mvn clean package
    java -jar target/netty-base-0.0.3-SNAPSHOT.jar

Features
--------
- Netty bootstrap configuration
- Support of HTTP
- Routing configured from Spring Context
- Slf4j + Log4j for logging
- mvn package wraps all dependencies into a single jar

Backlog
--------
- Support SSL
- Support regular expressions for routing
- Add basic business logic: {word: number of occurrences}
- Integrate with Database
- Write unit tests for routing and handlers
- Implement Web client in AngularJS
- Implement iOS client

Links
--------
- http://netty.io/wiki/
- Handling HTTP: http://netty.io/4.0/xref/io/netty/example/http/snoop/package-summary.html
- Spring IoC http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html
