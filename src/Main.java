import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProgrammPaths.createOrCheckExistingFolders();
        Logger.addRecord("Запуск программы");
        startMenu:
        while (true) {
            System.out.print("""
                    ------------------------------------
                    |      << Телефонная книга >>      |
                    |         добро пожаловать         |
                    ------------------------------------
                    |   1 - вход                       |
                    |   2 - регистрация                |
                    |   3 - посмотреть логи            |
                    |   4 - завершение программы       |
                    ------------------------------------
                    """);
            System.out.print("Ввод команды: ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": {
                    Logger.addRecord("Начало авторизации");
                    Authorization.authorization();
                    if (Session.getCurrentLoginContacts() != null) {
                        mainMenu(Session.getCurrentLoginContacts());
                    }
                    break;
                }
                case "2": {
                    Logger.addRecord("Начало регистрации нового пользователя");
                    Authorization.registration();
                    Logger.addRecord("Конец регистрации нового пользователя");
                    break;
                }
                case "3": {
                    Logger.addRecord("Начало чтения файла логов");
                    Logger.readLogsFile();
                    Logger.addRecord("Конец чтения файла логов");
                    break;
                }
                case "4": {
                    System.out.println("""
                            ------------------------------------
                            |       Завершение программы       |
                            ------------------------------------""");
                    System.out.println();
                    Logger.addRecord("Завершение программы");
                    break startMenu;
                }
                default: {
                    System.out.println("Ваш ввод неверный, попробуйте снова");
                    System.out.println();
                    break;
                }
            }
        }
    }

    private static void mainMenu(List<Contact> userContactList) {
        Scanner scanner = new Scanner(System.in);
        mainMenu:
        while (true) {
            System.out.print("""
                    ------------------------------------
                    |      << Телефонная книга >>      |
                    |           Главное меню           |
                    ------------------------------------
                    |   1 - изменение контактов        |
                    |   2 - просмотр контактов         |
                    |   3 - выход из аккаунта          |
                    ------------------------------------
                    """);
            System.out.print("Ввод команды: ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": {
                    Logger.addRecord("Переход в меню изменения контактов");
                    contactsChangeMenu(userContactList);
                    break;
                }
                case "2": {
                    Logger.addRecord("Переход в меню просмотра контактов");
                    contactsViewMenu(userContactList);
                    break;
                }
                case "3": {
                    System.out.println("""
                            ------------------------------------
                            |    Выход на экран авторизации    |
                            ------------------------------------""");
                    System.out.println();
                    Logger.addRecord("Пользователь \"" + Session.getCurrentLogin() + "\" вышел из аккаунта");
                    Logger.addRecord("Список контактов очищен");
                    Session.setCurrentLogin(null);
                    userContactList = null;
                    break mainMenu;
                }
                default: {
                    System.out.println("Неверная команда. Попробуйте снова.");
                    System.out.println();
                }
            }
        }
    }

    private static void contactsChangeMenu(List<Contact> userContactList) {
        Scanner scanner = new Scanner(System.in);
        contactsChangeMenu:
        while (true) {
            System.out.println("""
                    ------------------------------------
                    |      << Телефонная книга >>      |
                    |       Изменение контактов        |
                    ------------------------------------
                    |   1 - добавить контакт           |
                    |   2 - поменять контакт           |
                    |   3 - удалить контакт            |
                    |   4 - выход в главное меню       |
                    ------------------------------------""");
            System.out.print("Ввод команды: ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": {
                    Logger.addRecord("Начало добавления контакта");
                    try {
                        ContactFileManager.addContact(userContactList, ContactFileManager.createContact(userContactList));
                    } catch (InputMismatchException e) {
                        System.out.println("Некорректное значение возраста. Попробуйте снова.");
                        System.out.println();
                    }
                    Logger.addRecord("Конец добавления контакта");
                    break;
                }
                case "2": {
                    Logger.addRecord("Переход в меню изменения контактов");
                    updateContactsMenu(userContactList);
                    break;
                }
                case "3": {
                    Logger.addRecord("Переход в меню удаления контактов");
                    deletionMenu(userContactList);
                    break;
                }
                case "4": {
                    Logger.addRecord("Сохранение контактов пользователя \"" + Session.getCurrentLogin() + "\" в файл " + Login.getLoginContactsPath(Session.getCurrentLogin()));
                    Logger.addRecord("Выход в главное меню");
                    ContactFileManager.saveToFile(userContactList);
                    System.out.println("""
                            ------------------------------------
                            |       Выход в главное меню       |
                            ------------------------------------""");
                    System.out.println();
                    break contactsChangeMenu;
                }
                default: {
                    System.out.println("Неверная команда. Попробуйте снова.");
                    System.out.println();
                }
            }
        }
    }

    private static void contactsViewMenu(List<Contact> userContactList) {
        Scanner scanner = new Scanner(System.in);
        contactsViewMenu:
        while (true) {
            System.out.println("""
                    ------------------------------------
                    |      << Телефонная книга >>      |
                    |        Просмотр контактов        |
                    ------------------------------------
                    |   1 - просмотреть все контакты   |
                    |   2 - поиск контактов            |
                    |   3 - сортировка контактов       |
                    |   4 - выход в главное меню       |
                    ------------------------------------""");
            System.out.print("Ввод команды: ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": {
                    Logger.addRecord("Начало просмотра всех контактов");
                    ContactFileManager.viewAllContacts(userContactList);
                    Logger.addRecord("Конец просмотра всех контактов");
                    break;
                }
                case "2": {
                    Logger.addRecord("Переход в меню поиска контактов");
                    searchContactMenu(userContactList);
                    break;
                }
                case "3": {
                    Logger.addRecord("Переход в меню сортировки контактов");
                    sortMenu(userContactList);
                    break;
                }
                case "4": {
                    Logger.addRecord("Выход в главное меню из меню просмотра контактов");
                    System.out.println("""
                            ------------------------------------
                            |       Выход в главное меню       |
                            ------------------------------------""");
                    System.out.println();
                    break contactsViewMenu;
                }
                default: {
                    System.out.println("Неверная команда. Попробуйте снова.");
                    System.out.println();
                }
            }
        }
    }

    private static void sortMenu(List<Contact> userContactList) {
        Scanner scanner = new Scanner(System.in);
        if (userContactList.isEmpty()) {
            System.out.println("""
                    ------------------------------------
                    |       Список контактов пуст      |
                    |        Заполните контакты и      |
                    |          попробуйте снова        |
                    ------------------------------------""");
            System.out.println();
            return;
        }
        sortMenu:
        while (true) {
            System.out.println("""
                    ------------------------------------
                    |      << Телефонная книга >>      |
                    |        Сортировка контактов      |
                    ------------------------------------
                    |   1 - сортировка по имени        |
                    |   2 - сортировка по фамилии      |
                    |   3 - сортировка по номеру       |
                    |   4 - сортировка по возрасту     |
                    |   5 - выход в меню просмотра     |
                    |       контактов                  |
                    ------------------------------------""");
            System.out.print("Ввод команды: ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": {
                    Logger.addRecord("Переход в меню сортировки по имени");
                    System.out.println("""
                            ------------------------------------
                            |      << Телефонная книга >>      |
                            |        Сортировка по имени       |
                            ------------------------------------
                            |   1 - сортировка по имени (A-Z)  |
                            |   2 - сортировка по имени (Z-A)  |
                            ------------------------------------""");
                    System.out.print("Ввод команды: ");
                    String destinationInput = scanner.nextLine().trim();
                    if (destinationInput.equals("1")) {
                        Logger.addRecord("Начало сортировки по имени A-Z");
                        SortContact.sortContact(userContactList, 1);
                        Logger.addRecord("Конец сортировки по имени A-Z");
                    } else if (destinationInput.equals("2")) {
                        Logger.addRecord("Начало сортировки по имени Z-A");
                        SortContact.sortContact(userContactList, 2);
                        Logger.addRecord("Конец сортировки по имени Z-A");
                    } else {
                        System.out.println("Неверная команда. Попробуйте снова.");
                        System.out.println();
                    }
                    break;
                }
                case "2": {
                    Logger.addRecord("Переход в меню сортировки по фамилии");
                    System.out.println("""
                            ------------------------------------
                            |      << Телефонная книга >>      |
                            |       Сортировка по фамилии      |
                            ------------------------------------
                            |  1 - сортировка по фамилии (A-Z) |
                            |  2 - сортировка по фамилии (Z-A) |
                            ------------------------------------""");
                    System.out.print("Ввод команды: ");
                    String destinationInput = scanner.nextLine().trim();
                    if (destinationInput.equals("1")) {
                        Logger.addRecord("Начало сортировки по фамилии A-Z");
                        SortContact.sortContact(userContactList, 3);
                        Logger.addRecord("Конец сортировки по фамилии A-Z");
                    } else if (destinationInput.equals("2")) {
                        Logger.addRecord("Начало сортировки по фамилии Z-A");
                        SortContact.sortContact(userContactList, 4);
                        Logger.addRecord("Конец сортировки по фамилии Z-A");
                    } else {
                        System.out.println("Неверная команда. Попробуйте снова.");
                        System.out.println();
                    }
                    break;
                }
                case "3": {
                    Logger.addRecord("Переход в меню сортировки по номеру телефона");
                    System.out.println("""
                            ------------------------------------
                            |      << Телефонная книга >>      |
                            |       Сортировка по номеру       |
                            ------------------------------------
                            |   1 - сортировка по номеру(0-9)  |
                            |   2 - сортировка по номеру(9-0)  |
                            ------------------------------------""");
                    System.out.print("Ввод команды: ");
                    String destinationInput = scanner.nextLine().trim();
                    if (destinationInput.equals("1")) {
                        Logger.addRecord("Начало сортировки по номеру телефона 0-9");
                        SortContact.sortContact(userContactList, 5);
                        Logger.addRecord("Конец сортировки по номеру телефона 0-9");
                    } else if (destinationInput.equals("2")) {
                        Logger.addRecord("Начало сортировки по номеру телефона 9-0");
                        SortContact.sortContact(userContactList, 6);
                        Logger.addRecord("Конец сортировки по номеру телефона 9-0");
                    } else {
                        System.out.println("Неверная команда. Попробуйте снова.");
                        System.out.println();
                    }
                    break;
                }
                case "4": {
                    Logger.addRecord("Переход в меню сортировки по возрасту");
                    System.out.println("""
                            ------------------------------------
                            |      << Телефонная книга >>      |
                            |      Сортировка по возрасту      |
                            ------------------------------------
                            |  1 - сортировка по возрасту(0-9) |
                            |  2 - сортировка по возрасту(9-0) |
                            ------------------------------------""");
                    System.out.print("Ввод команды: ");
                    String destinationInput = scanner.nextLine().trim();
                    if (destinationInput.equals("1")) {
                        Logger.addRecord("Начало сортировки по возрасту 0-9");
                        SortContact.sortContact(userContactList, 7);
                        Logger.addRecord("Конец сортировки по возрасту 0-9");
                    } else if (destinationInput.equals("2")) {
                        Logger.addRecord("Начало сортировки по возрасту 9-0");
                        SortContact.sortContact(userContactList, 8);
                        Logger.addRecord("Конец сортировки по возрасту 9-0");
                    } else {
                        System.out.println("Неверная команда. Попробуйте снова.");
                        System.out.println();
                    }
                    break;
                }
                case "5": {
                    Logger.addRecord("Выход в меню просмотра контактов из меню сортировки");
                    System.out.println("""
                            ------------------------------------
                            |       Выход в меню просмотра     |
                            |              контактов           |
                            ------------------------------------""");
                    System.out.println();
                    break sortMenu;
                }
                default: {
                    System.out.println("Неверная команда. Попробуйте снова.");
                    System.out.println();
                }
            }
        }
    }

    private static void deletionMenu(List<Contact> userContactList) {
        Scanner scanner = new Scanner(System.in);
        if (userContactList.isEmpty()) {
            System.out.println("""
                    ------------------------------------
                    |       Список контактов пуст      |
                    |        Заполните контакты и      |
                    |          попробуйте снова        |
                    ------------------------------------""");
            System.out.println();
            return;
        }
        deletionMenu:
        while (true) {
            System.out.println("""
                    ------------------------------------
                    |      << Телефонная книга >>      |
                    |        Удаление контактов        |
                    ------------------------------------
                    |   1 - удалить по id              |
                    |   2 - удалить по имени           |
                    |   3 - удалить по фамилии         |
                    |   4 - удалить по номеру          |
                    |   5 - выход в меню изменения     |
                    |       контактов                  |
                    ------------------------------------""");
            System.out.print("Ввод команды: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1": {
                    Logger.addRecord("Начало удаления контакта по id");
                    try {
                        System.out.print("Введите id для удаления: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        DeleteContact.deleteContactById(userContactList, id);
                    } catch (InputMismatchException e) {
                        System.out.println("Некорректное значение id, попробуйте снова");
                        System.out.println();
                        scanner.nextLine();
                    }
                    Logger.addRecord("Конец удаления контакта по id");
                    break;
                }
                case "2": {
                    Logger.addRecord("Начало удаления контакта по имени");
                    System.out.print("Введите имя для удаления: ");
                    String name = scanner.nextLine().trim();
                    DeleteContact.deleteContactByName(userContactList, name);
                    Logger.addRecord("Конец удаления контакта по имени");
                    break;
                }
                case "3": {
                    Logger.addRecord("Начало удаления контакта по фамилии");
                    System.out.print("Введите фамилию для удаления: ");
                    String surname = scanner.nextLine().trim();
                    DeleteContact.deleteContactBySurname(userContactList, surname);
                    Logger.addRecord("Конец удаления контакта по фамилии");
                    break;
                }
                case "4": {
                    Logger.addRecord("Начало удаления контакта по номеру телефона");
                    System.out.print("Введите номер телефона для удаления: ");
                    String phone = scanner.nextLine().trim();
                    DeleteContact.deleteContactByPhone(userContactList, phone);
                    Logger.addRecord("Конец удаления контакта по номеру телефона");
                    break;
                }
                case "5": {
                    Logger.addRecord("Выход в меню изменения контактов из меню удаления контактов");
                    System.out.println("""
                            ------------------------------------
                            |       Выход в меню изменения     |
                            |              контактов           |
                            ------------------------------------""");
                    System.out.println();
                    break deletionMenu;
                }
                default: {
                    System.out.println("Неверная команда. Попробуйте снова.");
                    System.out.println();
                }
            }
        }
    }

    private static void updateContactsMenu(List<Contact> userContactList) {
        Scanner scanner = new Scanner(System.in);
        if (userContactList.isEmpty()) {
            System.out.println("""
                    ------------------------------------
                    |       Список контактов пуст      |
                    |        Заполните контакты и      |
                    |          попробуйте снова        |
                    ------------------------------------""");
            System.out.println();
            return;
        }
        updateContactsMenu:
        while (true) {
            System.out.println("""
                    ------------------------------------
                    |      << Телефонная книга >>      |
                    |      Изменение существующих      |
                    |            контактов             |
                    ------------------------------------
                    |   1 - изменить имя контакта      |
                    |   2 - изменить фамилию контакта  |
                    |   3 - изменить номер телефона    |
                    |       контакта                   |
                    |   4 - изменить возраст контакта  |
                    |   5 - выход в меню изменения     |
                    |       контактов                  |
                    ------------------------------------""");
            System.out.print("Ввод команды: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1": {
                    Logger.addRecord("Начало изменения контакта по имени");
                    System.out.print("Введите имя для изменения: ");
                    String name = scanner.nextLine().trim();
                    UpdateContact.updateContactByName(userContactList, name);
                    Logger.addRecord("Конец изменения контакта по имени");
                    break;
                }
                case "2": {
                    Logger.addRecord("Начало изменения контакта по фамилии");
                    System.out.print("Введите фамилию для изменения: ");
                    String surname = scanner.nextLine().trim();
                    UpdateContact.updateContactBySurname(userContactList, surname);
                    Logger.addRecord("Конец изменения контакта по фамилии");
                    break;
                }
                case "3": {
                    Logger.addRecord("Начало изменения контакта по номеру телефона");
                    System.out.print("Введите номер телефона для изменения: ");
                    String phone = scanner.nextLine().trim();
                    UpdateContact.updateContactByPhone(userContactList, phone);
                    Logger.addRecord("Конец изменения контакта по номеру телефона");
                    break;
                }
                case "4": {
                    Logger.addRecord("Начало изменения контакта по возрасту");
                    System.out.print("Введите возраст для изменения: ");
                    try {
                        int age = scanner.nextInt();
                        scanner.nextLine();
                        UpdateContact.updateContactByAge(userContactList, age);
                    } catch (InputMismatchException e) {
                        System.out.println("Неверное значение возраста. Попробуйте снова");
                        System.out.println();
                    }
                    Logger.addRecord("Конец изменения контакта по возрасту");
                    break;
                }
                case "5": {
                    Logger.addRecord("Выход в меню изменения контактов из меню изменения существующих контактов");
                    System.out.println("""
                            ------------------------------------
                            |       Выход в меню изменения     |
                            |              контактов           |
                            ------------------------------------""");
                    System.out.println();
                    break updateContactsMenu;
                }
                default: {
                    System.out.println("Неверная команда. Попробуйте снова.");
                    System.out.println();
                }
            }
        }
    }

    private static void searchContactMenu(List<Contact> userContactList) {
        Scanner scanner = new Scanner(System.in);
        if (userContactList.isEmpty()) {
            System.out.println("""
                    ------------------------------------
                    |       Список контактов пуст      |
                    |        Заполните контакты и      |
                    |          попробуйте снова        |
                    ------------------------------------""");
            System.out.println();
            return;
        }
        searchContactMenu:
        while (true) {
            System.out.println("""
                    ------------------------------------
                    |      << Телефонная книга >>      |
                    |        Поиск существующих        |
                    |            контактов             |
                    ------------------------------------
                    |   1 - поиск по имени             |
                    |   2 - поиск по фамилии           |
                    |   3 - поиск по номеру телефона   |
                    |   4 - поиск по возрасту          |
                    |   5 - выход в меню просмотра     |
                    |       контактов                  |
                    ------------------------------------""");
            System.out.print("Ввод команды: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1": {
                    Logger.addRecord("Начало поиска контактов по имени");
                    System.out.println("""
                            ------------------------------------
                            |      << Телефонная книга >>      |
                            |          Поиск по имени          |
                            ------------------------------------
                            |   1 - поиск по имени (полный)    |
                            |   2 - поиск по имени (частичный) |
                            ------------------------------------""");
                    System.out.print("Ввод команды: ");
                    String searchOption = scanner.nextLine().trim();
                    if (searchOption.equals("1")) {
                        Logger.addRecord("Начало поиска контактов по имени (полный)");
                        System.out.print("Введите имя для поиска: ");
                        String name = scanner.nextLine().trim();
                        SearchContact.searchContactByName(userContactList, name);
                        Logger.addRecord("Конец поиска контактов по имени (полный)");
                    } else if (searchOption.equals("2")) {
                        Logger.addRecord("Начало поиска контактов по имени (частичный)");
                        System.out.print("Введите имя для поиска, используя символы \"_\" или \"%\": ");
                        String name = scanner.nextLine().trim();
                        PartialSearchContact.partialSearchByName(userContactList, name);
                        Logger.addRecord("Конец поиска контактов по имени (частичный)");
                    } else {
                        System.out.println("Неверная команда. Попробуйте снова.");
                        System.out.println();
                    }
                    Logger.addRecord("Конец поиска контактов по имени");
                    break;
                }
                case "2": {
                    Logger.addRecord("Начало поиска контактов по фамилии");
                    System.out.println("""
                            ------------------------------------
                            |      << Телефонная книга >>      |
                            |          Поиск по фамилии        |
                            ------------------------------------
                            | 1 - поиск по фамилии (полный)    |
                            | 2 - поиск по фамилии (частичный) |
                            ------------------------------------""");
                    System.out.print("Ввод команды: ");
                    String searchOption = scanner.nextLine().trim();
                    if (searchOption.equals("1")) {
                        Logger.addRecord("Начало поиска контактов по фамилии (полный)");
                        System.out.print("Введите фамилию для поиска: ");
                        String surname = scanner.nextLine().trim();
                        SearchContact.searchContactBySurname(userContactList, surname);
                        Logger.addRecord("Конец поиска контактов по фамилии (полный)");
                    } else if (searchOption.equals("2")) {
                        Logger.addRecord("Начало поиска контактов по фамилии (частичный)");
                        System.out.print("Введите фамилию для поиска, используя символы \"_\" или \"%\": ");
                        String surname = scanner.nextLine().trim();
                        PartialSearchContact.partialSearchBySurname(userContactList, surname);
                        Logger.addRecord("Конец поиска контактов по фамилии (частичный)");
                    } else {
                        System.out.println("Неверная команда. Попробуйте снова.");
                        System.out.println();
                    }
                    Logger.addRecord("Конец поиска контактов по фамилии");
                    break;
                }
                case "3": {
                    Logger.addRecord("Начало поиска контактов по номеру телефона");
                    System.out.println("""
                            ------------------------------------
                            |      << Телефонная книга >>      |
                            |         Поиск по телефону        |
                            ------------------------------------
                            | 1 - поиск по телефону (полный)   |
                            | 2 - поиск по телефону (частичный)|
                            ------------------------------------""");
                    System.out.print("Ввод команды: ");
                    String searchOption = scanner.nextLine().trim();
                    if (searchOption.equals("1")) {
                        Logger.addRecord("Начало поиска контактов по номеру телефона (полный)");
                        System.out.print("Введите номер для поиска: ");
                        String phone = scanner.nextLine().trim();
                        SearchContact.searchContactByPhone(userContactList, phone);
                        Logger.addRecord("Конец поиска контактов по номеру телефона (полный)");
                    } else if (searchOption.equals("2")) {
                        Logger.addRecord("Начало поиска контактов по номеру телефона (частичный)");
                        System.out.print("Введите номер для поиска, используя символы \"_\" или \"%\": ");
                        String phone = scanner.nextLine().trim();
                        PartialSearchContact.partialSearchByPhone(userContactList, phone);
                        Logger.addRecord("Конец поиска контактов по номеру телефона (частичный)");
                    } else {
                        System.out.println("Неверная команда. Попробуйте снова.");
                        System.out.println();
                    }
                    Logger.addRecord("Конец поиска контактов по номеру телефона");
                    break;
                }
                case "4": {
                    Logger.addRecord("Начало поиска контактов по возрасту");
                    System.out.println("""
                            ------------------------------------
                            |      << Телефонная книга >>      |
                            |         Поиск по возрасту        |
                            ------------------------------------
                            | 1 - поиск по возрасту (полный)   |
                            | 2 - поиск по возрасту (ниже/выше)|
                            ------------------------------------""");
                    System.out.print("Ввод команды: ");
                    String searchOption = scanner.nextLine().trim();
                    if (searchOption.equals("1")) {
                        Logger.addRecord("Начало поиска контактов по возрасту (полный)");
                        System.out.print("Введите возраст для поиска: ");
                        try {
                            int age = scanner.nextInt();
                            scanner.nextLine();
                            SearchContact.searchContactByAge(userContactList, age);
                        } catch (InputMismatchException e) {
                            System.out.println("Неверный значение возраста. Попробуйте снова");
                            System.out.println();
                            scanner.nextLine();
                        }
                        Logger.addRecord("Конец поиска контактов по возрасту (полный)");
                    } else if (searchOption.equals("2")) {
                        Logger.addRecord("Начало поиска контактов по возрасту (частичный)");
                        System.out.print("Введите возраст для поиска: ");
                        try {
                            int age = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Введите значение 1 - выше, 2 - ниже: ");
                            String destination = scanner.nextLine().trim();
                            PartialSearchContact.partialSearchByAge(userContactList, age, destination);
                        } catch (InputMismatchException e) {
                            System.out.println("Неверный значение возраста. Попробуйте снова");
                            System.out.println();
                            scanner.nextLine();
                        }
                        Logger.addRecord("Конец поиска контактов по возрасту (частичный)");
                    } else {
                        System.out.println("Неверная команда. Попробуйте снова.");
                        System.out.println();
                    }
                    Logger.addRecord("Конец поиска контактов по возрасту");
                    break;
                }
                case "5": {
                    Logger.addRecord("Выход в меню просмотра контактов из меню поиска");
                    System.out.println("""
                            ------------------------------------
                            |       Выход в меню просмотра     |
                            |              контактов           |
                            ------------------------------------""");
                    System.out.println();
                    break searchContactMenu;
                }
                default: {
                    System.out.println("Неверная команда. Попробуйте снова.");
                    System.out.println();
                }
            }
        }
    }
}