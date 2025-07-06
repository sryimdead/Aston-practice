import java.util.*;
import java.util.stream.*;

class Book {
    public String name;
    private int volume;
    public int year;

    public Book(String name, int volume, int year) {
        this.name = name;
        this.volume = volume;
        this.year = year;
    }

    public int getVolume() {
        return volume;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return String.format("%s(%d,%d)",name,volume,year);
    }

}

class Student {
    public String name;
    public List<Book> books;

    public Student(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    @Override
    public String toString() {
        return "Student " + name;
    }

}//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Book b1 = new Book("Война и мир", 1300, 1869);
        Book b2 = new Book("Преступление и наказание", 600, 1866);
        Book b3 = new Book("Жизнь Пи", 360, 2001);
        Book b4 = new Book("Гарри Поттер и Дары смерти", 704, 2007);
        Book b5 = new Book("Бегущий в лабиринте", 448, 2009);
        Book b6 = new Book("Три мушкетера", 700, 1844);
        Book b7 = new Book("Гарри Поттер и орден феникса", 900, 2003);
        Book b8 = new Book("Мастер и Маргарита", 528, 1967);

        List<Book> lb1 = new ArrayList<>(5);
        lb1.add(b1);
        lb1.add(b4);
        lb1.add(b5);
        lb1.add(b7);
        lb1.add(b8);

        List<Book> lb2 = new ArrayList<>(5);
        lb2.add(b2);
        lb2.add(b3);
        lb2.add(b5);
        lb2.add(b6);
        lb2.add(b7);

        List<Book> lb3 = new ArrayList<>(5);
        lb3.add(b1);
        lb3.add(b3);
        lb3.add(b4);
        lb3.add(b5);
        lb3.add(b6);

        Student s1 = new Student("Иванов И.И.", lb1);
        Student s2 = new Student("Носов Н.Н.", lb2);
        Student s3 = new Student("Петров П.П.", lb3);

        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);

        students.stream()
                .map(s -> {
                    System.out.println(s); // Все студенты
                    System.out.println(s.books); // Книги каждого студента
                    return s.books;
                })
                .flatMap(s -> s.stream()) // Все книги
                .sorted(Comparator.comparingInt(Book::getVolume)) // Сортируем по количеству страниц
                .distinct() // Оставляем уникальные
                .filter(b -> b.year > 2000) // Фильтруем книги после 2000
                .limit(3) // Ограничение потока 3 элемента
                .findFirst() // возвращает Optional<Book>
                .ifPresentOrElse(
                book -> System.out.println("Год выпуска книги: " + book.getYear()),
                () -> System.out.println("Книга не найдена")
                );
    }
}
