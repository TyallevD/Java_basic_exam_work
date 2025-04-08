import java.util.List;

class Session {
    private static String currentLogin;
    private static List<Contact> currentLoginContacts;

    public Session(Login login) {
        Session.currentLogin = login.getLogin();
        Session.currentLoginContacts = login.getContactList();
    }

    public static String getCurrentLogin() {
        return currentLogin;
    }

    public static void setCurrentLogin(String currentLogin) {
        Session.currentLogin = currentLogin;
    }

    public static List<Contact> getCurrentLoginContacts() {
        return currentLoginContacts;
    }

    public static void setCurrentLoginContacts(List<Contact> currentLoginContacts) {
        Session.currentLoginContacts = currentLoginContacts;
    }
}
