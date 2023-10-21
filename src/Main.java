import model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee().setName("John").setId(100).setGender("Male").setSalary(25000.00));
        employees.add(new Employee().setName("Alex").setId(100).setGender("Male").setSalary(24000.00));
        employees.add(new Employee().setName("Merry").setId(100).setGender("Female").setSalary(23000.00));
        employees.add(new Employee().setName("Sam").setId(100).setGender("Male").setSalary(26000.00));
        employees.add(new Employee().setName("Angela").setId(100).setGender("Female").setSalary(27000.00));

        // 1. Function which takes one input and returns output
        // Capitalize names and print
        Function<String,String> capitalizeFunction = String::toUpperCase;
        employees.stream().map(Employee::getName).forEach(name -> System.out.println(capitalizeFunction.apply(name)));

        // 2. Chaining of functions
        // Capitalize then substring the length and print
        Function<String,String> substringFunction = name -> name.substring(2);
        employees.stream().map(Employee::getName).forEach(name -> System.out.println(capitalizeFunction.andThen(substringFunction).apply(name)));

        // 3. BiFunction
        // BiFunction<T,U,R> - function that accepts two arguments and produces a result
        BiFunction<String, String, String> concat = String::concat;
        employees.forEach(employee -> System.out.println(concat.apply(employee.getName(), employee.getGender())));

    }
}