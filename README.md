<h1 align="center">Swag Labs QA Automation </h1>

<p align="center">
  Комплексный дипломный проект QA.GURU (Java · Selenide · Rest Assured · Appium · Allure)
</p>

<p align="center">
  <code>UI Testing</code> &nbsp;•&nbsp; <code>API Testing</code> &nbsp;•&nbsp; <code>Mobile Testing</code> &nbsp;•&nbsp; <code>Manual QA</code>
</p>

---

<p align="center">
  <img src="./docs/images/saucedemo_main.png" alt="Swag Labs Interface" width="800px"/>
</p>


<h2 align="center">📌 О проекте</h2>

<p align="center">
  Дипломный проект представляет собой единый автоматизированный фреймворк, охватывающий несколько доменов тестирования:<br>
  автоматизацию веб-приложения и мобильного Android-клиента интернет-магазина <b>Swag Labs</b>,<br>
  тестирование REST API сервиса <b>Клубов</b> (<code style="color: #c7254e;">/api/v1/clubs/</code>), а также блок ручных проверок.
</p>

---

<h2 align="center">🎓 Направления дипломного проекта</h2>

---

### 🌐 UI Automation &nbsp;&nbsp;&nbsp;&nbsp; `Selenide · Java`
Автоматизация веб-магазина Swag Labs: авторизация, каталог, корзина и оформление заказа.  
👉 [Перейти к документации UI](./docs/ui/README.md)

---

### 🔌 API Automation &nbsp;&nbsp;&nbsp;&nbsp; `Rest Assured · Java`
Тестирование REST API сервиса клубов: CRUD-операции, авторизация, валидация и обработка ошибок.  
👉 [Перейти к документации API](./docs/api/README.md)

---

### 📱 Mobile Automation &nbsp;&nbsp;&nbsp;&nbsp; `Appium · BrowserStack`
Автоматизация Android-приложения Swag Labs на локальном эмуляторе и в облаке.  
👉 [Перейти к документации Mobile](./docs/mobile/README.md)

---

### 📝 Manual Testing &nbsp;&nbsp;&nbsp;&nbsp; `Allure TestOps`
Ручное тестирование, тест-кейсы, Test Plan и фиксация результатов выполнения.  
👉 [Перейти к документации Manual](./docs/manual/README.md)

---
<a id="structure"></a>
<h2 align="center">📂 Структура проекта</h2>

```text
swag-labs-qa-automation/
├── .gradle/
├── .idea/
├── build/
├── docs/
│   ├── api/
│   │   ├── images/
│   │   └── README.md
│   ├── images/
│   ├── manual/
│   │   ├── images/
│   │   └── README.md
│   ├── mobile/
│   │   ├── images/
│   │   └── README.md
│   └── ui/
│       └── README.md
├── src/
│   └── test/
│       ├── java/
│       │   └── guru/
│       │       └── qa/
│       │           ├── allure/
│       │           │   └── CustomAllureListener.java
│       │           ├── api/
│       │           │   ├── api/
│       │           │   │   ├── ApiClient.java
│       │           │   │   ├── AuthApiClient.java
│       │           │   │   └── ClubsApiClient.java
│       │           │   ├── config/
│       │           │   │   └── ApiConfig.java
│       │           │   ├── models.clubs/
│       │           │   │   ├── ClubBodyModel.java
│       │           │   │   ├── ClubModel.java
│       │           │   │   ├── ClubPatchBodyModel.java
│       │           │   │   ├── ClubReviewModel.java
│       │           │   │   ├── ClubReviewUserModel.java
│       │           │   │   └── ClubsListResponseModel.java
│       │           │   ├── specs/
│       │           │   │   └── clubs/
│       │           │   │       └── ClubsSpec.java
│       │           │   ├── BaseSpec.java
│       │           │   ├── TestBase.java
│       │           │   └── TestData.java
│       │           ├── manual/
│       │           │   └── ManualTests.java
│       │           ├── mobile/
│       │           │   ├── config/
│       │           │   │   ├── BrowserstackConfig.java
│       │           │   │   ├── LocalConfig.java
│       │           │   │   └── MobileConfig.java
│       │           │   ├── drivers/
│       │           │   │   ├── BrowserstackMobileDriver.java
│       │           │   │   └── LocalMobileDriver.java
│       │           │   ├── helpers/
│       │           │   │   ├── AndroidHelpers.java
│       │           │   │   └── Attach.java
│       │           │   ├── pages/
│       │           │   │   ├── LoginPage.java
│       │           │   │   └── ProductsPage.java
│       │           │   ├── tests/
│       │           │   │   ├── CartTests.java
│       │           │   │   └── LoginTests.java
│       │           │   └── BaseTest.java
│       │           └── ui/
│       │               ├── config/
│       │               │   └── WebConfig.java
│       │               ├── helpers/
│       │               │   └── Attach.java
│       │               ├── pages/
│       │               │   ├── CartPage.java
│       │               │   ├── CheckoutPage.java
│       │               │   ├── LoginPage.java
│       │               │   └── ProductsPage.java
│       │               ├── tests/
│       │               │   ├── CartTests.java
│       │               │   ├── LoginTests.java
│       │               │   └── ProductsTests.java
│       │               ├── BaseTest.java
│       │               └── TestData.java
│       └── resources/
│           ├── allure/
│           │   └── allure.properties
│           ├── apps/
│           │   └── Android.SauceLabs.Mobile.Sample.app.2.7.1.apk
│           ├── config/
│           │   ├── mobile/
│           │   │   ├── browserstack.properties
│           │   │   └── local.properties
│           │   └── web/
│           │       └── web.properties
│           ├── schemas.clubs/
│           │   ├── club_response_schema.json
│           │   └── clubs_list_response_schema.json
│           └── tpl/
│               ├── request.ftl
│               └── response.ftl
├── .gitignore
├── build.gradle
├── gradlew
├── gradlew.bat
├── README.md
└── settings.gradle
```
---

