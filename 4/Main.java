public class Main {
    public static void main(String[] args) {
        EmployeeDirectory directory = new EmployeeDirectory();


        directory.addEmployee(new Employee(1, "+1234567890", "Иван", 5));
        directory.addEmployee(new Employee(2, "+1987654321", "Одинаковое Имя", 3));
        directory.addEmployee(new Employee(3, "+1987654321", "Одинаковое Имя", 7));
        directory.addEmployee(new Employee(4, "+1122334455", "ОдинСтаж", 10));
        directory.addEmployee(new Employee(5, "+1574525235", "ОдинаковыйСтаж", 10));


        System.out.println(directory.findByExperience(10));
        System.out.println(directory.findPhoneNumberByName("Одинаковое Имя"));
        System.out.println(directory.findById(3));
    }
}
