[![Codacy Badge](https://app.codacy.com/project/badge/Grade/f2b671835fb845babc75e965cd7c06dd)](https://app.codacy.com/gh/AlekseiPetrovJ/hinkali-cloud/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)

# Приложение для заказа Хинкали

Реализована асинхронная отправка объекта ActiveMQ (Artemis)
## REST API спроектирован и разработан на стеке:

-   Spring Data JPA (H2 SQL)
-   Spring Data REST
-   Cassandra
-   MongoDB
-   Spring Boot
-   Spring validator
-   Spring MVC
-   Maven
-   Junit
-   ActiveMQ (Artemis)

### Запуск ActiveMQ (Artemis)
docker run --detach --name mycontainer -p 61616:61616 -p 8161:8161 --rm apache/activemq-artemis:latest-alpine

### Запуск СУБД Cassandra
Запуск образа с пробросом порта 9042:

`docker run --name my-cassandra -p 9042:9042 -d cassandra:latest`

Создание keyspace hinkalicloud

`cqlsh> create keyspace hinkalicloud with replication={'class':'SimpleStrategy', 'replication_factor':1} and durable_writes=true;`