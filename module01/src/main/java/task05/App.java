package task05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        Author[] authors = generateAuthors();
        Book[] books = generateBooks(authors);

        System.out.println("Checking if some/all book(s) have more than 200 pages:");
        Stream<Book> streamOfBooks = Arrays.stream(books);
        System.out.println("More than 200 pages: " + streamOfBooks.anyMatch(b -> b.getNumberOfPages() > 200));

        streamOfBooks = Arrays.stream(books);
        System.out.println("More than 200 pages: " + streamOfBooks.filter(b -> b.getNumberOfPages() > 200)
                .peek(b -> System.out.println("\tThis book contains more than 200 pages: " + b))
                .count() + " book(s)");

        System.out.println("\nLooking for books with max number of pages:");
        streamOfBooks = Arrays.stream(books);
        System.out.println(streamOfBooks.max(Comparator.comparingInt(Book::getNumberOfPages)).get());

        System.out.println("\nLooking for books with min number of pages:");
        streamOfBooks = Arrays.stream(books);
        System.out.println(streamOfBooks.min(Comparator.comparingInt(Book::getNumberOfPages)).get());

        System.out.println("\nLooking for books with only single author:");
        streamOfBooks = Arrays.stream(books);
        streamOfBooks.filter(b -> b.getAuthors().size() == 1).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("\nBooks sorted by number of pages (ascending):");
        streamOfBooks = Arrays.stream(books);
        streamOfBooks.sorted(Comparator.comparingInt(Book::getNumberOfPages)).forEach(System.out::println);

        System.out.println("\nBooks sorted by title (descending):");
        streamOfBooks = Arrays.stream(books);
        streamOfBooks.sorted(Comparator.comparing(Book::getTitle).reversed()).forEach(System.out::println);

        System.out.println("\nAll titles:");
        streamOfBooks = Arrays.stream(books);
        streamOfBooks.map(Book::getTitle).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("\nAll authors (distincted):");
        streamOfBooks = Arrays.stream(books);
        streamOfBooks
                .map(Book::getAuthors)
                .flatMap(List::stream)
                .map(Author::getName)
                .distinct()
                .forEach(System.out::println);

        System.out.println("\nAll authors (distincted and parallel):");
        streamOfBooks = Arrays.asList(books).parallelStream();
        streamOfBooks
                .map(Book::getAuthors)
                .flatMap(List::stream)
                .map(Author::getName)
                .distinct()
                .forEach(System.out::println);

        Author someAuthor = authors[0];
        System.out.println("\nThe title of the biggest book of author " + someAuthor.getName() + ":");

        streamOfBooks = Arrays.stream(books);

        Optional<String> title = streamOfBooks.filter(b -> b.getAuthors()
                .contains(someAuthor))
                .max(Comparator.comparingInt(Book::getNumberOfPages))
                .map(Book::getTitle);

        if(title.isPresent()){
            System.out.println(title.get());
        }
        else{
            System.out.println("Not found");
        }


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
