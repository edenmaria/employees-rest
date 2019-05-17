# Employees API REST

## Installation

### Requirements

- JAVA
- Maven
- MySQL

Create MySQL user with name **admin** and password **123**. Create data base with name **employees** with owner **admin**.  


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
[
    {
        "id": 1,
        "name": "Eden",
        "surname": "Arreaza",
        "id_number": "16691154",
        "workShifts": []
    },
    {
        "id": 2,
        "name": "Jose",
        "surname": "Perez",
        "id_number": "141312",
        "workShifts": []
    }
]

```

Response description

Return a array of employees with that structure

Param | Type | Description
------|------|------------
id    | Long | Id of register
name  | String | Employee name
surname | String | Employee surname
id_number | String | Employee id
workShifts | List | Work shifts associated with the employee

### Get employee

Get a employee by id

`GET /employees/:id`

Response example
```json
{
    "id": 1,
    "name": "Eden",
    "surname": "Arreaza",
    "id_number": "16691154",
    "workShifts": []
}

```

Response description

Param | Type | Description
------|------|------------
id    | Long | Id of register
name  | String | Employee name
surname | String | Employee surname
id_number | String | Employee id
workShifts | List | Work shifts associated with the employee


### Create employee

Create a employee

`POST /employees/`

Body example
```json
{ 
  "name":"Eden",
  "surname":"Arreaza",
  "id_number": "16691154"
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
  "id":"1",
  "name":"Eden",
  "surname":"Arreaza",
  "id_number": "1213"
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


### Get workshifts

Get all workshifts

`GET /workshifts/`

Response example
```json
[
    {
        "id": 1,
        "hora_inicio": "08:00:00",
        "hora_fin": "12:00:00",
        "dias": "lunes"
    },
    {
        "id": 2,
        "hora_inicio": "02:00:00",
        "hora_fin": "06:30:00",
        "dias": "martes"
    }
]

```

Response description

Return a array of employees with that structure

Param | Type | Description
------|------|------------
id    | Long | Id of register
hora_inicio | Date | Start time work shift
hora_fin | Date | End time work shift
dias  | String | Day of work shift

### Get workshift

Get a workshift by id

`GET /workshifts/:id`

Response example
```json
{
    "id": 1,
    "hora_inicio": "08:00:00",
    "hora_fin": "12:00:00",
    "dias": "lunes"
}

```

Response description

Param | Type | Description
------|------|------------
id    | Long | Id of register
hora_inicio | Date | Start time work shift
hora_fin | Date | End time work shift
dias  | String | Day of work shift


### Create workshift

Create a workshift

`POST /workshifts/`

Body example
```json
{ 
  "dias":"lunes",
  "hora_inicio":"08:00:00",
  "hora_fin":"12:00:00"
}

```

Body description

Param | Type | Description
------|------|------------
dias  | String | Day of work shift
hora_inicio | Date | Start time work shift
hora_fin | Date | End time work shift


Response http status 201 


### Update workshift

Update a workshift

`PUT /workshifts/`

Body example
```json
{
    "id": 5,
    "hora_inicio": "08:00:00",
    "hora_fin": "11:00:00",
    "dias": "lunes"
}

```

Body description

Param | Type | Description
------|------|------------
id    | Long | Id of register
hora_inicio | Date | Start time work shift
hora_fin | Date | End time work shift
dias  | String | Day of work shift


Response http status 200


### Delete workshift

Delete a workshift

`DELETE /workshifts/:id`

Response http status 204


### Create a work shift associated with the employee

Create a work shift associated with the employee

`POST /employees-workshifts/`

Body example
```json
{ 
  "idEmployee":1,
  "idWorkshift":2
	
}

```

Body description

Param | Type | Description
------|------|------------
id_employee  | long | Id of register of employee
id_workshift | long | Id of register of work shift

Response http status 201 


### Delete a work shift associated with the employee

Delete a work shift associated with the employee

`DELETE /employees-workshifts/:id_employee/:id_workshift`

Response http status 200


### Get employee data by identification number

Get employee data by identification number

`GET /employees-workshifts/:id_number`

Response example
```json
{
    "id": 1,
    "name": "Eden",
    "surname": "Arreaza",
    "id_number": "1213",
    "workShifts": [
        {
            "id": 1,
            "hora_inicio": "08:00:00",
            "hora_fin": "11:00:00",
            "dias": "lunes"
        },
        {
            "id": 3,
            "hora_inicio": "08:00:00",
            "hora_fin": "12:00:00",
            "dias": "lunes"
        }
    ]
}

```

Response description

Param | Type | Description
------|------|------------
id    | Long | Id of register
name  | String | Employee name
surname | String | Employee surname
id_number | String | Employee id
workShifts | List | Work shifts associated with the employee


### Get work shifts filtering by day, start time and end time

Get work shifts filtering by day, start time and end time

`GET /workshifts-filters/&dias=:dia&hora_inicio=:hora_inicio&hora_fin=:hora_fin

Response example
```json
[
    {
        "id": 9,
        "hora_inicio": "08:00:00",
        "hora_fin": "12:00:00",
        "dias": "jueves"
    },
    {
        "id": 10,
        "hora_inicio": "15:30:01",
        "hora_fin": "23:30:05",
        "dias": "jueves"
    }
]

```

Response description

Param | Type | Description
------|------|------------
id    | Long | Id of register
hora_inicio | Date | Start time work shift
hora_fin | Date | End time work shift
dias  | String | Day of work shift




