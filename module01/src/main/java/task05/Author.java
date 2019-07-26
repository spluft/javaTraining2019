package task05;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private String name;
    private short age;
    private List<Book> books;

    public Author(String name, short age) {
        this.name = name;
        this.age = age;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book){
        books.add(book);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        str.append(name);

        books.forEach(b -> str.append("\n\t")
                .append(b.getTitle())
                .append(" [")
                .append(b.getNumberOfPages())
                .append("]"));

        return str.toString();
    }
}
