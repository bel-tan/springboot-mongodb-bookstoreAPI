# springboot-mongodb-bookstoreAPI

Bookstore API application using springboot and mongodb

API Function Specification:
1. Request Mapping: /api/v1/user/authenticateUser
Permission: permission given to all
Description: Authenticate the user and generate an authentication token
e.g
{
  "username" : "gordon",
  "password" : "pwd3"
}

2. Request Mapping: /api/v1/user/addUser
Permission: Only user with ROLE_ADMIN
Description: Add a new system user
e.g
{
  "username": "gordon",
  "password": "pwd3",
  "role": "ROLE_USER"
}

3. Request Mapping: /api/v1/bookstore/addBook
Permission: Only user with ROLE_ADMIN or ROLE_USER
Description: Add a new book
e.g
{
    "isbn": "0205080070",
    "title": "sixth edition",
    "year": 2020,
    "price": 40,
    "genre": "Economics",
    "author": [
      {
        "name": "Fredrick Clark",
        "dob": "1978-06-01"
      }
    ]
  }

4. Request Mapping: /api/v1/bookstore/updateBook
Permission: Only user with ROLE_ADMIN or ROLE_USER
Description: Update an existing book details
e.g
{
    "isbn": "0205080070",
    "title": "sixth book",
    "year": 2020,
    "price": 40,
    "genre": "Economics",
    "author": [
      {
        "name": "Fredrick Clark",
        "dob": "1978-06-01"
      }
    ]
  }

5. Request Mapping: /api/v1/bookstore/deleteBookById/{isbn}
Permission: Only user with ROLE_ADMIN or ROLE_USER
Description: Delete an existing book details
e.g
/api/v1/bookstore/deleteBookById/0205080070

6. Request Mapping: /api/v1/bookstore/findByTitleAndAuthorName/{title}/{authorName}
Permission: permission given to all
Description: Find books by title and author
e.g
/api/v1/bookstore/findByTitleAndAuthorName/sixth book/Fredrick Clark

7. Request Mapping: /api/v1/bookstore/findByTitleOrAuthorName?title={title}&authorName={authorName}
Permission: permission given to all
Description: Find books either by title or author
e.g
/api/v1/bookstore/findByTitleOrAuthorName?title=sixth book&authorName=Fredrick Clark