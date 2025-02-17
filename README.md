# Задание 1. Базовая реализация REST API

Данный модуль описывает функциональные и бизнес-требования к проекту управления товарами. Проект реализует
CRUD (Create, Read, Update, Delete) функциональность для товаров, хранящихся во внутренней коллекции (без
использования базы данных). Проект использует Spring Boot и Maven для управления зависимостями.

## Бизнес-требования

### 1. Получение списка товаров (GET /products):

Возвращает список всех товаров в формате JSON. Каждый товар содержит следующие поля:
* id товара
* Название товара
* Описание товара
* Цена товара
* В наличии ли товар

### 2. Получение отдельного товара (GET /products/{id}):

* Принимает ID товара в качестве параметра пути.
* Возвращает информацию о товаре с указанным ID в формате JSON.
* Возвращает HTTP статус 404, если товар не найден.

### 3. Создание товара (POST /products):

* Принимает JSON с данными нового товара.
* Проверки:
    * `name`: Длина не более 255 символов. Ошибка, если поле пустое.
    * `description`: Длина не более 4096 символов. Значение по умолчанию пустая строка.
    * `price`: Числовое значение, не может быть меньше 0. Значение по умолчанию 0.
    * `inStock`: Булево значение. Значение по умолчанию `false`, если не указано явно.
* Возвращает созданный товар в формате JSON, включая сгенерированный ID.
* Возвращает соответствующий HTTP код статуса (201 Created при успехе, 400 Bad Request при ошибке валидации).

### 4. Изменение товара (PUT /products/{id}):

* Принимает ID товара и JSON с обновленными данными.
* Проверки аналогичны созданию товара.
* Возвращает обновленный товар в формате JSON.
* Возвращает HTTP статус 404, если товар не найден.
* Возвращает соответствующий HTTP код статуса (200 OK при успехе, 400 Bad Request при ошибке валидации).

### 5. Удаление товара (DELETE /products/{id}):

* Принимает ID товара в качестве параметра пути.
* Удаляет товар с указанным ID.
* Возвращает HTTP статус 204 No Content при успехе.
* Возвращает HTTP статус 404, если товар не найден.

# Задание 2. Подключение базы данных

Проект реализует систему управления продуктами с использованием базы данных PostgreSQL.  Он удовлетворяет требованиям
сохранения данных при перезапуске приложения и включает бонусные функции: автоматическое создание таблиц при первом
запуске и использование SQL-миграций.

## Функциональные требования

* СУБД: PostgreSQL
* Сохранение данных: Данные сохраняются между перезапусками приложения.

## Бонусные функции

* Автоматическое создание таблиц: Приложение создает необходимые таблицы в базе данных, если они отсутствуют.
* Ограничения на столбцы: На столбцы наложены ограничения, соответствующие бизнес-требованиям из пункта 1.
* SQL-миграции: Используется механизм SQL-миграций Liquibase для управления версиями схемы базы данных.

# Задание 3. Использование Docker

Проект использует Docker и docker-compose для обеспечения единообразного и предсказуемого окружения для 
развертывания приложения.