package course.tasktracker;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class TaskApplication {
    private TaskApplication() {
    }

    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        HttpServer server = createServer(port);
        server.start();
        System.out.println("Task Tracker running at http://localhost:" + server.getAddress().getPort() + "/tasks");
    }

    public static HttpServer createServer(int port) throws IOException {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, "Buy groceries", "Home", false, 4));
        tasks.add(new Task(2, "Finish reading assignment", "School", true, 3));
        tasks.add(new Task(3, "Practice <HTML>", "Coding", false, 2));
        tasks.add(new Task(4, "Sleep", "Free time", false, 1));

        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", port), 0);
        server.createContext("/", exchange -> handle(exchange, tasks));
        return server;
    }

    private static void handle(HttpExchange exchange, List<Task> tasks) throws IOException {
        try {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();

            if ("GET".equals(method) && ("/".equals(path) || "/tasks".equals(path))) {
                sendHtml(exchange, 200, TaskPage.render(tasks));
                return;
            }
            sendHtml(exchange, 404, TaskPage.messagePage("Page not found", "No route matches " + path));
        } catch (Exception exception) {
            exception.printStackTrace();
            sendHtml(exchange, 500, TaskPage.messagePage("Server error", "Check the server log."));
        }
    }

    static void sendHtml(HttpExchange exchange, int status, String html) throws IOException {
        send(exchange, status, "text/html; charset=UTF-8", html);
    }

    private static void send(HttpExchange exchange, int status, String contentType, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.sendResponseHeaders(status, bytes.length);
        try (var output = exchange.getResponseBody()) {
            output.write(bytes);
        }
    }
}
