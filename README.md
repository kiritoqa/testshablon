# testshablon
1 Установить докер 
2 Установить гит
3 Клонировать с гита проект 
4 Окрыть фаил docker-compose.yaml
5 Нужно заменить в файле docker-compose.yaml путь C:/Users/Serghei/IdeaProjects/ на актуальный твой
"C:/Users/Serghei/IdeaProjects/testshablon/init/selenoid:/etc/selenoid"
6 В терминале открыть папку testshablon
7 Ввести docker-compose up
8 Ввести docker pull selenoid/vnc:chrome_87.0
9 Ввести mvn clean test -DsuiteXmlFile=pageTests.xml
10 Ввести allure serve
