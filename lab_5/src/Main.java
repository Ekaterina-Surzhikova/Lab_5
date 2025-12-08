import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== ВЫПОЛНЕНИЕ ЗАДАЧ 5 ВАРИАНТА ===\n");

        boolean exit = false;
        while (!exit) {
            System.out.println("Выберите задачу для выполнения:");
            System.out.println("1.  Демонстрация всех задач (автоматически)");
            System.out.println("2.  Работа с дробями (1.1)");
            System.out.println("3.  Работа с котами (2.1)");
            System.out.println("4.  Симметрическая разность списков (3.9)");
            System.out.println("5.  Обработка абитуриентов (4.4)");
            System.out.println("6.  Анализ текста на звонкие согласные (5.5)");
            System.out.println("7.  Очередь в обратном порядке (6.1)");
            System.out.println("8.  Обработка точек через Stream (7.1)");
            System.out.println("9.  Обработка файла с именами (7.2)");
            System.out.println("0.  Выход");
            System.out.print("Ваш выбор: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    demonstrateAllTasks();
                    break;
                case 2:
                    interactiveFractions();
                    break;
                case 3:
                    interactiveCats();
                    break;
                case 4:
                    interactiveListDifference();
                    break;
                case 5:
                    interactiveApplicants();
                    break;
                case 6:
                    interactiveTextAnalysis();
                    break;
                case 7:
                    interactiveQueue();
                    break;
                case 8:
                    interactivePoints();
                    break;
                case 9:
                    interactiveFileProcessing();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Выход из программы...");
                    break;
                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }
        }
        scanner.close();
    }

    // ========== ОСНОВНЫЕ МЕТОДЫ (без Collectors) ==========

    private static void demonstrateAllTasks() {
        System.out.println("\n=== ДЕМОНСТРАЦИЯ ВСЕХ ЗАДАЧ ===\n");

        demonstrateFractions();
        demonstrateCats();
        demonstrateListDifference();
        processApplicants();
        analyzeText();
        demonstrateQueue();
        demonstrateStreamPoints();
        processPeopleFile();

        System.out.println("=== ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА ===\n");
        System.out.print("Нажмите Enter для продолжения...");
        scanner.nextLine();
    }

    private static void interactiveFractions() {
        System.out.println("\n=== РАБОТА С ДРОБЯМИ ===");
        System.out.println("1. Создать дробь");
        System.out.println("2. Проверить кэширование");
        System.out.println("3. Изменить дробь");
        System.out.print("Ваш выбор: ");

        int choice = getIntInput();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    System.out.print("Введите числитель: ");
                    int numerator = getIntInput();
                    System.out.print("Введите знаменатель: ");
                    int denominator = getIntInput();

                    Fraction fraction = new Fraction(numerator, denominator);
                    System.out.println("Создана дробь: " + fraction);
                    System.out.println("Десятичное значение: " + fraction.getDoubleValue());
                    break;

                case 2:
                    Fraction f = new Fraction(3, 4);
                    System.out.println("Дробь: " + f);
                    System.out.println("Первое вычисление: " + f.getDoubleValue());
                    System.out.println("Второе вычисление (из кэша): " + f.getDoubleValue());
                    System.out.println("Третье вычисление (из кэша): " + f.getDoubleValue());
                    break;

                case 3:
                    Fraction f2 = new Fraction(1, 2);
                    System.out.println("Исходная дробь: " + f2 + " = " + f2.getDoubleValue());
                    System.out.print("Введите новый числитель: ");
                    int newNum = getIntInput();
                    System.out.print("Введите новый знаменатель: ");
                    int newDen = getIntInput();

                    f2.setNumeratorAndDenominator(newNum, newDen);
                    System.out.println("Измененная дробь: " + f2 + " = " + f2.getDoubleValue());
                    break;

                default:
                    System.out.println("Неверный выбор!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void interactiveCats() {
        System.out.println("\n=== РАБОТА С КОТАМИ ===");
        System.out.print("Введите имя кота: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        Cat cat = new Cat(name);
        System.out.println("Создан: " + cat);

        System.out.print("Сколько раз кот должен мяукнуть? ");
        int count = getIntInput();

        List<Meowable> meowables = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            meowables.add(cat);
        }

        System.out.println("До мяуканья: мяукнул " + cat.getMeowCount() + " раз");
        MeowUtils.makeAllMeow(meowables);
        System.out.println("После мяуканья: мяукнул " + cat.getMeowCount() + " раз");

        System.out.print("Сбросить счетчик мяуканий? (да/нет): ");
        String answer = scanner.nextLine().trim().toLowerCase();
        if (answer.equals("да") || answer.equals("yes") || answer.equals("д") || answer.equals("y")) {
            cat.resetMeowCount();
            System.out.println("Счетчик сброшен. Теперь: " + cat.getMeowCount() + " раз");
        }
    }

    private static void interactiveListDifference() {
        System.out.println("\n=== СИММЕТРИЧЕСКАЯ РАЗНОСТЬ СПИСКОВ ===");

        System.out.println("Введите элементы первого списка через пробел:");
        scanner.nextLine();
        String input1 = scanner.nextLine();
        List<String> list1 = Arrays.asList(input1.split(" "));

        System.out.println("Введите элементы второго списка через пробел:");
        String input2 = scanner.nextLine();
        List<String> list2 = Arrays.asList(input2.split(" "));

        List<String> result = ListUtils.symmetricDifference(list1, list2);

        System.out.println("\nСписок L1: " + list1);
        System.out.println("Список L2: " + list2);
        System.out.println("Симметрическая разность L: " + result);
    }

    private static void interactiveApplicants() {
        System.out.println("\n=== ОБРАБОТКА РЕЗУЛЬТАТОВ ТЕСТИРОВАНИЯ ===");
        System.out.print("Сколько абитуриентов обработать? ");
        int count = getIntInput();
        scanner.nextLine();

        List<Applicant> applicants = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            System.out.println("\nАбитуриент " + (i + 1) + ":");
            System.out.print("Фамилия: ");
            String lastName = scanner.nextLine();
            System.out.print("Имя: ");
            String firstName = scanner.nextLine();
            System.out.print("Балл по предмету 1 (0-100): ");
            int score1 = getIntInput();
            System.out.print("Балл по предмету 2 (0-100): ");
            int score2 = getIntInput();
            scanner.nextLine();

            applicants.add(new Applicant(lastName, firstName, score1, score2));
        }

        // БЕЗ Collectors
        List<Applicant> failedApplicants = new ArrayList<>();
        for (Applicant applicant : applicants) {
            if (applicant.isFailed()) {
                failedApplicants.add(applicant);
            }
        }

        System.out.println("\n=== РЕЗУЛЬТАТЫ ===");
        System.out.println("Всего абитуриентов: " + applicants.size());
        System.out.println("Не допущены к экзаменам (" + failedApplicants.size() + " чел.):");
        if (failedApplicants.isEmpty()) {
            System.out.println("Нет абитуриентов, не допущенных к экзаменам");
        } else {
            for (Applicant applicant : failedApplicants) {
                System.out.println(applicant);
            }
        }
    }

    private static void interactiveTextAnalysis() {
        System.out.println("\n=== АНАЛИЗ ТЕКСТА НА ЗВОНКИЕ СОГЛАСНЫЕ ===");
        System.out.println("Введите текст на русском языке:");
        scanner.nextLine();
        String text = scanner.nextLine();

        Set<Character> consonants = TextUtils.findVoicedConsonants(text);

        System.out.println("\nРезультат анализа:");
        System.out.println("Текст: " + (text.length() > 50 ? text.substring(0, 50) + "..." : text));
        System.out.println("Звонкие согласные, входящие более чем в одно слово: " + consonants);

        if (consonants.isEmpty()) {
            System.out.println("Таких согласных не найдено.");
        } else {
            System.out.println("Найдено " + consonants.size() + " согласных: " + consonants);
        }
    }

    private static void interactiveQueue() {
        System.out.println("\n=== РАБОТА С ОЧЕРЕДЬЮ ===");
        Queue<String> queue = new LinkedList<>();

        boolean adding = true;
        while (adding) {
            System.out.print("Введите элемент для добавления в очередь (или 'стоп' для завершения): ");
            String item = scanner.nextLine();

            if (item.equalsIgnoreCase("стоп") || item.equalsIgnoreCase("stop")) {
                adding = false;
            } else {
                queue.add(item);
                System.out.println("Элемент добавлен. Текущая очередь: " + queue);
            }
        }

        if (queue.isEmpty()) {
            System.out.println("Очередь пуста.");
        } else {
            System.out.println("\nИсходная очередь: " + queue);
            QueueUtils.printQueueReverse(queue);
        }
    }

    private static void interactivePoints() {
        System.out.println("\n=== ОБРАБОТКА ТОЧЕК ===");
        System.out.print("Сколько точек создать? ");
        int count = getIntInput();
        scanner.nextLine();

        List<Point> points = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            System.out.println("Точка " + (i + 1) + ":");
            System.out.print("Координата X: ");
            int x = getIntInput();
            System.out.print("Координата Y: ");
            int y = getIntInput();
            scanner.nextLine();

            points.add(new Point(x, y));
        }

        System.out.println("\nИсходные точки: " + points);

        // Создаем линию из первых двух точек
        if (points.size() >= 2) {
            Line line = new Line(points.get(0), points.get(1));
            System.out.println("Создана линия: " + line);
        }

        // Обработка БЕЗ Stream API
        // 1. Убираем дубликаты
        Set<Point> uniquePoints = new LinkedHashSet<>(points);

        // 2. Сортируем по X
        List<Point> sortedPoints = new ArrayList<>(uniquePoints);
        sortedPoints.sort(Comparator.comparingInt(Point::getX));

        // 3. Делаем Y положительными
        List<Point> finalPoints = new ArrayList<>();
        for (Point p : sortedPoints) {
            finalPoints.add(new Point(p.getX(), Math.abs(p.getY())));
        }

        // 4. Создаем ломаную
        Polyline polyline = new Polyline(finalPoints);

        System.out.println("\nРезультирующая ломаная: " + polyline);

        // Показываем точки ломаной
        System.out.println("Точки ломаной:");
        for (Point p : polyline.getPoints()) {
            System.out.println("  " + p);
        }
    }

    private static void interactiveFileProcessing() {
        System.out.println("\n=== ОБРАБОТКА ФАЙЛА С ИМЕНАМИ И НОМЕРАМИ ===");
        System.out.println("1. Использовать тестовые данные");
        System.out.println("2. Ввести свои данные");
        System.out.print("Ваш выбор: ");

        int choice = getIntInput();
        scanner.nextLine();

        String fileContent;
        if (choice == 1) {
            fileContent = "Вася:5\nПетя:3\nАня:5\nМаша:\nКоля:3\nОля:7\n";
            System.out.println("Тестовые данные:");
            System.out.println(fileContent);
        } else {
            System.out.println("Введите данные в формате 'Имя:номер' (пустая строка для завершения):");
            StringBuilder content = new StringBuilder();
            while (true) {
                String line = scanner.nextLine();
                if (line.isEmpty()) break;
                content.append(line).append("\n");
            }
            fileContent = content.toString();
        }

        try {
            // Записываем в файл
            Files.write(Paths.get("people.txt"), fileContent.getBytes());

            // Читаем файл БЕЗ Stream API
            List<String> lines = Files.readAllLines(Paths.get("people.txt"));
            Map<Integer, List<String>> result = new HashMap<>();

            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts.length == 2 && !parts[1].trim().isEmpty()) {
                    int number = Integer.parseInt(parts[1].trim());
                    String name = parts[0];
                    // Приводим имя к правильному регистру
                    String formattedName = name.substring(0, 1).toUpperCase() +
                            name.substring(1).toLowerCase();

                    // Добавляем в карту
                    if (!result.containsKey(number)) {
                        result.put(number, new ArrayList<>());
                    }
                    result.get(number).add(formattedName);
                }
            }

            System.out.println("\nРезультат группировки:");
            if (result.isEmpty()) {
                System.out.println("Нет данных для отображения");
            } else {
                for (Map.Entry<Integer, List<String>> entry : result.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            }

            // Удаляем файл
            Files.deleteIfExists(Paths.get("people.txt"));

        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
        }
    }

    // ========== ДЕМОНСТРАЦИОННЫЕ МЕТОДЫ (без Collectors) ==========

    private static void demonstrateFractions() {
        System.out.println("=== ДЕМОНСТРАЦИЯ РАБОТЫ С ДРОБЯМИ ===");

        try {
            Fraction f1 = new Fraction(3, 4);
            Fraction f2 = new Fraction(2, 6);
            Fraction f3 = new Fraction(-1, 2);

            System.out.println("Дробь 1: " + f1 + " = " + f1.getDoubleValue());
            System.out.println("Дробь 2: " + f2 + " = " + f2.getDoubleValue());
            System.out.println("Дробь 3: " + f3 + " = " + f3.getDoubleValue());

            System.out.println("Проверка кэширования:");
            double value1 = f1.getDoubleValue();
            double value2 = f1.getDoubleValue();
            System.out.println("Первое вычисление: " + value1);
            System.out.println("Второе вычисление: " + value2);

            f1.setNumeratorAndDenominator(1, 3);
            System.out.println("После изменения: " + f1 + " = " + f1.getDoubleValue());

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        System.out.println();
    }

    private static void demonstrateCats() {
        System.out.println("=== ДЕМОНСТРАЦИЯ РАБОТЫ С КОТАМИ ===");

        Cat cat = new Cat("Барсик");
        System.out.println("Создан: " + cat);

        List<Meowable> meowables = new ArrayList<>();
        meowables.add(cat);
        meowables.add(cat);

        System.out.println("До мяуканья: мяукнул " + cat.getMeowCount() + " раз");

        MeowUtils.makeAllMeow(meowables);

        System.out.println("После мяуканья: мяукнул " + cat.getMeowCount() + " раз");
        System.out.println();
    }

    private static void demonstrateListDifference() {
        System.out.println("=== СИММЕТРИЧЕСКАЯ РАЗНОСТЬ СПИСКОВ ===");

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(4, 5, 6, 7, 8);

        List<Integer> result = ListUtils.symmetricDifference(list1, list2);

        System.out.println("Список L1: " + list1);
        System.out.println("Список L2: " + list2);
        System.out.println("Симметрическая разность L: " + result);
        System.out.println();
    }

    private static void processApplicants() {
        System.out.println("=== ОБРАБОТКА РЕЗУЛЬТАТОВ ТЕСТИРОВАНИЯ ===");

        List<Applicant> applicants = new ArrayList<>();
        applicants.add(new Applicant("Ветров", "Роман", 68, 59));
        applicants.add(new Applicant("Анисимова", "Екатерина", 64, 88));
        applicants.add(new Applicant("Петров", "Иван", 25, 45));
        applicants.add(new Applicant("Сидорова", "Мария", 35, 28));
        applicants.add(new Applicant("Кузнецов", "Алексей", 20, 25));

        // БЕЗ Stream API и Collectors
        List<Applicant> failedApplicants = new ArrayList<>();
        for (Applicant applicant : applicants) {
            if (applicant.isFailed()) {
                failedApplicants.add(applicant);
            }
        }

        System.out.println("Всего абитуриентов: " + applicants.size());
        System.out.println("Не допущены к экзаменам:");
        for (Applicant applicant : failedApplicants) {
            System.out.println(applicant);
        }
        System.out.println();
    }

    private static void analyzeText() {
        System.out.println("=== АНАЛИЗ ТЕКСТА НА ЗВОНКИЕ СОГЛАСНЫЕ ===");

        String text = "Это пример текста на русском языке. " +
                "В этом тексте нужно найти все звонкие согласные буквы, " +
                "которые встречаются более чем в одном слове. " +
                "Бобр и барсук бежали по берегу быстрой реки.";

        Set<Character> consonants = TextUtils.findVoicedConsonants(text);

        System.out.println("Текст: " + text);
        System.out.println("Звонкие согласные, входящие более чем в одно слово: " + consonants);
        System.out.println();
    }

    private static void demonstrateQueue() {
        System.out.println("=== РАБОТА С ОЧЕРЕДЬЮ ===");

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= 5; i++) {
            queue.add(i);
        }

        System.out.println("Исходная очередь: " + queue);
        QueueUtils.printQueueReverse(queue);
        System.out.println();
    }

    private static void demonstrateStreamPoints() {
        System.out.println("=== ОБРАБОТКА POINT ===");

        List<Point> points = Arrays.asList(
                new Point(1, 2),
                new Point(3, 4),
                new Point(1, 2),
                new Point(5, -6),
                new Point(7, 8),
                new Point(3, 4),
                new Point(-2, -3),
                new Point(0, 0)
        );

        System.out.println("Исходные точки: " + points);

        // БЕЗ Stream API
        // 1. Убираем дубликаты
        Set<Point> uniquePoints = new LinkedHashSet<>(points);

        // 2. Сортируем по X
        List<Point> sortedPoints = new ArrayList<>(uniquePoints);
        sortedPoints.sort(Comparator.comparingInt(Point::getX));

        // 3. Делаем Y положительными
        List<Point> finalPoints = new ArrayList<>();
        for (Point p : sortedPoints) {
            finalPoints.add(new Point(p.getX(), Math.abs(p.getY())));
        }

        // 4. Создаем ломаную
        Polyline polyline = new Polyline(finalPoints);

        System.out.println("Результирующая ломаная: " + polyline);
        System.out.println();
    }

    private static void processPeopleFile() {
        System.out.println("=== ОБРАБОТКА ФАЙЛА С ИМЕНАМИ И НОМЕРАМИ ===");

        String testData = "Вася:5\nПетя:3\nАня:5\nМаша:\nКоля:3\nОля:7\n";

        try {
            Files.write(Paths.get("people.txt"), testData.getBytes());

            // Читаем файл БЕЗ Stream API
            List<String> lines = Files.readAllLines(Paths.get("people.txt"));
            Map<Integer, List<String>> result = new HashMap<>();

            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts.length == 2 && !parts[1].trim().isEmpty()) {
                    int number = Integer.parseInt(parts[1].trim());
                    String name = parts[0];
                    String formattedName = name.substring(0, 1).toUpperCase() +
                            name.substring(1).toLowerCase();

                    if (!result.containsKey(number)) {
                        result.put(number, new ArrayList<>());
                    }
                    result.get(number).add(formattedName);
                }
            }

            System.out.println("Содержимое:");
            System.out.println(testData);
            System.out.println("Результат группировки:");
            for (Map.Entry<Integer, List<String>> entry : result.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            Files.deleteIfExists(Paths.get("people.txt"));

        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        System.out.println();
    }

    // ========== ВСПОМОГАТЕЛЬНЫЙ МЕТОД ==========
    private static int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Пожалуйста, введите целое число: ");
            }
        }
    }
}