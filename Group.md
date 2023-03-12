## Api specification for groups:

1. Get all groups by teacher:

GET /api/group?teacher=1

---
```
[
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
```

2. Delete group by id:

DELETE /api/group/1

3. Register student to subject:

PUT /api/group/register/1?student=1

4. Unregister student from subject:

PUT /api/group/unregister/1?student=1