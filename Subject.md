## Api specification for subjects:

1. Get all subjects:

GET /api/subject/all

---
```
[
    {
        "id": 1,
        "name": "Philosophy",
        "authorId": 1,
        "groups": [
            {
                "id": 1,
                "number": 1,
                "teacherId": 1
            },
            {
                "id": 2,
                "number": 2,
                "teacherId": 1
            }
        ]
    },
    {
        "id": 2,
        "name": "English",
        "authorId": 1,
        "groups": []
    }
]
```
2. Get all subjects by author:

GET /api/subject/all?author=1

---
```
[
    {
        "id": 1,
        "name": "Philosophy",
        "authorId": 1,
        "groups": [
            {
                "id": 1,
                "number": 1,
                "teacherId": 1
            },
            {
                "id": 2,
                "number": 2,
                "teacherId": 1
            }
        ]
    },
    {
        "id": 2,
        "name": "English",
        "authorId": 1,
        "groups": []
    }
]
```
3. Get subject by id:
GET /api/subject/2

---
```
{
    "id": 2,
    "name": "English",
    "authorId": 1,
    "groups": []
}
```

4. Get subject by name (for now fully matched):
GET /api/subject?name=English

---
```
{
    "id": 2,
    "name": "English",
    "authorId": 1,
    "groups": []
}
```

6. Add subject:

POST /api/subject

Request Body:

Without groups:

```
{
    "name": "English",
    "authorId": 1
}
```

With groups:

```
{
    "name": "Philosophy",
    "authorId": 1,
    "groups": [
        {
            "number": 1,
            "teacherId": 1
        },
        {
            "number": 2,
            "teacherId": 1
        }
    ]
}
```

7. Update subject:

PUT /api/subject

Request Body (same as for adding just some differences)
 + id of subject in body
 + groups can be without id for now

8. Delete subject:

DELETE /api/subject/1

9. Register student to subject:

PUT /api/subject/register/1?student=1

10. Unregister student from subject:

PUT /api/subject/unregister/1?student=1
