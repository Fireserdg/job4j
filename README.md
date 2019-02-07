[![Build Status](https://travis-ci.org/Fireserdg/job4j.svg?branch=master)](https://travis-ci.org/Fireserdg/job4j)
[![codecov](https://codecov.io/gh/Fireserdg/job4j/branch/master/graph/badge.svg)](https://codecov.io/gh/Fireserdg/job4j)
# Репозиторий Филиппов Сергей
Всем привет, я прохожу стажировку в качестве Java Developer на job4j. 
Ссылка: http://job4j.ru/index.html

####Участие в разработке Spring/JPA Enterprise приложения c авторизацией и правами доступа на основе ролей на стеке Maven/ Spring MVC/ Security/ REST(Jackson)/ OpenJDK 11 Stream and Time API:

+ реализация сохранения в базы Postgres и HSQLDB на основе Spring JBDC, 
  JPA(Hibernate) и Spring-Data-JPA;
+ реализация и тестирование REST и AJAX контроллеров;

+ реализация клиента на Bootstrap (css/js), datatables, jQuery + plugins;

+ собственная доработка проекта.

####Ниже представленны наиболее интересные реализованные проекты с указанием применямых технологий:

1. ***Tracker*** для работы с системой заявок (Java Stream API, JDBC, SQL, PostgreSQL, Slf4J, liquibase).
Приложение собирается при помощи Maven. Запуск через консоль: `java -jar tracker-final.jar`).
[Перейти по ссылке к проекту](https://github.com/Fireserdg/job4j/tree/master/chapter_002/tracker).
2. ***Парсер вакансий Java разработчиков с форума <https://www.sql.ru/forum>***. (Java LocalDateTime, PostgreSQL, Quartz, Jsoup, Slf4J).
Сборка при помощи Maven. Cron expression (обновление списка вакансий) - обновление 1 раз в день. 
Запуск исполняемого .jar файла после сборки проекта через консоль: `java -jar sqlRuParser.jar app.properties`. 
Запись вакансий происходит в дефолтную базу данных *postgres*, таблица *vacancy*. 
[Перейти по ссылке проекту](https://github.com/Fireserdg/job4j/tree/master/chapter_103/parser_vacancy).



