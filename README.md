### java-demo

#### Инструменты

- Java 11
- Gradle 5
- PostgreSql

#### Настройка БД
```
$ psql -h localhost -U postgres
> CREATE database javademo;
> CREATE role program WITH password 'qwerty';
> GRANT ALL PRIVILEGES ON database javademo TO program;
> ALTER role program WITH login;
> /q
$ psql -h localhost -U program javademo
```

#### Сброка проекта
Сборка выполняется в jar файл с embedded tomcat-контейнером.

`gradle clean build`

#### Запуск проекта

`gradle clean bootRun`

Запуск происходит как standalone приложение.
После запуска можно зайти в браузере `http://localhost:8080/manage/health` - health-чек.  