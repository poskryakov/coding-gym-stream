package codinggym.stream;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Post {
    private String title;
    private List<String> authors;
    private PostType type; // BLOG, INTERVIEW, PODCAST
    private LocalDate date;
    private URL url;
    private int likes;

    public Post(String title, List<String> authors, PostType type, LocalDate date, String urlString, int likes) {
        this.title = title;
        this.authors = authors;
        this.type = type;
        this.date = date;
        try {
            this.url = new URL(urlString);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public PostType getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public URL getUrl() {
        return url;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", type=" + type +
                ", date=" + date +
                ", url=" + url +
                ", likes=" + likes +
                '}';
    }

    // HttpClient появился в Java 11
    public String fetchPage() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(url.toURI())
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        }
        catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // HttpClient появился в Java 11
    // CompletableFuture появился в Java 8
    public CompletableFuture<String> fetchPageAsync() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(url.toURI())
                    .build();

            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body);
        }
        catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
