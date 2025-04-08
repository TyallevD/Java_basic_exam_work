import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Logger {
    public static void addRecord(String message) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String login;
        if (Session.getCurrentLogin() == null) {
            login = "unauthorized";
        } else {
            login = Session.getCurrentLogin();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ProgrammPaths.LOGS_FILE, true))) {
            writer.write("[" + date.format(formatter) + "] " + "[" + login + "]" + " " + message);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Не удалось выполнить запись в лог");
        }
    }

    public static void readLogsFile() {
        System.out.println("""
                ------------------------------------
                |          Логи программы          |
                ------------------------------------""");
        System.out.println();
        try (BufferedReader reader = new BufferedReader(new FileReader(ProgrammPaths.LOGS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл логов");
        }
    }
}
