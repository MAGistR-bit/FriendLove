<h1 style="display: block; font-size: 2.5em; font-weight: bold; margin-block-start: 1em; margin-block-end: 1em;">
<a><img src="images/logo_social_network.png" alt="FriendLove" style="width:65%;
height:65%"/></a>
</h1>

![Ubuntu](https://camo.githubusercontent.com/1814dfdb62c9a3366a9946083ac0f3ed32aad98e665b287769332252d945f2f1/68747470733a2f2f696d672e736869656c64732e696f2f7374617469632f76313f7374796c653d666f722d7468652d6261646765266d6573736167653d5562756e747526636f6c6f723d453935343230266c6f676f3d5562756e7475266c6f676f436f6c6f723d464646464646266c6162656c3d)

![GitHub repo size](https://img.shields.io/github/repo-size/MAGistR-bit/FriendLove)

---

# 📋 Введение (Introduction)

_**Friend Love**_ - это социальная сеть, имеющая адаптивный веб-дизайн.

Бизнес-логика проекта написана при помощи Spring Framework.
Что касается визуальной части приложения, то она была разработана при
помощи фреймворка Bootstrap (версия 5).

Подробная информация о технологиях, которые были использованы при создании
продукта, представлена в соответствующем разделе.

**Особенности социальной сети**:

1. Возможность добавлять в друзья различных пользователей.
2. Возможность отправлять личные сообщения другим пользователям.
3. Возможность публиковать посты, добавлять комментарии,
   оценивать понравившиеся публикации.
4. Возможность создать группу, в которой Вы будете обладать
   правами администратора.
5. Возможность вести личный блог (страницу), просматривать ленту новостей.
6. Возможность удалять/редактировать записи.

Этот проект носит образовательный характер, поэтому я прошу Вас не судить строго.

<details>
<summary> English version </summary>

_**Friend Love**_ is a social network with an adaptive web design.

The business logic of the project is written using the Spring Framework.
As for the visual part of the application, it was developed
using the Bootstrap framework (version 5).

Detailed information about the technologies that were used to create
the product is provided in the corresponding section.

**Features of the social network**:

1. The ability to add different users as friends.
2. The ability to send private messages to other users.
3. The ability to publish posts, add comments,
   rate your favorite publications.
4. The ability to create a group (community) in which you will be an administrator.
5. The ability to maintain a personal blog (page), view the news feed.
6. The ability to delete/edit entries.

This project is educational in nature, so I ask you not to judge strictly.
</details>

# ⚙️ Технологический стек (Technology stack)

Ниже представлены технологии, используемые при
разработке социальной сети:

* Java
* PostgreSQL (система управления базами данных)
* Spring: Boot, Security, Data
* Apache Maven (автоматическая сборка проекта)
* Thymeleaf (шаблонизатор)
* Bootstrap 5

<details>
<summary> English version </summary>

Below are the technologies used in
the development of a social network:

* Java
* PostgreSQL (Database management system)
* Spring: Boot, Security, Data
* Apache Maven (automatic build tool)
* Thymeleaf (template engine)
* Bootstrap 5

</details>

# 🔧 Настройка базы данных (Database settings)

Веб-приложение использует СУБД PostgreSQL.
Как же подключить базу данных к проекту?
Подробная информация представлена в файле [DB_Settings](DB_Settings.md).

<details>
<summary> English version</summary>

The web application uses the PostgreSQL.
How do I connect the database to the project?
Detailed information is provided in the file DB_Settings.md.
</details>

# 🚀 Запуск приложения (Application launch)

Для запуска социальной сети используйте IDE (IntelliJ IDEA).
Приложение Spring Boot работает на порту **_8080_**.

Указав в адресной строке **_localhost:8080/_**, Вы должны увидеть главную страницу
приложения. Стартовая страница FriendLove продемонстрирована на скриншоте.

![Main page](images/demonstration/main_page.png)

Чтобы ознакомиться с работой социальной сети (детально), откройте [Demonstration_App.md](Demonstration_App.md)
<details>
<summary>English version</summary>

To launch a social network, use the IDE (IntelliJ IDEA).
The Spring Boot application runs on the port 8080.

By specifying **_localhost:8080/_** in the address bar, you should see the main page
of the application. The FriendLove homepage is shown in the screenshot.

To get acquainted with the operation of the application (in detail), open [Demonstration_App.md](Demonstration_App.md).
</details>

# 👨‍💻 Тестирование

Социальная сеть **FriendLove** была успешно протестирована.
Чтобы ознакомиться с JUnit-тестами, откройте соответствующую директорию
(test).

![Result](images/junit/successful_tests.png)

**Протестирована реализация следующих функций:**
+ Регистрация / авторизация пользователя.
+ Отправка личных сообщений.
+ Создание сообществ.
+ Добавление / редактирование публикаций (постов).
+ Ведение личного блога (страницы).
+ Добавление модератора в сообщество.
+ Добавление друзей (+ заявки в друзья).

**Требования к программной части:**
1. Язык программирования **Java версии 17**.
2. База данных **PostgreSQL**.
3. Фреймворк для автоматизации сборки проектов – **Apache Maven**.
4. Фреймворк для создания веб-приложений - **Spring Boot**.
5. Современный шаблонизатор для Java - **Thymeleaf**.

<details>
<summary>English version</summary>

The social network FriendLove has been successfully tested.
To get acquainted with the JUnit tests, open the corresponding directory
(test).
</details>

# ✅ Внесение собственного вклада в проект (Contributing)

Приветствуются запросы на принятие изменений (_**Pull Request**_).
Что касается серьезных изменений, пожалуйста, сначала откройте проблему (**_Issue_**), чтобы обсудить,
что Вы хотели бы изменить.

<details>
<summary> English version </summary>

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

</details>

# 📜 Лицензия (License)

[MIT](LICENSE)

# 🤝 Благодарность (Gratitude)
Спасибо, что ознакомились с моим проектом!
Желаю Вам здоровья и всего самого наилучшего! 

<details>
<summary>English version</summary>

Thank you for reading my project!
I wish you good health and all the best!
</details>
