# System Details

Java 8
Spring-Boot (v2.2.1.BUILD-SNAPSHOT)
Maven 3


# Compile and execute program

for compile this project you need Java y Maven pre-install.
In to directory *nisum_test* and execute this command *maven*

```bash
mvn package
```

Next of compile the project into directory *target* execute this command *java*

```bash
java -jar .\solution-1.0.jar

```
*Note*:
You have need available port *8081* in the PC where execute this API


# View where available API

URL to request the REST operations and Methods
```bash
GET --header 'Accept: application/json' 'http://localhost:8081/persona'
    Response an List of Person Info (JSON)

GET --header 'Accept: application/json' 'http://localhost:8081/persona/{id}'
    Response a Person Info (JSON) if exists, where id is a Integer
    If id not exists send message error

POST --header 'Accept: application/json' 'http://localhost:8081/persona/filtro'
    Send in the body a Person Info (JSON), at least one field for the search
    Response an List of Person Info (JSON)

DELETE --header 'Accept: application/json' 'http://localhost:8081/persona/{id}'
    Response a message successful if exists and otherwise message error, where id is a Integer

POST --header 'Accept: application/json' 'http://localhost:8081/persona
    Send in the body a Person Info (JSON)
    For Insert id must be null otherwise
    For Update if id exists response succesful message and Person Info (JSON) otherwise message error
```

# Codigos de Respuestas

200 OK - Successful, possibilities:
    Response List of all Persons
    Response at request Person (include List empty)
    Delete Person Info
201 Created - Response Message Successful and Person Info (JSON) - Create or Update
400 Bad Request - Response Message Error when Person Exists
404 Not Data Found - Response Message Error Person Not Exists
406 Not Acceptable - Response Message Error when hair colour its not valid

# Example Person Info (JSON)
```bash
{
  "id": 1,
  "name": "Yasenia",
  "lastname": "Gonzalez Blanco",
  "address": "Calle el Molino 1845",
  "phone": 56931230321,
  "hair": "brown"
}
```

#Addition:
If you need change port go to main/resources/application.properties

# Made by:

Christian Ayo Roca
christian.ayo@gmail.com
