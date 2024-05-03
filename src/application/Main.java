package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();
        System.out.print("Enter salary: ");
        double avg = sc.nextDouble();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            List<Employee> list = new ArrayList<>();

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Employee(fields[0], (fields[1]), Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            System.out.printf("Email of people whose salary is more than %.2f%n", avg);

            //CONVERTER STRING PARA MAIUSCULO E COMPARA-LAS
            Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

            //LISTA COM METODOS PARA FILTRAR EMAILS QUE RECEBEM VALORES MAIOR QUE 'avg'
            List<String> names = list.stream()
                    .filter(e -> e.getSalary() > avg)
                    .map(Employee::getEmail)
                    .sorted(comp)
                    .collect(Collectors.toList());

            names.forEach(System.out::println);


            System.out.print("Enter the letter: ");
            //LER CARACTER E CONVERTER EM MAIUSCULO
            char x = sc.next().toUpperCase().charAt(0);

            System.out.print("Sum of salary of people whose name starts with '" + x + "': ");

            //REALIZAR A SOMA DE TODOS OS VALORES ONDE 'x' É A PRIMEIRA LETRA DO NOME INFORMADO LOGO ACIMA
            double sumM = list.stream()
                    .filter(e -> e.getName().charAt(0) == x)
                    .map(Employee::getSalary)
                    .reduce(0.0, Double::sum);

            System.out.println(sumM);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}