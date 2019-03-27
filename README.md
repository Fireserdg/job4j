[![Build Status](https://travis-ci.org/Fireserdg/job4j.svg?branch=master)](https://travis-ci.org/Fireserdg/job4j)
[![codecov](https://codecov.io/gh/Fireserdg/job4j/branch/master/graph/badge.svg)](https://codecov.io/gh/Fireserdg/job4j)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/69d149a67b474d2aacc8ec11f3783198)](https://www.codacy.com/app/Fireserdg/job4j?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Fireserdg/job4j&amp;utm_campaign=Badge_Grade)
# Репозиторий Филиппов Сергей
Всем привет, я прохожу стажировку в качестве Java Developer на job4j. 
Ссылка: http://job4j.ru/index.html

#### Участие в разработке Spring/JPA Enterprise приложения c авторизацией и правами доступа на основе ролей на стеке Maven/ Spring MVC/ Security/ REST(Jackson)/ JDK 11:

+ реализация сохранения в базы Postgres и HSQLDB на основе Spring JBDC, 
  JPA(Hibernate) и Spring-Data-JPA;
+ реализация и тестирование REST и AJAX контроллеров;

+ реализация клиента на Bootstrap (css/js), datatables, jQuery + plugins;

+ собственная доработка проекта.

#### Ниже представленны наиболее интересные реализованные проекты с указанием применямых технологий:

- **Tracker** для работы с системой заявок.
    
     > - Java 8 Stream API, JDBC, SQL, PostgreSQL, Slf4J, liquibase.

    Приложение собирается при помощи Maven. Запуск через консоль: `java -jar tracker-final.jar`.
    
    [Перейти по ссылке к проекту](https://github.com/Fireserdg/job4j/tree/master/chapter_002/tracker).

- **Парсер вакансий Java разработчиков с форума <https://www.sql.ru/forum>**. 
    > - Java 8 LocalDateTime, PostgreSQL, Quartz, Jsoup, Slf4J.

    Сборка при помощи Maven. Cron expression (обновление списка вакансий) - обновление 1 раз в день. 
    
    Запуск исполняемого .jar файла после сборки проекта через консоль: `java -jar sqlRuParser.jar app.properties`. 
    Запись вакансий происходит в дефолтную базу данных *postgres*, таблица *vacancy*. 
    
    [Перейти по ссылке проекту](https://github.com/Fireserdg/job4j/tree/master/chapter_103/parser_vacancy).

- **CRUD-MVC web-app**. Веб-приложение для работы с пользователем на основе ролей с авторизацией и аутентификацией на фильтрах.
    > - Java 8 stream API/Servlet API and JSP/JSTL/Jackson;
    > - Liquibase/common-dbcp2/PostgreSQL;
    > - JavaScript/JQuery/AJAX/Json.
    > - JUnit/Powermock/Mockito

    `Веб-приложение собирается при помощи сборщика Maven в war - архив
     и деплоится в Tomcat 9.0.14 и выше.`
    
    [Перейти по ссылке к проекту](https://github.com/Fireserdg/job4j/tree/master/chapter_104/servlets_crud).
    
- **Сервис-кинотеатр web-app**. Приложение для бронирования билетов в кинотеатр. 
    > - Java 8 stream API/Servlet API and JSP/JSTL/Jackson;
    > - Liquibase/common-dbcp2/PostgreSQL;
    > - JavaScript/JQuery/AJAX/Json.
    > - JUnit/Powermock/Mockito
    
    `Веб-приложение собирается при помощи сборщика Maven в war - архив
     и деплоится в Tomcat 9.0.14 и выше.`
    
    [Перейти по ссылке к проекту](https://github.com/Fireserdg/job4j/tree/master/chapter_104/control_task).

    "Создания новой ветки через IDEA"

