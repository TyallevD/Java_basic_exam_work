import java.util.List;
import java.util.regex.Pattern;

class PartialSearchContact {
    public static void partialSearchByName(List<Contact> userContactList, String name) {
        int count = 0;
        if (name.contains("_")) {
            String regex = convertToPartialRegex(name);
            Pattern pattern = Pattern.compile(regex);
            Logger.addRecord("Поиск по имени: \"" + name + "\"");
            System.out.println("------------------------------------");
            System.out.println(" Найденные контакты по шаблону имени \"" + name + "\"");
            System.out.println("------------------------------------");
            for (Contact contact : userContactList) {
                if (pattern.matcher(contact.getName()).matches()) {
                    count++;
                    System.out.println(contact);
                }
            }
            System.out.println("Всего найдено: " + count);
            Logger.addRecord("Всего найдено контактов: " + count);
        } else if (name.contains("%")) {
            String regex = convertToFullRegex(name);
            Pattern pattern = Pattern.compile(regex);
            Logger.addRecord("Поиск по имени: \"" + name + "\"");
            System.out.println("------------------------------------");
            System.out.println(" Найденные контакты по шаблону имени \"" + name + "\"");
            System.out.println("------------------------------------");
            for (Contact contact : userContactList) {
                if (pattern.matcher(contact.getName()).matches()) {
                    count++;
                    System.out.println(contact);
                }
            }
            System.out.println("Всего найдено: " + count);
            Logger.addRecord("Всего найдено контактов: " + count);
        } else {
            System.out.println("По заданному шаблону ничего не найдено");
            Logger.addRecord("По заданному шаблону ничего не найдено");
        }
    }

    public static void partialSearchBySurname(List<Contact> userContactList, String surname) {
        int count = 0;
        if (surname.contains("_")) {
            String regex = convertToPartialRegex(surname);
            Pattern pattern = Pattern.compile(regex);
            Logger.addRecord("Поиск по фамилии: \"" + surname + "\"");
            System.out.println("------------------------------------");
            System.out.println(" Найденные контакты по шаблону фамилии \"" + surname + "\"");
            System.out.println("------------------------------------");
            for (Contact contact : userContactList) {
                if (pattern.matcher(contact.getSurname()).matches()) {
                    count++;
                    System.out.println(contact);
                }
            }
            System.out.println("Всего найдено: " + count);
            Logger.addRecord("Всего найдено контактов: " + count);
        } else if (surname.contains("%")) {
            String regex = convertToFullRegex(surname);
            Pattern pattern = Pattern.compile(regex);
            Logger.addRecord("Поиск по фамилии: \"" + surname + "\"");
            System.out.println("------------------------------------");
            System.out.println(" Найденные контакты по шаблону фамилии \"" + surname + "\"");
            System.out.println("------------------------------------");
            for (Contact contact : userContactList) {
                if (pattern.matcher(contact.getSurname()).matches()) {
                    count++;
                    System.out.println(contact);
                }
            }
            System.out.println("Всего найдено: " + count);
            Logger.addRecord("Всего найдено контактов: " + count);
        } else {
            System.out.println("По заданному шаблону ничего не найдено");
            Logger.addRecord("По заданному шаблону ничего не найдено");
        }
    }

    public static void partialSearchByPhone(List<Contact> userContactList, String phone) {
        int count = 0;
        if (phone.contains("_")) {
            String regex = convertToPartialRegex(phone);
            Pattern pattern = Pattern.compile(regex);
            Logger.addRecord("Поиск по номеру телефона: \"" + phone + "\"");
            System.out.println("------------------------------------");
            System.out.println(" Найденные контакты по шаблону телефона \"" + phone + "\"");
            System.out.println("------------------------------------");
            for (Contact contact : userContactList) {
                if (pattern.matcher(contact.getPhone()).matches()) {
                    count++;
                    System.out.println(contact);
                }
            }
            System.out.println("Всего найдено: " + count);
            Logger.addRecord("Всего найдено контактов: " + count);
        } else if (phone.contains("%")) {
            String regex = convertToFullRegex(phone);
            Pattern pattern = Pattern.compile(regex);
            Logger.addRecord("Поиск по номеру телефона: \"" + phone + "\"");
            System.out.println("------------------------------------");
            System.out.println(" Найденные контакты по шаблону телефона \"" + phone + "\"");
            System.out.println("------------------------------------");
            for (Contact contact : userContactList) {
                if (pattern.matcher(contact.getPhone()).matches()) {
                    count++;
                    System.out.println(contact);
                }
            }
            System.out.println("Всего найдено: " + count);
            Logger.addRecord("Всего найдено контактов: " + count);
        } else {
            System.out.println("По заданному шаблону ничего не найдено");
            Logger.addRecord("По заданному шаблону ничего не найдено");
        }
    }

    private static String convertToPartialRegex(String stringPattern) {
        String escapedString = stringPattern.replace("+", "\\+");
        String regex = escapedString.replace("_", ".");
        return "^" + regex + "$";
    }

    private static String convertToFullRegex(String stringPattern) {
        String escapedString = stringPattern.replace("+", "\\+");
        return escapedString.replace("%", ".*");
    }

    public static void partialSearchByAge(List<Contact> userContactList, int age, String direction) {
        boolean isFound = false;
        int count = 0;
        if (direction.equals("1")) {
            Logger.addRecord("Поиск по возрасту больше \"" + age + "\"");
            System.out.println("------------------------------------");
            System.out.println(" Найденные контакты по возрасту больше \"" + age + "\"");
            System.out.println("------------------------------------");
            for (Contact contact : userContactList) {
                if (contact.getAge() > age) {
                    isFound = true;
                    count++;
                    System.out.println(contact);
                }
            }
            if (isFound) {
                System.out.println("Всего найдено: " + count);
                System.out.println();
                Logger.addRecord("Всего найдено контактов: " + count);
            } else {
                System.out.println("По заданным критериям ничего не найдено");
                System.out.println();
                Logger.addRecord("По заданным критериям ничего не найдено");
            }
        } else if (direction.equals("2")) {
            Logger.addRecord("Поиск по возрасту меньше \"" + age + "\"");
            System.out.println("------------------------------------");
            System.out.println(" Найденные контакты по возрасту меньше \"" + age + "\"");
            System.out.println("------------------------------------");
            for (Contact contact : userContactList) {
                if (contact.getAge() < age) {
                    isFound = true;
                    count++;
                    System.out.println(contact);
                }
            }
            if (isFound) {
                System.out.println("Всего найдено: " + count);
                System.out.println();
                Logger.addRecord("Всего найдено контактов: " + count);
            } else {
                System.out.println("По заданным критериям ничего не найдено");
                System.out.println();
                Logger.addRecord("По заданным критериям ничего не найдено");
            }
        } else {
            System.out.println("Неверное значение поиска");
            System.out.println();
            Logger.addRecord("Неверное значение поиска");
        }
    }
}
