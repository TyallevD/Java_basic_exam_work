import java.io.File;
import java.io.IOException;

class ProgrammPaths {

    public static final File PROJECT_DIR = new File(System.getProperty("user.dir"));
    public static final File USERS_DIR = new File(PROJECT_DIR, "users");
    public static final File USERS_FILE = new File(USERS_DIR, "users.txt");
    public static final File CONTACTS_DIR = new File(PROJECT_DIR, "contacts");
    public static final File LOGS_DIR = new File(PROJECT_DIR, "logs");
    public static final File LOGS_FILE = new File(LOGS_DIR, "log.txt");

    public static void createOrCheckExistingFolders() {

        if (!ProgrammPaths.USERS_DIR.exists()) {
            System.out.println(ProgrammPaths.USERS_DIR.mkdir() ? "Папка пользователей создана" : "Папка пользователей не создана");
        }

        if (!ProgrammPaths.CONTACTS_DIR.exists()) {
            System.out.println(ProgrammPaths.CONTACTS_DIR.mkdir() ? "Папка контактов создана" : "Папка контактов не создана");
        }

        if (!ProgrammPaths.LOGS_DIR.exists()) {
            System.out.println(ProgrammPaths.LOGS_DIR.mkdir() ? "Папка логов создана" : "Папка логов не создана");

        }

        if (!ProgrammPaths.USERS_FILE.exists()) {
            try {
                ProgrammPaths.USERS_FILE.createNewFile();
            } catch (IOException e) {
                System.out.println("Ошибка создания файла users.txt " + e.getMessage());
            }
        }

        if (!ProgrammPaths.LOGS_FILE.exists()) {
            try {
                ProgrammPaths.LOGS_FILE.createNewFile();
            } catch (IOException e) {
                System.out.println("Ошибка создания файла log.txt " + e.getMessage());
            }
        }
    }
}
