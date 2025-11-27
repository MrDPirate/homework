package com.ga.LaS;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private List<Employee> employees = Arrays.asList(
            new Employee("Bezos, Jeff", LocalDate.of(2004, 4, 2), 68_109.00, "Male"),
            new Employee("Sheryl Sandberg", LocalDate.of(2014, 7, 1), 87_846.00, "Female"),
            new Employee("Buffet, Warren", LocalDate.of(2011, 7, 23), 95_035.00, "Male"),
            new Employee("Susan Wojcick", LocalDate.of(2015, 6, 1), 37_210.00, "Female"),
            new Employee("Zuckerberg, Mark", LocalDate.of(2016, 5, 12), 48_450.00, "Male"),
            new Employee("Brin, Sergey", LocalDate.of(2016, 8, 5), 74_416.00, "Male")
    );



    private <E> void printList(List<E> list) {
        // TODO Print out all the elements in the supplied list:
        list.stream().forEach(System.out::println);

    }

    public void getEmployeesOver50k() {
        // TODO Print a list of all employees that earn $50,000 or more
        List<Employee> over = employees.stream().filter(employee -> employee.getSalary() > 50_000.00).toList();
        System.out.println("Over 50k:");
         printList(over);
    }

    public void getEmployeeNamesHiredAfter2012() {
        // TODO Print a list of the names (not the Employee instances) of all employees who were hired on or after Jan. 1, 2012:
        // HINT: look it up for "LocalDate.of"
       List<Employee> after = employees.stream().filter(employee -> employee.getHireDate().isAfter(LocalDate.of(2012,1,1))).toList();
        System.out.println("After 2012:");
       printList(after);
    }

    public void getMaxSalary() {
        // TODO Print the maximum salary of all employees...
        Employee max = employees.stream().max(Comparator.comparing(Employee::getSalary)).orElse(null);
        assert max != null;
        System.out.println("Max Salary: "+max.getSalary());
    }

    public void getMinSalary() {
        // TODO Print the minimum salary of all employees...
        Employee min = employees.stream().min(Comparator.comparing(Employee::getSalary)).orElse(null);
        assert min != null;
        System.out.println("Min Salary: "+min.getSalary());
    }

    public void getAverageSalaries() {
        // TODO print the average salary of all Female and Male employees:
        Double averageMale = employees.stream()
                .filter(employee -> employee.getGender().equals("Male"))
                .collect(Collectors.averagingDouble(Employee::getSalary));

        double averageFemale = employees.stream()
                .filter(employee -> employee.getGender().equals("Female"))
                .mapToDouble(Employee::getSalary).average()
                .orElse(0);

        Double averageAll = employees.stream()
                        .collect(Collectors.averagingDouble(Employee::getSalary));

        System.out.println("Average Salary is: "+averageAll);
        System.out.println("Average Male Salary is: "+averageMale);
        System.out.println("Average Female Salary is: "+averageFemale);
    }

    public void getMaximumPaidEmployee() {
        // TODO use the reduce() operation to find the Employee instance of the employees list with the highest salary:
        Employee highest = employees.stream()
                .reduce((a,b)-> a.getSalary() >= b.getSalary() ? a:b).orElse(null);

        System.out.println("Maximum Paid is: "+highest);
    }

    public static void main(String[] args) {
        Main test = new Main();

        test.getMaxSalary();
        test.getMinSalary();
        test.getEmployeesOver50k();
        test.getAverageSalaries();
        test.getMaximumPaidEmployee();
        test.getEmployeeNamesHiredAfter2012();



    }
}