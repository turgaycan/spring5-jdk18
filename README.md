# spring5 & jdk 18

 Experimental spring api project.

    It includes;
    
        - exception handling
        - example restcontroller (create, paginated list apis)
        - spring security based authentication / authorization
        - API http curl requests
        - Example data scripts
        - Docker file & shell scripts
        - Code & Image versioning (semantic method)

# build & push to image local or remote docker registry 

use the release.sh script as below. we use semantic versioning.

    PATCH version when you make backwards compatible bug fixes,
    MINOR version when you add functionality in a backwards compatible manner, and
    MAJOR version when you make incompatible API changes.
    
ref : https://semver.org

    ./release.sh patch #version 1.0.0 -> to be -> 1.0.1
    ./release.sh minor #version 1.0.0 -> to be -> 1.1.0
    ./release.sh major #version 1.0.0 -> to be -> 2.0.0

# The application tech stack

    Java18
    Spring Boot 2.6.7 (Spring 5.3.19)
    Undertow 2.2.17.Final
    Gradle 7.4.1
    Log4j2
    Lombok
    H2Db (in memory)
    Liquibase
    Intellij IDEA (recommended IDE)
    
    Docker 
      - build.sh
      - release.sh

# curl i/o examples

### create employee

request

```json
 curl --request POST
--url 'http://localhost:8080/api/rest/v10/employees'
--header 'Authorization: Basic dHVyZ2F5OnRjMTIzNA=='
--header 'Content-Type: application/json'
--data '{
"email":"turgay.can2@inomera.com",
"fullname": "Turgay Can",
"title": "Software Engineer"
}'
```

response

```json lines
{
  "status": {
    "code": "0",
    "description": "No Error"
  },
  "txKey": "d76985aa66fb47398084d2d40d2955ba",
  "data": {
    "id": 11,
    "fullname": "Turgay Can",
    "title": "Software Engineer",
    "createDate": "2022-05-12T16:08:00.523+00:00"
  }
}
```

### list with search filters

request

```json
curl --request GET
--url 'http://localhost:8080/api/rest/v10/employees?page=0&max=20'
--header 'Authorization: Basic dHVyZ2F5OnRjMTIzNA=='
--header 'Content-Type: application/json'
--data '{
"fullname":"turgay"
}'
```

response

```json lines
{
  "content": [
    {
      "id": 11,
      "email": "turgay.can3@inomera.com",
      "fullname": "Turgay Can",
      "title": "Software Engineer",
      "createDate": "2022-05-11T21:00:00.000+00:00"
    },
    {
      "id": 1,
      "email": "turgay.can@inomera.com",
      "fullname": "Turgay Can",
      "title": "Software Engineer",
      "createDate": "2022-05-11T21:00:00.000+00:00"
    }
  ],
  "totalElements": 2,
  "pageSize": 20,
  "pageNumber": 0,
  "status": {
    "code": "0",
    "description": "No Error"
  },
  "txKey": "d76985aa66fb47398084d2d40d2955ba"
}
```

### list without search filters

request

```json
 curl --request GET
--url 'http://localhost:8080/api/rest/v10/employees?page=0&max=20'
--header 'Authorization: Basic dHVyZ2F5OnRjMTIzNA=='
--header 'Content-Type: application/json'
--data '{
}'
```

response

```json lines
{
  "content": [
    {
      "id": 7,
      "email": "burak.kandil@inomera.com",
      "fullname": "Burak Kandil",
      "title": "Senior Software Engineer",
      "createDate": "2022-05-11T21:00:00.000+00:00"
    },
    {
      "id": 6,
      "email": "melek.uzun@inomera.com",
      "fullname": "Melek Uzun",
      "title": "Senior Software Engineer",
      "createDate": "2022-05-11T21:00:00.000+00:00"
    },
    {
      "id": 8,
      "email": "kenan.kartal@inomera.com",
      "fullname": "Kenan Kartal",
      "title": "Ambitious Software Engineer",
      "createDate": "2022-05-11T21:00:00.000+00:00"
    },
    {
      "id": 9,
      "email": "merve.ecevit@inomera.com",
      "fullname": "Merve Ecevit",
      "title": "Ambitious Software Engineer",
      "createDate": "2022-05-11T21:00:00.000+00:00"
    },
    {
      "id": 10,
      "email": "birgul.ayaz@inomera.com",
      "fullname": "Birgül Ayaz",
      "title": "Principal Software Engineer",
      "createDate": "2022-05-11T21:00:00.000+00:00"
    },
    {
      "id": 1,
      "email": "turgay.can@inomera.com",
      "fullname": "Turgay Can",
      "title": "Software Engineer",
      "createDate": "2022-05-11T21:00:00.000+00:00"
    },
    {
      "id": 2,
      "email": "serdar.kuzucu@inomera.com",
      "fullname": "Serdar Kuzucu",
      "title": "Software Architect",
      "createDate": "2022-05-11T21:00:00.000+00:00"
    },
    {
      "id": 5,
      "email": "atakan.ulker@inomera.com",
      "fullname": "Atakan Ülker",
      "title": "Senior Software Engineer",
      "createDate": "2022-05-11T21:00:00.000+00:00"
    },
    {
      "id": 4,
      "email": "ayberk.unsalan@inomera.com",
      "fullname": "Ali Ayberk Ünsalan",
      "title": "Senior Software Engineer",
      "createDate": "2022-05-11T21:00:00.000+00:00"
    },
    {
      "id": 3,
      "email": "fatih.bozik@inomera.com",
      "fullname": "Fatih Bozik",
      "title": "Senior Software Engineer",
      "createDate": "2022-05-11T21:00:00.000+00:00"
    }
  ],
  "totalElements": 10,
  "pageSize": 20,
  "pageNumber": 0,
  "status": {
    "code": "0",
    "description": "No Error"
  },
  "txKey": "d76985aa66fb47398084d2d40d2955ba"
}
```
