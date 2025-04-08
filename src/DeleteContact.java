import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class DeleteContact {
    public static void deleteContactById(List<Contact> userContactList, int id) {
        for (Contact contact : userContactList) {
            if (contact.getId() == id) {
                userContactList.remove(contact);
                System.out.println("Контакт с id \"" + id + "\" был удалён");
                System.out.println();
                Logger.addRecord("Контакт с id \"" + id + "\" был удалён");
                return;
            }
        }
        System.out.println("Контакт с id \"" + id + "\" не найден");
        System.out.println();
        Logger.addRecord("Контакт с id \"" + id + "\" не найден");
    }

    public static void deleteContactByName(List<Contact> userContactList, String name) {
        Scanner scanner = new Scanner(System.in);
        int contactCount = SearchContact.searchContactByName(userContactList, name);
        if (contactCount == 1) {
            for (Contact contact : userContactList) {
                if (contact.getName().equals(name)) {
                    userContactList.remove(contact);
                    System.out.println("Контакт с именем \"" + name + "\" был удалён");
                    System.out.println();
                    Logger.addRecord("Контакт с именем \"" + name + "\" был удалён");
                    return;
                }
            }
        } else if (contactCount > 1) {
            System.out.println("Найдено несколько записей с именем \"" + name + "\"");
            Logger.addRecord("Найдено несколько записей с именем \"" + name + "\"");
            try {
                System.out.print("Введите id для удаления: ");
                int contactId = scanner.nextInt();
                scanner.nextLine();
                DeleteContact.deleteContactById(userContactList, contactId);
            } catch (InputMismatchException e) {
                System.out.println("Некорректное значение id");
                System.out.println();
                scanner.nextLine();
                Logger.addRecord("Некорректное значение id");
            }
        } else {
            System.out.println("Контакт с именем \"" + name + "\" не найден");
            System.out.println();
            Logger.addRecord("Контакт с именем \"" + name + "\" не найден");
        }
    }

    public static void deleteContactBySurname(List<Contact> userContactList, String surname) {
        Scanner scanner = new Scanner(System.in);
        int contactCount = SearchContact.searchContactBySurname(userContactList, surname);
        if (contactCount == 1) {
            for (Contact contact : userContactList) {
                if (contact.getSurname().equals(surname)) {
                    userContactList.remove(contact);
                    System.out.println("Контакт с фамилией \"" + surname + "\" был удалён");
                    System.out.println();
                    Logger.addRecord("Контакт с фамилией \"" + surname + "\" был удалён");
                    return;
                }
            }
        } else if (contactCount > 1) {
            System.out.println("Найдено несколько записей с фамилией \"" + surname + "\"");
            Logger.addRecord("Найдено несколько записей с фамилией \"" + surname + "\"");
            try {
                System.out.print("Введите id для удаления: ");
                int contactId = scanner.nextInt();
                scanner.nextLine();
                DeleteContact.deleteContactById(userContactList, contactId);
            } catch (InputMismatchException e) {
                System.out.println("Некорректное значение id");
                System.out.println();
                scanner.nextLine();
                Logger.addRecord("Некорректное значение id");
            }
        } else {
            System.out.println("Контакт с фамилией \"" + surname + "\" не найден");
            System.out.println();
            Logger.addRecord("Контакт с фамилией \"" + surname + "\" не найден");
        }
    }

    public static void deleteContactByPhone(List<Contact> userContactList, String phone) {
        Scanner scanner = new Scanner(System.in);
        int contactCount = SearchContact.searchContactByPhone(userContactList, phone);
        if (contactCount == 1) {
            for (Contact contact : userContactList) {
                if (contact.getPhone().equals(phone)) {
                    userContactList.remove(contact);
                    System.out.println("Контакт с номером телефона \"" + phone + "\" был удалён");
                    System.out.println();
                    Logger.addRecord("Контакт с номером телефона \"" + phone + "\" был удалён");
                    return;
                }
            }
        } else if (contactCount > 1) {
            System.out.println("Найдено несколько записей с номером телефона \"" + phone + "\"");
            Logger.addRecord("Найдено несколько записей с номером телефона \"" + phone + "\"");
            try {
                System.out.print("Введите id для удаления: ");
                int contactId = scanner.nextInt();
                scanner.nextLine();
                DeleteContact.deleteContactById(userContactList, contactId);
            } catch (InputMismatchException e) {
                System.out.println("Некорректное значение id");
                System.out.println();
                scanner.nextLine();
                Logger.addRecord("Некорректное значение id");
            }
        } else {
            System.out.println("Контакт с номером телефона \"" + phone + "\" не найден");
            System.out.println();
            Logger.addRecord("Контакт с номером телефона \"" + phone + "\" не найден");
        }
    }
}
