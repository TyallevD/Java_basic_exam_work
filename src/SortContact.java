import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class SortContact {
    public static void sortContact(List<Contact> contacts, int destination) {
        switch (destination) {
            case 1: {
                System.out.println("""
                        ------------------------------------
                        |     Сортировка по имени (A-Z)    |
                        ------------------------------------""");
                System.out.println();
                List<Contact> sortedByNameAZ = contacts.stream().sorted((a, b) -> a.getName().compareTo(b.getName())).collect(Collectors.toList());
                for (Contact contact : sortedByNameAZ) {
                    System.out.println(contact);
                }
                Logger.addRecord("Сортировка по имени (A-Z) выполнена успешно");
                break;
            }
            case 2: {
                System.out.println("""
                        ------------------------------------
                        |     Сортировка по имени (Z-A)    |
                        ------------------------------------""");
                System.out.println();
                List<Contact> sortedByNameZA = contacts.stream().sorted((a, b) -> b.getName().compareTo(a.getName())).collect(Collectors.toList());
                for (Contact contact : sortedByNameZA) {
                    System.out.println(contact);
                }
                Logger.addRecord("Сортировка по имени (Z-A) выполнена успешно");
                break;
            }
            case 3: {
                System.out.println("""
                        ------------------------------------
                        |    Сортировка по фамилии (A-Z)   |
                        ------------------------------------""");
                System.out.println();
                List<Contact> sortedBySurnameAZ = contacts.stream().sorted((a, b) -> a.getSurname().compareTo(b.getSurname())).collect(Collectors.toList());
                for (Contact contact : sortedBySurnameAZ) {
                    System.out.println(contact);
                }
                Logger.addRecord("Сортировка по фамилии (A-Z) выполнена успешно");
                break;
            }
            case 4: {
                System.out.println("""
                        ------------------------------------
                        |    Сортировка по фамилии (Z-A)   |
                        ------------------------------------""");
                System.out.println();
                List<Contact> sortedBySurnameZA = contacts.stream().sorted((a, b) -> b.getSurname().compareTo(a.getSurname())).collect(Collectors.toList());
                for (Contact contact : sortedBySurnameZA) {
                    System.out.println(contact);
                }
                Logger.addRecord("Сортировка по фамилии (Z-A) выполнена успешно");
                break;
            }
            case 5: {
                System.out.println("""
                        ------------------------------------
                        |    Сортировка по номеру (0-9)    |
                        ------------------------------------""");
                System.out.println();
                List<Contact> sortedByPhoneForward = contacts.stream().sorted((a, b) -> a.getPhone().compareTo(b.getPhone())).collect(Collectors.toList());
                for (Contact contact : sortedByPhoneForward) {
                    System.out.println(contact);
                }
                Logger.addRecord("Сортировка по номеру (0-9) выполнена успешно");
                break;
            }
            case 6: {
                System.out.println("""
                        ------------------------------------
                        |    Сортировка по номеру (9-0)    |
                        ------------------------------------""");
                System.out.println();
                List<Contact> sortedByPhoneBackward = contacts.stream().sorted((a, b) -> b.getPhone().compareTo(a.getPhone())).collect(Collectors.toList());
                for (Contact contact : sortedByPhoneBackward) {
                    System.out.println(contact);
                }
                Logger.addRecord("Сортировка по номеру (9-0) выполнена успешно");
                break;
            }
            case 7: {
                System.out.println("""
                        ------------------------------------
                        |    Сортировка по возрасту (0-9)  |
                        ------------------------------------""");
                System.out.println();
                List<Contact> sortedByAgeForward = contacts.stream().sorted(Comparator.comparingInt(Contact::getAge)).collect(Collectors.toList());
                for (Contact contact : sortedByAgeForward) {
                    System.out.println(contact);
                }
                Logger.addRecord("Сортировка по возрасту (0-9) выполнена успешно");
                break;
            }
            case 8: {
                System.out.println("""
                        ------------------------------------
                        |    Сортировка по возрасту (9-0)  |
                        ------------------------------------""");
                System.out.println();
                List<Contact> sortedByAgeBackward = contacts.stream().sorted(Comparator.comparingInt(Contact::getAge).reversed()).collect(Collectors.toList());
                for (Contact contact : sortedByAgeBackward) {
                    System.out.println(contact);
                }
                Logger.addRecord("Сортировка по возрасту (9-0) выполнена успешно");
                break;
            }
            default: {
                System.out.println("Непредвиденная ошибка, попробуйте снова");
                System.out.println();
                Logger.addRecord("Непредвиденная ошибка, попробуйте снова");
            }
        }
    }
}
