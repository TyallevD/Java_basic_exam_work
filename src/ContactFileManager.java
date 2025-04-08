import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class ContactFileManager {
    public static Contact createContact(List<Contact> userContactsList) throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String name = scanner.nextLine().trim();
        System.out.print("Введите фамилию: ");
        String surname = scanner.nextLine().trim();
        System.out.print("Введите номер телефона: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Введите возраст: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        int id = Contact.generateId(userContactsList);
        Contact contact = new Contact(id, name, surname, phone, age);
        return contact;
    }

    public static void saveToFile(List<Contact> userContactList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Login.getLoginContactsPath(Session.getCurrentLogin())))) {
            for (Contact contact : userContactList) {
                writer.write(contact.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    public static void addContact(List<Contact> contacts, Contact contact) {
        contacts.add(contact);
        Logger.addRecord("Добавлен контакт: " + contact);
    }

    public static void viewAllContacts(List<Contact> contactsList) {
        int count = 0;
        System.out.println("""
                ------------------------------------
                |          Ваши контакты           |
                ------------------------------------""");
        if (contactsList.isEmpty()) {
            Logger.addRecord("Список контактов пуст");
            System.out.println("""
                    ------------------------------------
                    |       Список контактов пуст      |
                    ------------------------------------""");
            System.out.println();
        }
        for (Contact contact : contactsList) {
            System.out.println(contact);
            count++;
        }
        Logger.addRecord("Всего контактов: " + count);
        System.out.println();
    }

    public static void loadContactList(Login login) {
        File contactFile = new File(Login.getLoginContactsPath(login.getLogin()));
        if (!contactFile.exists()) {
            System.out.println("Файл контактов не найден");
            System.out.println();
            Logger.addRecord("Ошибка. Файл контактов не найден");
            return;
        }
        List<Contact> contactList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(contactFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contactList.add(Contact.parse(line));
            }
            login.setContactList(contactList);
        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл: " + e.getMessage());
        }
    }
}
