<div align="center">

📱 Mobile Automated Tests
SWAG LABS · ANDROID AUTOMATION

⬅️ Вернуться к главному README проекта

!Java !Gradle !Appium !Selenide !JUnit !Allure !BrowserStack

Автоматизированное тестирование Android-приложения Swag Labs

Локальный запуск на Android Emulator и удалённое выполнение на реальных устройствах BrowserStack.

</div>

📑 Содержание
🎯 О проекте
📱 Демонстрация приложения
🛠 Технологический стек
📂 Структура проекта
🧪 Покрытие тестами
🏗 Архитектура проекта
⚙️ Конфигурация окружений
🚀 Подготовка к запуску
▶️ Запуск тестов
📊 Allure Report
📸 Результаты тестирования
🔧 Troubleshooting
🚀 Возможности развития
🎯 О проекте

Проект представляет собой отдельный модуль мобильной автоматизации Android-приложения Swag Labs.

Основная задача — проверка ключевых пользовательских сценариев мобильного приложения: авторизации пользователя и взаимодействия с корзиной товаров.

Автоматизация реализована на Java с использованием Appium и Selenide.

✨ Основные возможности
Автоматизация пользовательских сценариев Android-приложения.
Запуск тестов на локальном Android-эмуляторе.
Удалённое выполнение на реальных устройствах BrowserStack.
Разделение конфигурации для разных окружений.
Использование паттерна Page Object Model.
Автоматический сбор артефактов в Allure Report.
Получение видеозаписей удалённых тестовых сессий.
Возможность запуска отдельных тестовых классов.
🌐 Поддерживаемые окружения

<table>
<tr>
<th align="center">🖥 LOCAL</th>
<th align="center">☁️ BROWSERSTACK</th>
</tr>
<tr>
<td align="center">

Android Emulator

Appium Server

Локальный APK

3/3 PASSED

</td>
<td align="center">

Google Pixel 7

BrowserStack App Automate

Облачное приложение

3/3 PASSED

</td>
</tr>
</table>

📱 Демонстрация приложения

В рамках мобильной автоматизации проверяются три основных пользовательских сценария.

🔐 Экран авторизации

Ввод логина и пароля, успешная авторизация и проверка сообщения об ошибке при использовании неверных учётных данных.

!Swag Labs Login

🛍 Каталог товаров

Отображение списка товаров и взаимодействие с каталогом.

!Swag Labs Products

🛒 Корзина товаров

Добавление товара Sauce Labs Backpack и проверка счётчика корзины.

!Swag Labs Cart

Скриншоты мобильного приложения располагаются в docs/mobile/images/.

🛠 Технологический стек
Компонент	Технология	Версия
Язык программирования	Java	17
Сборщик проекта	Gradle	9.3.0
UI-автоматизация	Selenide	7.13.0
Мобильная автоматизация	Appium Java Client	10.0.0
Android Automation	UiAutomator2	—
Тестовый фреймворк	JUnit 5	5.11.0
Отчётность	Allure Framework	2.29.0
Allure Gradle Plugin	Qameta Allure	3.0.0
Управление конфигурацией	Aeonbits Owner	1.0.12
Утверждения	AssertJ	3.27.7
API-клиент	RestAssured	5.5.0
Облачная платформа	BrowserStack App Automate	—
📂 Структура проекта
text
swag-labs-qa-automation/
│
├── src/
│   └── test/
│       ├── java/
│       │   └── guru/
│       │       └── qa/
│       │           └── mobile/
│       │               │
│       │               ├── config/
│       │               │   ├── MobileConfig.java
│       │               │   ├── LocalConfig.java
│       │               │   └── BrowserstackConfig.java
│       │               │
│       │               ├── drivers/
│       │               │   ├── LocalMobileDriver.java
│       │               │   └── BrowserstackMobileDriver.java
│       │               │
│       │               ├── helpers/
│       │               │   ├── AndroidHelpers.java
│       │               │   └── Attachments.java
│       │               │
│       │               ├── pages/
│       │               │   ├── LoginPage.java
│       │               │   └── ProductsPage.java
│       │               │
│       │               ├── tests/
│       │               │   ├── LoginTests.java
│       │               │   └── CartTests.java
│       │               │
│       │               └── BaseTest.java
│       │
│       └── resources/
│           ├── apps/
│           │   └── Android.SauceLabs.Mobile.Sample.app.2.7.1.apk
│           │
│           └── config/
│               ├── local.properties
│               └── browserstack.properties
│
├── docs/
│   └── mobile/
│       ├── README.md
│       └── images/
│           ├── mobile-login.png
│           ├── mobile-products.png
│           ├── mobile-cart.png
│           ├── allure-overview.png
│           └── allure-test-details.png
│
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
└── .gitignore


