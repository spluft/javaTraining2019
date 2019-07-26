package task05;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private List<Author> authors;
    private int numberOfPages;

    public Book(String title, List<Author> authors, int numberOfPages) {
        this.title = title;
        this.authors = authors;
        this.numberOfPages = numberOfPages;

        for(Author author: authors){
            addAuthor(author, this);
        }
    }

    public Book(String title, Author author, int numberOfPages){
        this.title = title;
        this.authors = new ArrayList<>();
        this.numberOfPages = numberOfPages;

        authors.add(author);
        addAuthor(author, this);
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    private void addAuthor(Author author, Book book){
        author.addBook(book);
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        str.append(title).append(" [").append(numberOfPages).append("] - ");

        authors.forEach(a -> str.append(a.getName()).append("; "));

        str.replace(str.lastIndexOf(";"), str.lastIndexOf(";") + 1, "");

        return str.toString().trim();
    }
}