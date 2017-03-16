# spring-webflux-demo
Spring WebFlux Demo

## Info
Project showing a reactive example of handling realtime sensor data

## Reference articles
 * https://spring.io/blog/2017/02/23/spring-framework-5-0-m5-update
 * https://spring.io/blog/2016/07/20/notes-on-reactive-programming-part-iii-a-simple-http-server-application
 * https://www.w3schools.com/html/html5_serversentevents.asp

## How to run
in order to run on pivotal cloud, create an account, update the project name in manifest.yml
- run "cf push <project name>" to deploy on cloud

## Git tags for presentation:
* STEP-1
    * add server method @GetMapping
    * add javascript consuming "text/event-stream" (server sent events)
