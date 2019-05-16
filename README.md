# Employees API REST

## Installation

### Requirements

- JAVA
- Maven
- MySQL

Create MySQL user with name **admin** and password **123***. Create data base with name **employees** with owner **admin**.  


Clone the project

```sh
$ git clone https://github.com/edenmaria/employees-rest.git

```

Run the project

```sh
$ mvn spring-boot:run

```

Use Curl or Postman to browse the api at http//:localhost:8080/api

## API Documentation

### Get employees

Get all employees

`GET /employees/`

Response example
```json
{ 
    {
        id:"1",
        name:"Eden"
        surname:"Arreaza"
    }

    {
        id:"1",
        name:"Eden"
        surname:"Arreaza"
    }
}

```

Response description

Return a array of employees with that structure

Param | Type | Description
------|------|------------
id    | Long | Id of register
name  | String | Employee name
surname | String | Employee surname
id_number | String | Employee id


### Get employee

Get a employee by id

`GET /employees/:id`

Response example
```json
{ id:"1",
  name:"Eden"
  surname:"Arreaza"  
}

```

Response description

Param | Type | Description
------|------|------------
id    | Long | Id of register
name  | String | Employee name
surname | String | Employee surname
id_number | String | Employee id


### Create employee

Create a employee

`POST /employees/`


Body example
```json
{ 
  name:"Eden",
  surname:"Arreaza",
  id_number: "1213"
}

```

Body description

Param | Type | Description
------|------|------------
name  | String | Employee name
surname | String | Employee surname
id_number | String | Employee id


Response http status 201 


### Update employee

Update a employee

`PUT /employees/`


Body example
```json
{ 
  id:"1",
  name:"Eden",
  surname:"Arreaza",
  id_number: "1213"
}

```

Body description

Param | Type | Description
------|------|------------
id    | Long | Id of register
name  | String | Employee name
surname | String | Employee surname
id_number | String | Employee id


Response http status 200


### Delete employee

Delete a employee

`DELETE /employees/:id`

Response http status 204