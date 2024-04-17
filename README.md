# kafka-study

Run kafka container:
~~~
docker compose up -d
docker compose logs kafka | grep -i started
docker compose ps      
~~~

Connect into container to run kafka commands:
~~~
docker exec -it kafka-study-kafka-1 /bin/bash
~~~

Comandos do curso pra criar e listar topicos: 
~~~
kafka-topics 

kafka-topics --bootstrap-server localhost:9092 --list 

kafka-topics --bootstrap-server localhost:9092 --topic first_topic --create

kafka-topics --bootstrap-server localhost:9092 --topic second_topic --create --partitions 3

kafka-topics --bootstrap-server localhost:9092 --topic third_topic --create --partitions 3 --replication-factor 2

# Create a topic (working)
kafka-topics --bootstrap-server localhost:9092 --topic third_topic --create --partitions 3 --replication-factor 1

# List topics
kafka-topics --bootstrap-server localhost:9092 --list 

# Describe a topic
kafka-topics --bootstrap-server localhost:9092 --topic first_topic --describe

# Delete a topic 
kafka-topics --bootstrap-server localhost:9092 --topic first_topic --delete
# (only works if delete.topic.enable=true)

~~~


##### todo list
- ao final fazer uma poc com spring for kafka: https://spring.io/projects/spring-kafka, explorar o kafka-test
- https://docs.spring.io/spring-kafka/reference/quick-tour.html