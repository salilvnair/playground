package com.salilvnair.day1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamLamdaDemo {

    public static void main(String[] args) {
        // System.out.println(sortAndConcatStringArrayUsingJava8());
        System.out.println(findDistinctLanguagesFromListOfEmployees());
    }

    private static String sortAndConcatStringArrayUsingJava8() {
        String[] sample = {"Java", "Lamda", "Function", "Main", "JDK", "JVM", "Rod"};
        return Stream
                .of(sample)
                .sorted()
                .collect(Collectors.joining(""));
    }

    private static Set<String> findDistinctLanguagesFromListOfEmployees() {
        Employee rod = new Employee()
                            .setName("Rod Johnson")
                            .addLanguage("Spring")
                            .addLanguage("Spring Boot")
                            .addLanguage("Java")
                            .addLanguage("JPA");

        Employee james = new Employee()
                            .setName("James Gosling")
                            .addLanguage("Java")
                            .addLanguage("J2EE")
                            .addLanguage("JSP");

        Employee salil = new Employee()
                            .setName("Salil V Naie")
                            .addLanguage("Java")
                            .addLanguage("JSP")
                            .addLanguage("Spring")
                            .addLanguage("Javascript")
                            .addLanguage("Typescript")
                            .addLanguage("HTML")
                            .addLanguage("CSS");

        List<Employee> employees = new ArrayList<>();
        employees.add(rod);
        employees.add(james);
        employees.add(salil);

        return employees
                .stream()
                .map(employee ->  employee.getLanguages())
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
