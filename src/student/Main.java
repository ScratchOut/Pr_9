package student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

interface LapClassUI {
    void sortGrade();
    Student searchName(String name) throws StudentNotFoundException;
    void printStudents();
    void addStudent(String name, int Grade) throws EmptyNameException;
}

class LabCLass implements LapClassUI{
    ArrayList<Student> students;

    public LabCLass() {
        students = new ArrayList<>();
    }


    @Override
    public void sortGrade() {
        Comparator comparator = (student1, student2) -> ((Student) student1).compareTo((Student) student2);
        students.sort(comparator);
    }

    @Override
    public Student searchName(String name) throws StudentNotFoundException {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        throw new StudentNotFoundException("Такого студента нет!");
    }

    @Override
    public void printStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Override
    public void addStudent(String name, int grade) throws EmptyNameException {
        Student student = new Student(name, grade);
        students.add(student);
    }
}

class EmptyNameException extends IOException {
    public EmptyNameException(String mes) {
        super(mes);
    }
}

class StudentNotFoundException extends IOException {
    public StudentNotFoundException(String mes) {
        super(mes);
    }
}

class Student{
    private String name;
    private int grade;

    public Student(String name, int grade) throws EmptyNameException {
        if (name.isEmpty()) {
            throw new EmptyNameException("Поле имени должно быть заполненно!");
        }
        this.name = name;
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(Student student) {
        if (grade > student.getGrade()) {
            return 1;
        } else if (grade == student.getGrade()) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}

public class Main {
    public static void main(String[] args) throws EmptyNameException, StudentNotFoundException {
        LapClassUI lapClassUI = new LabCLass();
        lapClassUI.addStudent("Ivan", 5);
        lapClassUI.addStudent("Artem", 3);
        lapClassUI.addStudent("Alex", 5);
        lapClassUI.addStudent("Sasha", 4);

        lapClassUI.printStudents();
        lapClassUI.sortGrade();
        System.out.println();
        lapClassUI.printStudents();

        System.out.println();
        System.out.println(lapClassUI.searchName("Artem"));
        lapClassUI.searchName("Ivan");
    }
}
