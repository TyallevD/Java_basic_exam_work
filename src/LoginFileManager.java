import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

class LoginFileManager {
    public void createLoginContacts(String login) throws IOException {
        Path contactsFilePath = Paths.get(ProgrammPaths.CONTACTS_DIR.getPath());
        File file = new File(contactsFilePath + "/" + login.concat("Contacts.txt"));
        file.createNewFile();
    }

    public void addLogin(Login login) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ProgrammPaths.USERS_FILE.getPath(), true))) {
            writer.write(login.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Не удалось записать информацию в файл: " + e.getMessage());
        }
    }

    public static boolean checkLogin(String login, String password) {
        File usersFile = ProgrammPaths.USERS_FILE;
        try (BufferedReader reader = new BufferedReader(new FileReader(usersFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 4 && parts[2].equals(login) && parts[3].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла пользователей: " + e.getMessage());
        }
        return false;
    }
}
