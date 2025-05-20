import java.util.*;
import java.util.stream.Collectors;

class Book {
    private String title;
    private String author;
    private String genre;
    private double rating;

    public Book(String title, String author, String genre, double rating) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.rating = rating;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public double getRating() { return rating; }
}

class BookRecommendation {
    private String title;
    private double rating;

    public BookRecommendation(String title, double rating) {
        this.title = title;
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Rating: " + rating;
    }
}

public class BookRecommendationProcessor {

    // Method to process books and return paginated list of recommendations
    public static List<List<BookRecommendation>> getPaginatedRecommendations(List<Book> books, int pageSize) {
        List<BookRecommendation> filteredSorted = books.stream()
            .filter(b -> b.getGenre().equalsIgnoreCase("Science Fiction") && b.getRating() > 4.0)
            .map(b -> new BookRecommendation(b.getTitle(), b.getRating()))
            .sorted(Comparator.comparingDouble(BookRecommendation::getRating).reversed())
            .limit(10)
            .collect(Collectors.toList());

        // Paginate the results into sublists of size pageSize
        List<List<BookRecommendation>> pages = new ArrayList<>();
        for (int i = 0; i < filteredSorted.size(); i += pageSize) {
            pages.add(filteredSorted.subList(i, Math.min(i + pageSize, filteredSorted.size())));
        }
        return pages;
    }

    public static void main(String[] args) {
        List<Book> books = List.of(
            new Book("Dune", "Frank Herbert", "Science Fiction", 4.8),
            new Book("Neuromancer", "William Gibson", "Science Fiction", 4.3),
            new Book("The Martian", "Andy Weir", "Science Fiction", 4.5),
            new Book("Foundation", "Isaac Asimov", "Science Fiction", 4.1),
            new Book("Snow Crash", "Neal Stephenson", "Science Fiction", 4.2),
            new Book("Ender's Game", "Orson Scott Card", "Science Fiction", 3.9),
            new Book("Hyperion", "Dan Simmons", "Science Fiction", 4.6),
            new Book("Ready Player One", "Ernest Cline", "Science Fiction", 4.0),
            new Book("The Left Hand of Darkness", "Ursula K. Le Guin", "Science Fiction", 4.4),
            new Book("Old Man's War", "John Scalzi", "Science Fiction", 4.3),
            new Book("2001: A Space Odyssey", "Arthur C. Clarke", "Science Fiction", 4.1),
            new Book("Brave New World", "Aldous Huxley", "Science Fiction", 3.8)
        );

        List<List<BookRecommendation>> pages = getPaginatedRecommendations(books, 5);

        // Print paginated results
        int pageNum = 1;
        for (List<BookRecommendation> page : pages) {
            System.out.println("Page " + pageNum + ":");
            page.forEach(System.out::println);
            System.out.println();
            pageNum++;
        }
    }
}


