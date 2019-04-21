# circuit-breaker-demo

Open h2 console
http://localhost:8443/circuit-breaker-demo/h2-console
    param - url : jdbc:h2:mem:AZ;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=TRUE
          - user : sa
          - password : 

Open Hystrix dashboard
http://localhost:8443/circuit-breaker-demo/hystrix
    param - url : http://localhost:8443/circuit-breaker-demo/actuator/hystrix.stream

Curl
- create bucket
`curl -X POST \
  http://localhost:8443/circuit-breaker-demo/buckets \
  -H 'Content-Type: application/json' \
  -d '{
	"customerName": "non",
	"stuffData": [
		{
			"name": "apple",
			"price": 15.0
		},
		{
			"name": "donut",
			"price": 25.0
		},
		{
			"name": "toast",
			"price": 58.0
		}
	]
}'`

- get buckets
`curl -X GET \
   http://localhost:8443/circuit-breaker-demo/buckets`
   
- get bucket with customerName
`curl -X GET \
   http://localhost:8443/circuit-breaker-demo/buckets/adam`

- get stuffs
`curl -X GET \
   http://localhost:8443/circuit-breaker-demo/stuffs`
   
- get stuffs with customerName
`curl -X GET \
   http://localhost:8443/circuit-breaker-demo/buckets/non` 
   
- get buckets with customerName and circuit-breaker
`curl -X GET \
   http://localhost:8443/circuit-breaker-demo/buckets/circuit/dene`