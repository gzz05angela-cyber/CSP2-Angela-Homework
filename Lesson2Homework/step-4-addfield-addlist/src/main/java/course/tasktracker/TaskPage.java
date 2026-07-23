package course.tasktracker;

import java.util.List;

public final class TaskPage {
    private TaskPage() {
    }

    public static String render(List<Task> tasks) {
        StringBuilder items = new StringBuilder();
        for (Task task : tasks) {
            String cssClass = task.completed() ? "task completed" : "task";
            String status = task.completed() ? "Completed" : "Not completed";
            items.append("<li class=\"").append(cssClass).append("\">")
                    .append("<p class=\"category\">").append(escapeHtml(task.category())).append("</p>")
                    .append("<h2>").append(escapeHtml(task.title())).append("</h2>")
                    .append("<p>").append(status).append("</p>")
                    .append("<p>Priority: ").append(task.priority()).append("</p></li>");
        }

        return """
                <!doctype html>
                <html lang="en">
                <head>
                  <meta charset="utf-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1">
                  <title>Task Tracker</title>
                  <style>
                    body{max-width:720px;margin:48px auto;padding:0 16px;background:#f4f0e8;color:#17211b;font-family:system-ui,sans-serif}
                    h1{font-size:3rem}.tasks{padding:0;list-style:none}.task{padding:18px 0;border-top:1px solid #bdb3a5}
                    .task h2{margin:0 0 6px}.task p{margin:0}.category{color:#994429;font-size:.75rem;font-weight:800;text-transform:uppercase}.completed h2{text-decoration:line-through;color:#687169}
                    .priority {font-size: 0.9rem;font-weight: 600;color: #1a5a8c;margin-top: 4px;font-family: monospace;}
                  </style>
                </head>
                <body><main><h1>Task Tracker</h1><p>Java generated this page.</p><ul class="tasks">
                """ + items + "</ul></main></body></html>";
    }

    public static String messagePage(String title, String message) {
        return "<!doctype html><html lang=\"en\"><meta charset=\"utf-8\"><title>"
                + escapeHtml(title) + "</title><h1>" + escapeHtml(title) + "</h1><p>"
                + escapeHtml(message) + "</p></html>";
    }

    public static String escapeHtml(String value) {
        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
