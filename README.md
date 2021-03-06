# TextAnalyzer
Веб-приложение для анализа текста. 
![image](https://user-images.githubusercontent.com/4527850/36965042-75e9dc1e-2069-11e8-8b81-ab5a5834fd1b.png)

### Используемые технологии:
```Java```

```Spring Boot```

```JQuery```

```Bootstrap```


#### Требования:

```csv
Поддерживается только файлы формата UTF-8
```

### На данный момент реализовано 2 функции:
+ Подсчет количества повторяющихся слов в тексте. 
  Союзы, предлоги и местоимения не учитываются.
+ Проверка правильности расстановки скобок.

#### Для первой функции:

```csv
Входные данные: txt-файл с текстом для анализа
Результат: топ-10 наиболее часто встречающихся слов
```
#### Для второй фуекции:

```java
Входные данные: txt-файл с текстом для анализа
Результат: correct/incorrect
```

#### Так же в формате csv хранятся слова, которые исключаются из поиска:

```xml
сколько,каковой,каков,зачем,когда,тот,это,то,этот,столько,такой,ту,тебе,тут,так,таков,сей,всякий,
я,ты,он,она,оно,мы,вы,они,себя,мой,мне,твой,свой,ваш,наш,его,её,их,кто,что,какой,
чей,где,который,откуда,сколько,каковой,каков,зачем,кто,что,какой,тебя,который,чей,
...
```
