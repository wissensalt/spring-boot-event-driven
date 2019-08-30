# spring-boot-event-driven
Repository for spring-boot-event-driven

| Module | Description |
| ------ | ------ |
| API Gateway | Gateway for load balancer (if needed) |
| Service Discovery | Service to register sub modules (if needed) |


# Synchronous Process
<img src="https://github.com/KNIGHTMASTER/Resources/blob/master/SPRINGBOOT-EVEN-DRIVEN/design-spring-boot-event-driven.png?raw=true"></img>


# Event Driven (Asynchronous) Process
<img src="https://github.com/KNIGHTMASTER/Resources/blob/master/SPRINGBOOT-EVEN-DRIVEN/asynchronous-microservices.png?raw=true"></img>

# Prerequisites
-  JDK 1.8
-  PostgreSQL : Create several database using `database.sql` file
-  Apache Kafka 
```sh
# this will install java 1.8, zookeeper, and kafka
$ brew install kafka

# this will run ZK and kafka as services
$ brew services start zookeeper
$ brew services start kafka
```

# How to run
-  Start Zookeeper, then Kafka
-  Open `application.yml` Configure Database based on your own installation
```
db:
     name : db_name
     host : db_host
     port : 5432
     username : db_uname
     password : db_pass
     auto-reconnect : true
     ssl : false
```
- Run each main class using your own IDE as Spring Boot Application for `order-api`, `customer-api`, `inventory-api`, `payment-api`, `product-api`
- When all services already run, send payload through Rest Client into `http://localhost:8080/order/start` using this payload
```
{
	"transactionCode" : "01",
	"customer" : {
		"name" : "customer_01"
	},
	"order" : {
		"totalPrice" : 5000.0,
		"orderDetails" :[
			{
				"productId" : 1,
				"quantity" : 1,
				"price" : 5000.0
			}
			]
	},
	"payment" : {
		"paymentTotal" : 5000.0,
		"paymentType" : "cash",
		"paymentStatus" : "paid"
	}
}
```



Database design available here : https://dbdiagram.io/d/5d43e562ced98361d6dd3d01