<a id="jenkins"></a>
<h2 align="center">⚙️ Запуск тестов через Jenkins</h2>

Для комплексного запуска всех видов тестов в CI/CD используется проект в Jenkins: [ainur_diplom](https://jenkins.qa.guru/job/ainur_diplom/)

### Параметры сборки:
Для запуска полного прогона всех автотестов выбирается параметры:
- `TEST_TYPE = ALL`
- `REMOTE_URL = https://user1:1234@selenoid.qa.guru/wd/hub`
- `DEVICE_HOST = browserstack`

<p align="center">
  <img src="./docs/images/jenkins-all-build-parameters.png" alt="Jenkins All Build Parameters" width="850px"/>
</p>

### Результаты прогона в Jenkins по всем тестам
После завершения общей сборки все тест-сюиты (UI, API, Mobile) успешно отработали:

<p align="center">
  <img src="./docs/images/jenkins-all-build-success.png" alt="Jenkins All Build Success" width="850px"/>
</p>

<p align="right"><a href="#readme-top">⬆️ наверх</a></p>

---
<h2 align="center">📊 Общие результаты прогона</h2>

### 📈 Allure-отчет по всем тестам
Посмотреть детальный сводный отчет можно по [ссылке на Allure Report в Jenkins](https://jenkins.qa.guru/job/ainur_diplom/74/allure/).

#### Основная страница отчета
<p align="center">
  <img src="./docs/images/allure-all-overview.png" alt="Allure Overview All Tests" width="850px"/>
</p>

#### Тест-кейсы
<p align="center">
  <img src="./docs/images/allure-all-test-cases.png" alt="Allure Test Cases All" width="850px"/>
</p>


<p align="right"><a href="#readme-top">⬆️ наверх</a></p>

---


<a id="testops"></a>
## 📊 Просмотр результатов в Allure TestOps

Все результаты комплексного тестирования автоматически синхронизируются с Allure TestOps. Детальный лаунч со всеми тест-кейсами доступен по [ссылке в Allure TestOps](https://allure.qa.guru/).

<p align="center">
  <img src="./docs/images/allure-testops-all-launch.png" alt="Allure TestOps Launch All" width="850px"/>
</p>

<p align="right"><a href="#readme-top">⬆️ наверх</a></p>

---

<a id="telegram"></a>
## 📱 Telegram-уведомления о прогоне

В процессе выполнения комплексной сборки в Jenkins настроена автоматическая отправка сводных нотификаций в Telegram-чат с общей статистикой по всем направлениям тестирования:

<p align="center">
  <img src="./docs/images/telegram-all-notification.png" alt="Telegram Notification All" width="450px"/>
</p>

<p align="right"><a href="#readme-top">⬆️ наверх</a></p>

---


<h2 align="center">🛠 Технологический стек</h2>

<p align="center">
  <code>Java 17</code> &nbsp;•&nbsp; <code>Gradle</code> &nbsp;•&nbsp; <code>JUnit 5</code> &nbsp;•&nbsp; <code>Selenide</code> &nbsp;•&nbsp; <code>Rest Assured</code> &nbsp;•&nbsp; <code>Appium</code> &nbsp;•&nbsp; <code>Allure</code> &nbsp;•&nbsp; <code>Jenkins</code>
</p>

<br>

<p align="center">Разработано в рамках дипломного проекта QA.GURU | Автор: <b>Ainur Kaliakbarova</b> (@AKaliakbarova)</p>

<p align="right"><a href="#readme-top">⬆️ наверх</a></p>

---

