package task05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {
        Author[] authors = generateAuthors();
        Book[] books = generateBooks(authors);
        Stream<Book> streamOfBooks = Arrays.stream(books);

        isMorePagesThen(200, streamOfBooks);
        findBigBook(streamOfBooks);
        findSmallBook(streamOfBooks);
        findSingleAuthorBooks(streamOfBooks);
        sortBooksByNumberPagesAsc(streamOfBooks);
        sortBooksByTitleDsc(streamOfBooks);
        getListOfBookTitles(streamOfBooks);
        getListOfBookAuthors(streamOfBooks);
    }

    public static void isMorePagesThen(int bookSize, Stream<Book> streamOfBooks) {
        System.out.println("Checking if some/all book(s) have more than 200 pages:");
        System.out.println("More than 200 pages: " + streamOfBooks.anyMatch(b -> b.getNumberOfPages() > bookSize));

    }

    public static void findBigBook(Stream<Book> streamOfBooks) {
        System.out.println("\nLooking for books with max number of pages:");
        System.out.println(streamOfBooks.max(Comparator.comparingInt(Book::getNumberOfPages)).get());
    }

    public static void findSmallBook(Stream<Book> streamOfBooks) {
        System.out.println("\nLooking for books with min number of pages:");
        System.out.println(streamOfBooks.min(Comparator.comparingInt(Book::getNumberOfPages)).get());
    }

    public static void findSingleAuthorBooks(Stream<Book> streamOfBooks) {
        System.out.println("\nLooking for books with only single author:");
        streamOfBooks.filter(b -> b.getAuthors().size() == 1).collect(Collectors.toList()).forEach(System.out::println);
    }

    public static void sortBooksByNumberPagesAsc(Stream<Book> streamOfBooks) {
        System.out.println("\nBooks sorted by number of pages (ascending):");
        streamOfBooks.sorted(Comparator.comparingInt(Book::getNumberOfPages)).forEach(System.out::println);
    }

    public static void sortBooksByTitleDsc(Stream<Book> streamOfBooks) {
        System.out.println("\nBooks sorted by title (descending):");
        streamOfBooks.sorted(Comparator.comparing(Book::getTitle).reversed()).forEach(System.out::println);
    }

    public static void getListOfBookTitles(Stream<Book> streamOfBooks) {
        System.out.println("\nAll titles:");
        streamOfBooks.map(Book::getTitle).collect(Collectors.toList()).forEach(System.out::println);

    }

    public static void getListOfBookAuthors(Stream<Book> streamOfBooks) {
        System.out.println("\nAll authors (distincted):");
        streamOfBooks
                .map(Book::getAuthors)
                .flatMap(List::stream)
                .map(Author::getName)
                .distinct()
                .forEach(System.out::println);
    }

    private static Author[] generateAuthors() {
        Author[] authors = new Author[]{
                new Author("Saveliy", (short) 25),
                new Author("Olga", (short) 23),
                new Author("Oleg", (short) 45),
                new Author("Alex", (short) 32)
        };

        return authors;
    }

    private static Book[] generateBooks(Author[] authors) {
        Book books[] = new Book[]{
                new Book("Interesting book", authors[0], 50),
                new Book("Book about Java", Arrays.asList(authors[1], authors[2]), 100),
                new Book("Book about photography", authors[1], 250),
                new Book("Another interesting book", Arrays.asList(authors[2], authors[0]), 110)
        };

        return books;
    }
}