Назначение основных директорий:

Директория	Назначение
config	Конфигурация окружений и общие настройки
drivers	Создание и настройка AndroidDriver
helpers	Вспомогательные методы и сбор Allure-вложений
pages	Page Object классы мобильного приложения
tests	Автоматизированные тестовые сценарии
resources/apps	APK-файл тестируемого приложения
resources/config	Файлы настроек Local и BrowserStack
docs/mobile	Документация мобильного модуля
🧪 Покрытие тестами

На данный момент реализованы три автоматизированных сценария.

№	Тест	Проверяемый сценарий
1	successfulLoginTest()	Успешная авторизация с валидными учётными данными
2	loginWithInvalidCredentialsTest()	Проверка сообщения об ошибке при вводе неверного пароля
3	addProductToCartTest()	Добавление Sauce Labs Backpack в корзину и проверка счётчика товаров
🔑 Тестовые данные
Параметр	Значение
Валидный логин	standard_user
Валидный пароль	secret_sauce
Невалидный пароль	wrong_password
Добавляемый товар	Sauce Labs Backpack
📈 Результаты выполнения
Тест	Local	BrowserStack
successfulLoginTest()	✅ Passed	✅ Passed
loginWithInvalidCredentialsTest()	✅ Passed	✅ Passed
addProductToCartTest()	✅ Passed	✅ Passed
Итого	3/3	3/3
🏗 Архитектура проекта

Проект построен с использованием паттерна Page Object Model (POM) и принципа разделения ответственности.

1. Page Object Model

Для взаимодействия с элементами мобильного приложения используются отдельные классы страниц.

LoginPage — локаторы и методы взаимодействия с экраном авторизации: ввод логина, пароля и нажатие кнопки Login.
ProductsPage — взаимодействие с каталогом товаров и корзиной.

Тестовые классы используют готовые методы страниц, благодаря чему сценарии остаются компактными, читаемыми и удобными для поддержки.

2. BaseTest — базовый класс тестов

В BaseTest вынесена общая логика подготовки и завершения тестовых сценариев.

@BeforeAll

Инициализация конфигурации.
Определение режима выполнения через системный параметр deviceHost.
Выбор окружения: local или browserstack.

@BeforeEach

Инициализация соответствующего AndroidDriver.
Запуск мобильного приложения.
Обработка системного диалога совместимости Android при его появлении.

@AfterEach

Получение скриншота текущего экрана.
Сохранение Page Source в формате XML.
Добавление BrowserStack Session ID при удалённом запуске.
Закрытие WebDriver-сессии.
Добавление ссылки на видеозапись BrowserStack.
3. Управление конфигурацией через Aeonbits Owner

Для управления настройками используется библиотека Aeonbits Owner.

Конфигурация разделена на независимые профили:

MobileConfig — общие настройки мобильного окружения.
LocalConfig — параметры локального Android-эмулятора.
BrowserstackConfig — параметры удалённого устройства и подключения к BrowserStack.

Выбор окружения осуществляется через системный параметр deviceHost.

Для переключения между локальным и удалённым запуском не требуется изменять исходный код драйверов.

