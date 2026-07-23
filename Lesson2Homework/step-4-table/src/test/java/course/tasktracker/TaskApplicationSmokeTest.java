package course.tasktracker;

import com.sun.net.httpserver.HttpServer;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public final class TaskApplicationSmokeTest {
    public static void main(String[] args) throws Exception {
        HttpServer server = TaskApplication.createServer(0);
        server.start();
        try {
            int port = server.getAddress().getPort();
            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> tasks = get(client, port, "/tasks");
            require(tasks.statusCode() == 200, "GET /tasks should return 200");
            require(tasks.body().contains("Buy groceries"), "response should contain a task");
            require(tasks.body().contains("Home"), "response should contain the task category");
            require(tasks.body().contains("Practice &lt;HTML&gt;"), "task titles should be HTML-escaped");
            require(tasks.body().contains("Priority: 2"), "response should contain priority information");

            HttpResponse<String> missing = get(client, port, "/missing");
            require(missing.statusCode() == 404, "unknown routes should return 404");
            System.out.println("Lesson 2 smoke test passed.");
        } finally {
            server.stop(0);
        }
    }

    private static HttpResponse<String> get(HttpClient client, int port, String path) throws Exception {
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:" + port + path)).GET().build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static void require(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
