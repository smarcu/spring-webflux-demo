# spring-webflux-demo
Spring WebFlux Demo

## Info
Project showing a reactive example of handling realtime sensor data
Example project used in presentation https://github.com/smarcu/ReactivePresentation/blob/master/Reactive.pdf


## Reference articles
 * https://spring.io/blog/2017/02/23/spring-framework-5-0-m5-update
 * https://spring.io/blog/2016/07/20/notes-on-reactive-programming-part-iii-a-simple-http-server-application
 * https://www.w3schools.com/html/html5_serversentevents.asp
 * https://www.youtube.com/watch?v=2To3_mYT2hc

## How to run
in order to run on pivotal cloud, create an account, update the project name in manifest.yml
- run "cf push project_name" to deploy on cloud
- view local page at http://localhost:8080/index.html

## Git tags for presentation:
* STEP-1
    * add server method @GetMapping
    * add javascript consuming "text/event-stream" (server sent events)

* STEP-2
    * add spring integration (channel message)
    
* STEP-3
    * implement gyroscope on javascript, send events from browser
    * 
* STEP-4
	* link input messages using inputChannel - flux - outputChannel
	* calculate average on input values
    