4. Два независимых драйвера

LocalMobileDriver

Создаёт AndroidDriver для локального запуска.
Получает параметры устройства из local.properties.
Использует локальный APK-файл приложения.
Подключается к локальному Appium Server.

BrowserstackMobileDriver

Создаёт AndroidDriver для удалённого запуска.
Получает параметры устройства и приложения из browserstack.properties.
Использует BrowserStack credentials из переменных окружения.
Передаёт параметры проекта, билда и тестовой сессии в BrowserStack.
Подключается к облачной платформе через Remote WebDriver.
5. Allure Attachments

Для сбора артефактов используется вспомогательный класс Attachments.

После завершения каждого теста в Allure автоматически добавляются:

Вложение	Назначение
Screenshot	Скриншот текущего экрана приложения
Page Source	XML-структура текущего экрана
BrowserStack Session ID	Идентификатор тестовой сессии
BrowserStack Video	Ссылка на видеозапись выполнения теста

Действия внутри тестов и Page Object логируются с использованием аннотации @Step.

Это позволяет анализировать не только итоговый статус теста, но и последовательность выполненных действий.

⚙️ Конфигурация окружений

Проект поддерживает два режима выполнения.

<details>
<summary><strong>🖥 LOCAL — локальный запуск</strong></summary>

Файл конфигурации:

src/test/resources/config/local.properties

Пример:

properties
platformName=Android
deviceName=emulator-5554
platformVersion=17.0
app=src/test/resources/apps/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk


Локальный запуск осуществляется через Appium Server и Android-эмулятор.

</details>

<details>
<summary><strong>☁️ BROWSERSTACK — удалённый запуск</strong></summary>

Файл конфигурации:

src/test/resources/config/browserstack.properties

Пример:

properties
browserstack.user=${BROWSERSTACK_USERNAME}
browserstack.key=${BROWSERSTACK_ACCESS_KEY}

platformName=Android
deviceName=Google Pixel 7
platformVersion=13.0

app=bs://YOUR_APP_ID

remoteUrl=https://hub-cloud.browserstack.com/wd/hub


Для подключения используются:

BROWSERSTACK_USERNAME — имя пользователя BrowserStack.
BROWSERSTACK_ACCESS_KEY — секретный ключ доступа.

Реальные credentials не должны храниться в GitHub.

</details>

🚀 Подготовка к запуску
1. Необходимое программное обеспечение

Перед запуском тестов необходимо установить:

JDK 17.
IntelliJ IDEA.
Android Studio и Android SDK.
Appium 2.x.
Appium UiAutomator2 Driver.
Git.

Для локального выполнения потребуется запущенный Android-эмулятор или подключённое физическое устройство.

2. Подготовка Appium

Установка UiAutomator2:

bash
appium driver install uiautomator2


Запуск Appium Server:

bash
appium


Проверка подключения Android-устройства:

bash
adb devices


Убедитесь, что эмулятор запущен и доступен для подключения.

3. Подготовка BrowserStack

Для удалённого запуска необходимо:

Создать аккаунт на BrowserStack.
Открыть App Automate.
Загрузить APK мобильного приложения.
Получить идентификатор приложения формата bs://....
Указать полученный App ID в browserstack.properties.
Настроить переменные окружения с credentials.

Пример настройки для macOS:

bash
export BROWSERSTACK_USERNAME="your_username"
export BROWSERSTACK_ACCESS_KEY="your_access_key"


Проверка наличия переменных:

bash
echo ${BROWSERSTACK_USERNAME:+USERNAME_SET}
echo ${BROWSERSTACK_ACCESS_KEY:+ACCESS_KEY_SET}


При корректной настройке терминал выведет:

text
USERNAME_SET
ACCESS_KEY_SET

▶️ Запуск тестов

Все команды выполняются из корневой директории проекта.

🖥 Local — локальный запуск
bash
./gradlew clean test -DdeviceHost=local


