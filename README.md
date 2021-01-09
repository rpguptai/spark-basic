## How to install

- install Docker
- either clone the repo or download as zip
- open with IntelliJ as an SBT project
- in a terminal window, navigate to the folder where you downloaded this repo and run `docker-compose up` to build and start the PostgreSQL container - we will interact with it from Spark
- in another terminal window, navigate to `spark-cluster/` and build the Docker-based Spark cluster with
```
chmod +x build-images.sh
./build-images.sh
```
- when prompted to start the Spark cluster, go to the `spark-cluster` folder and run `docker-compose up --scale spark-worker=3` to spin up the Spark containers

### Postgre issue solution:

Connect to a bash inside the docker:

docker exec -it postgres bash

Then go to the folder where the SQL file we have in the course is mounted:

cd docker-entrypoint-initdb.d

Then run the sql file as an input to the postgres database:

cat db.sql | psql -U docker

docker cp (the sql file) postgres:/docker-entrypoint-initdb.d (if required)

# Postgre Connection

while connecting to Postgre check

- docker-machine ip default 
and put that ip then localhost in case of connection issue
