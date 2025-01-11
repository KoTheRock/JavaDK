import java.util.ArrayList;
import java.util.List;

public class EmployeeDirectory {
    private List<Employee> employees;

    public EmployeeDirectory() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);

    }


    public ArrayList<Employee> findByExperience(int experience) {
        ArrayList<Employee> sameExp = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getExperience() == experience) {
                sameExp.add(employee);

            }
        }
        return sameExp;
    }
    public ArrayList<String> findPhoneNumberByName(String name){
        ArrayList<String> phoneNumbers = new ArrayList<>();
        for (Employee employee : employees){
            if(employee.getName().equals(name)){
                phoneNumbers.add(employee.getPhoneNumber());
            }
        }
        return phoneNumbers;
    }



    public Employee findById(int employeeID) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == employeeID) {
                return employee;
            }
        }
        return null;
    }
}