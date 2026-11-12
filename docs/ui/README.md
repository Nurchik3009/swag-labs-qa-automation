<a id="readme-top"></a>

# 🌐 UI Automation Tests — Swag Labs

> Автоматизированное UI-тестирование веб-приложения интернет-магазина [Swag Labs (SauceDemo)](https://www.saucedemo.com/) на базе Java, Selenide, JUnit 5 и Selenoid.

---

## 📜 Содержание:

- <a href="#tools">Технологии и инструменты</a>
- <a href="#structure">Архитектура и структура проекта</a>
- <a href="#cases">Реализованные тест-кейсы</a>
- <a href="#console">Запуск тестов из терминала</a>
- <a href="#jenkins">Запуск тестов через Jenkins</a>
- <a href="#allure">Allure-отчёт и вложения</a>
- <a href="#video">Видео прохождения тестов в Selenoid</a>
- <a href="#testops">Allure TestOps</a>
- <a href="#jira">Интеграция с Jira</a>
- <a href="#telegram">Telegram-уведомления</a>

---

<a id="tools"></a>
## 🛠 Технологии и инструменты

<p align="center">
  <code>☕ Java 17</code> &nbsp;•&nbsp;
  <code>💻 IntelliJ IDEA</code> &nbsp;•&nbsp;
  <code>🧪 JUnit 5</code> &nbsp;•&nbsp;
  <code>📦 Gradle</code> &nbsp;•&nbsp;
  <code>🌐 Selenide</code> &nbsp;•&nbsp;
  <code>🐳 Selenoid</code> &nbsp;•&nbsp;
  <code>📊 Allure</code> &nbsp;•&nbsp;
  <code>⚙️ Jenkins</code>
</p>

* **Java 17** — язык написания автотестов.
* **Selenide** — фреймворк для взаимодействия с UI элементов страницы.
* **Page Object Model (POM)** — шаблон для разделения логики страниц и тестов.
* **JUnit 5** — тестовый фреймворк (`@Tag("ui")`).
* **Selenoid** — удалённый запуск браузеров в Docker с записью видео.
* **Allure Framework** — отчёты с разбивкой по эпикам, фичам и стори.

<p align="right"><a href="#readme-top">⬆️ наверх</a></p>

---

<a id="structure"></a>
## 📂 Архитектура и структура проекта

Тестовый модуль UI организован по паттерну **Page Object Pattern**:

```text
swag-labs-qa-automation/
├── docs/
│   └── ui/
│       └── README.md        # Подробная документация по UI-тестам (тут лежат и скриншоты)
└── src/
    └── test/
        └── java/
            └── guru/
                └── qa/
                    └── ui/
                        ├── config/
                        │   └── WebConfig.java       # Конфиг запуска
                        ├── helpers/
                        │   └── Attach.java          # Вложения в Allure
                        ├── pages/
                        │   ├── CartPage.java        # Корзина
                        │   ├── CheckoutPage.java    # Оформление заказа
                        │   ├── LoginPage.java       # Страница входа
                        │   └── ProductsPage.java    # Каталог товаров
                        ├── tests/
                        │   ├── CartTests.java       # Тесты корзины
                        │   ├── LoginTests.java      # Тесты авторизации
                        │   └── ProductsTests.java   # Тесты каталога
                        ├── BaseTest.java            # Базовый класс тестов
                        └── TestData.java            # Тестовые константы
```
<p align="right"><a href="#readme-top">⬆️ наверх</a></p>

---

<a id="cases"></a>
## ✅ Реализованные тест-кейсы

### 🔐 Авторизация и доступ (`LoginTests`)
* `successfulLoginTest` — Успешная авторизация (`standard_user`).
* `lockedOutUserLoginTest` — Ошибка для заблокированного пользователя (`locked_out_user`).
* `invalidPasswordTest` — Ошибка при неверном пароле.
* `logoutTest` — Корректный выход из системы (Logout).

### 🛒 Корзина и оформление заказа (`CartTests`)
* `addProductToCartTest` — Добавление товара и проверка счётчика.
* `removeProductFromCartTest` — Удаление товара из корзины.
* `successfulCheckoutFlowTest` — **[BLOCKER]** Полный цикл покупки с проверкой сообщения `"Thank you for your order!"`.

### 📦 Каталог товаров (`ProductsTests`)
* `productsPageDisplayedTest` — Проверка заголовка и списка товаров.
* `sortProductsByPriceTest` — Сортировка по возрастанию цены.

<p align="right"><a href="#readme-top">⬆️ наверх</a></p>

---

Markdown
<a id="console"></a>
## 💻 Запуск тестов из терминала

Локальный запуск UI-тестов:
```
./gradlew clean test -DtestTags=ui
```
Запуск с параметрами браузера:
```
./gradlew clean test -DtestTags=ui -Dbrowser=chrome -DbrowserSize=1920x1080
```
Удалённый запуск в Selenoid:

```
./gradlew clean test -DtestTags=ui -DisRemote=true -DremoteUrl="https://user1:1234@selenoid.qa.guru/wd/hub"
```
Просмотр Allure-отчёта:

```
./gradlew allureServe
```
<p align="right"><a href="#readme-top">⬆️ наверх</a></p>

---
<a id="jenkins"></a>
## ⚙️ Запуск тестов через Jenkins

Для запуска UI-тестов в CI/CD используется проект в Jenkins: [ainur_diplom](https://jenkins.qa.guru/job/ainur_diplom/)

### Параметры сборки:
При запуске сборки (`Build with Parameters`) передаются следующие параметры:

<p align="center">
  <img src="./images/jenkins/jenkins-build-parameters.png" alt="Jenkins Build Parameters" width="850px"/>
</p>

### Результаты прогона в Jenkins
После завершения сборки (#69) все тесты успешно отработали

<p align="center">
  <img src="./images/jenkins/jenkins-build-success.png" alt="Jenkins Build Success" width="850px"/>
</p>

---

<p align="right"><a href="#readme-top">⬆️ наверх</a></p>

---

<a id="allure"></a>

<a id="allure"></a>
## <img alt="Allure" height="30" src="./images/logo.png" width="60"/> [Allure-отчет](https://jenkins.qa.guru/job/ainur_diplom/69/allure/)
### Основная страница отчета
<p align="center">
  <img src="./images/ui-allure-overview.png" alt="Allure Overview" width="850px"/>
</p>

### Тест-кейсы
<p align="center">
  <img src="./images/Test_cases.png" alt="Allure Test Details" width="850px"/>
</p>

### Шаги выполнения 
<p align="center">
  <img src="./images/ui-allure-test-details.png" alt="Allure Test Details" width="850px"/>
</p>

### Автоматические вложения
К каждому тесту прикрепляются: скриншот, page Source, логи консоли и видео.
<p align="center">
  <img src="./images/ui-allure-attachments.png" alt="Allure Attachments" width="850px"/>
</p>

<p align="right"><a href="#readme-top">⬆️ наверх</a></p>

---

<a id="video"></a>
## 🎥Видео прохождения тестов в Selenoid

<p align="center">
  <img src="./images/ui-selenoid-video-ezgif.com-video-to-gif-converter.gif" alt="Selenoid Video Execution" width="850px"/>
</p>

<p align="right"><a href="#readme-top">⬆️ наверх</a></p>

---


<a id="testops"></a>
## 📊 Просмотр результатов в Allure TestOps

Результаты выполнения тестов автоматически выгружаются в Allure TestOps. Посмотреть детальный отчет и шаги прогона можно по [ссылке на лаунч в Allure TestOps](https://allure.qa.guru/launch/57091/tree/1177648)

<p align="center">
  <img src="./images/allure-testops-launch.png" alt="Allure TestOps Launch Results" width="850px"/>
</p>

<p align="right"><a href="#readme-top">⬆️ наверх</a></p>

---
<a id="jira"></a>
## 🔗 Интеграция с Jira

В рамках проекта настроена интеграция с Jira. Задача на автоматизацию интерфейса **[MUL-73](https://jira.qa.guru/browse/MUL-73)**

<p align="center">
  <img src="./images/ui-jira-integration.png" alt="Jira Task MUL-73 Integration" width="850px"/>
</p>

<p align="right"><a href="#readme-top">⬆️ наверх</a></p>

---

<a id="telegram"></a>
## 📱 Telegram-уведомления о прогоне

В процессе выполнения сборки в Jenkins настроена автоматическая отправка нотификаций в Telegram-чат с общей статистикой успешных тестов (9 из 9 успешных, 100%):

<p align="center">
  <img src="./images/telegram-notification.png" alt="Telegram Notification" width="450px"/>
</p>

<p align="right"><a href="#readme-top">⬆️ наверх</a></p>

---
<p align="center">
  <a href="../../README.md"><b>👈 Вернуться на главную страницу репозитория</b></a>
</p>