import model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee().setName("John").setId(100).setGender("Male").setSalary(25000.00));
        employees.add(new Employee().setName("Alex").setId(101).setGender("Male").setSalary(24000.00));
        employees.add(new Employee().setName("Merry").setId(102).setGender("Female").setSalary(23000.00));
        employees.add(new Employee().setName("Sam").setId(103).setGender("Male").setSalary(26000.00));
        employees.add(new Employee().setName("Angela").setId(104).setGender("Female").setSalary(27000.00));

        System.out.println("1. Function which takes one input and returns output");
        // Capitalize names and print
        Function<String,String> capitalizeFunction = String::toUpperCase;
        employees.stream().map(Employee::getName).forEach(name -> System.out.println(capitalizeFunction.apply(name)));

        System.out.println("2. Chaining of functions");
        // Capitalize then substring the length and print
        Function<String,String> substringFunction = name -> name.substring(2);
        employees.stream().map(Employee::getName).forEach(name -> System.out.println(capitalizeFunction.andThen(substringFunction).apply(name)));

        System.out.println("3. Compose method");
        // Allows us to combine multiple functions
        // function 1. capitalize
        // function 2. substring
        Function<String,String> composedResult = substringFunction.compose(capitalizeFunction);
        employees.forEach(employee -> System.out.println(composedResult.apply(employee.getName())));


        System.out.println("4. Passing function as argument to method");
        employees.forEach(employee -> {
            System.out.println(process(employee.getName(), nm -> nm.toUpperCase())); // Option 1
            System.out.println(process(employee.getName(), String::toUpperCase)); // Option 2 - Method reference
        });

        System.out.println("5. BiFunction");
        // BiFunction<T,U,R> - function that accepts two arguments and produces a result
        BiFunction<String, String, String> concat = (name,gender) -> String.format("%s %s", name,gender);
        employees.forEach(employee -> System.out.println(concat.apply(employee.getName(), employee.getGender())));

    }
    private static String process(String name,Function<String,String> capitalize){
        return capitalize.apply(name);
    }
}