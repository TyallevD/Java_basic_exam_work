import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Login {
    private String name;
    private String surname;
    private String login;
    private String password;
    private List<Contact> contactList;

    public Login(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.contactList = new ArrayList<>();
    }

    public Login(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public static String getLoginContactsPath(String login) {
        return ProgrammPaths.CONTACTS_DIR + "/" + login.concat("Contacts.txt");
    }

    public static String generateLogin(String name, String surname) {
        return name.substring(0, 2).concat("-").concat(surname);
    }

    public static boolean isLoginExists(String login) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ProgrammPaths.USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] readedLine = line.split("\\s+");
                if (readedLine[2].equals(login)) {
                    System.out.println("Такой логин уже зарегистрирован");
                    System.out.println();
                    Logger.addRecord("Такой логин уже зарегистрирован");
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл пользователей");
        }
        return false;
    }

    public static String manualCreateLogin() {
        Scanner scanner = new Scanner(System.in);
        String manualLogin;
        do {
            System.out.print("Придумайте и введите свой логин: ");
            manualLogin = scanner.nextLine().trim();
            System.out.println();
        } while (isLoginExists(manualLogin));
        Logger.addRecord("Ручное создание логина выполнено успешно");
        return manualLogin;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + login + " " + password;
    }
}
