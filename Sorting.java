import java.util.*;
class Employee implements Comparable<Employee>, Cloneable {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id); // Natural ordering by id
    }

    @Override
    public Employee clone() {
        try {
            return (Employee) super.clone(); // Shallow copy
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can’t happen
        }
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + "}";
    }
}

class EmployeeNameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.getName().compareTo(e2.getName());
    }
}

class EmployeeSalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return Double.compare(e1.getSalary(), e2.getSalary());
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(3, "Alice", 75000));
        employees.add(new Employee(1, "Bob", 50000));
        employees.add(new Employee(2, "Charlie", 100000));

        // Natural sorting by id
        Collections.sort(employees);
        System.out.println("Sorted by ID (natural order):");
        for (Employee emp : employees) {
            System.out.println(emp);
        }

        // Sorting by name
        Collections.sort(employees, new EmployeeNameComparator());
        System.out.println("\nSorted by Name:");
        for (Employee emp : employees) {
            System.out.println(emp);
        }

        // Sorting by salary
        Collections.sort(employees, new EmployeeSalaryComparator());
        System.out.println("\nSorted by Salary:");
        for (Employee emp : employees) {
            System.out.println(emp);
        }

        // Cloning an Employee
        Employee original = employees.get(0);
        Employee cloned = original.clone();

        System.out.println("\nOriginal Employee: " + original);
        System.out.println("Cloned Employee: " + cloned);
    }
}
