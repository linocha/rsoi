### rsoi

#### Инструменты

- Java 11
- Gradle 5
- PostgreSql

#### Настройка БД
```
$ psql -h localhost -U postgres
> CREATE database book_database;
> CREATE role zina WITH password 'qwerty';
> GRANT ALL PRIVILEGES ON database book_database TO zina;
> ALTER role program WITH login;
> /q
$ psql -h localhost -U program javademo
```

Запуск происходит как standalone приложение.
После запуска можно зайти в браузере `http://localhost:8080/books' 

Для поиска книги  `http://localhost:8080/books/ID'