Тесты выполняются на локальном Android-эмуляторе через Appium Server.

☁️ BrowserStack — удалённый запуск
bash
./gradlew clean test -DdeviceHost=browserstack


Тесты выполняются на облачном Android-устройстве BrowserStack.

Перед запуском необходимо убедиться, что настроены BrowserStack credentials и указан действующий App ID.

Запуск отдельного тестового класса

Тесты авторизации:

bash
./gradlew test -DdeviceHost=local --tests "guru.qa.mobile.tests.LoginTests"


Тесты корзины:

bash
./gradlew test -DdeviceHost=local --tests "guru.qa.mobile.tests.CartTests"


Для BrowserStack достаточно заменить local на browserstack.

📊 Allure Report

Для анализа результатов автоматизированного тестирования используется Allure Report.

После выполнения тестов результаты сохраняются в директории:

text
build/allure-results/


Генерация и открытие отчёта:

bash
./gradlew allureServe


В отчёте доступны:

Статус каждого тестового сценария.
Продолжительность выполнения.
Иерархия шагов и вложенных действий.
Скриншоты приложения.
XML Page Source.
BrowserStack Session ID.
Ссылки на видеозаписи удалённых тестовых сессий.
📸 Результаты тестирования

Ниже представлены результаты выполнения мобильных автотестов в Allure Report.

Общая статистика

Все три тестовых сценария успешно завершились на локальном эмуляторе и в BrowserStack.

!Allure Overview

Детализация выполнения теста

Allure Report отображает последовательность действий, продолжительность шагов, результаты проверок и собранные вложения.

!Allure Test Details

📎 Артефакты BrowserStack

При удалённом запуске BrowserStack предоставляет видеозаписи тестовых сессий.

Для каждого теста в Allure доступны:

Screenshot — скриншот приложения.
Page Source — XML-структура экрана.
BrowserStack Session ID — идентификатор сессии.
BrowserStack Video — ссылка Open BrowserStack video.

Видеозаписи позволяют визуально проследить выполнение сценария и проанализировать поведение приложения.

🔧 Troubleshooting

<details>
<summary><strong>BrowserStack credentials не настроены</strong></summary>

Ошибка:

text
BrowserStack credentials are not configured.


Проверьте наличие переменных окружения:

bash
echo ${BROWSERSTACK_USERNAME:+USERNAME_SET}
echo ${BROWSERSTACK_ACCESS_KEY:+ACCESS_KEY_SET}


При необходимости задайте их повторно через export.

</details>

<details>
<summary><strong>Не удаётся подключиться к локальному устройству</strong></summary>

Проверьте:

bash
adb devices


Убедитесь, что Android-эмулятор запущен, а Appium Server работает.

</details>

<details>
<summary><strong>Не удаётся запустить приложение в BrowserStack</strong></summary>

Проверьте:

Корректность remoteUrl.
Наличие действующего App ID.
Соответствие выбранного устройства и версии Android.
Правильность BrowserStack credentials.

</details>

<details>
<summary><strong>Не отображается Allure Report</strong></summary>

Проверьте наличие результатов:

bash
build/allure-results/


Затем выполните:

bash
./gradlew allureServe


</details>

🚀 Возможности развития

В дальнейшем проект может быть расширен следующими возможностями:

Увеличение покрытия пользовательских сценариев.
Проверка удаления товаров из корзины.
Добавление дополнительных негативных сценариев авторизации.
Расширение тестирования на различные Android-устройства.
Интеграция с GitHub Actions.
Автоматическое формирование Allure Report в CI/CD pipeline.
Добавление дополнительных категорий и тегов для тестовых сценариев.

<div align="center">

📱 Mobile Automation | Swag Labs

Java · Appium · Selenide · JUnit 5 · Allure · BrowserStack

Автоматизация · Надёжность · Качество

⬆️ Вернуться к началу

⬅️ Главная страница дипломного проекта

</div>