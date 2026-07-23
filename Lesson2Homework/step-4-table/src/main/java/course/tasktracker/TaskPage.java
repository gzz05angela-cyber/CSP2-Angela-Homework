package course.tasktracker;

import java.util.List;

public final class TaskPage {
    private TaskPage() {
    }

    public static String render(List<Task> tasks) {
        StringBuilder rows = new StringBuilder();
        for (Task task : tasks) {
            String status = task.completed() ? "Completed" : "Not completed";
            rows.append("<tr>")
                  .append("<td>").append(escapeHtml(task.category())).append("</td>")
                  .append("<td>").append(escapeHtml(task.title())).append("</td>")
                  .append("<td>").append(status).append("</td>")
                  .append("<td>").append(task.priority()).append("</td>")
                  .append("</tr>");
        }

        return """
                <!doctype html>
                <html lang="en">
                <head>
                  <meta charset="utf-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1">
                  <title>Task Tracker</title>
                  <style>
                    body {
                      max-width: 720px;
                      margin: 48px auto;
                      padding: 0 16px;
                      background: #f4f0e8;
                      color: #17211b;
                      font-family: system-ui, -apple-system, sans-serif;
                      line-height: 1.5;
                    }
                    h1 {
                      font-size: 2.5rem;
                      margin-bottom: 0.25rem;
                    }
                    .subtitle {
                      color: #687169;
                      margin-top: 0;
                      margin-bottom: 2rem;
                    }
                    
                    /* 表格样式 */
                    .tasks-table {
                      width: 100%;
                      border-collapse: collapse;
                      background: white;
                      border-radius: 8px;
                      overflow: hidden;
                      box-shadow: 0 1px 3px rgba(0,0,0,0.1);
                    }
                    
                    .tasks-table caption {
                      font-size: 1.1rem;
                      font-weight: 600;
                      text-align: left;
                      padding: 0 0 12px 0;
                      color: #17211b;
                      caption-side: bottom;
                    }
                    
                    .tasks-table thead {
                      background: #17211b;
                      color: white;
                    }
                    
                    .tasks-table thead th {
                      text-align: left;
                      padding: 14px 16px;
                      font-weight: 600;
                      font-size: 0.85rem;
                      letter-spacing: 0.5px;
                      text-transform: uppercase;
                    }
                    
                    .tasks-table tbody td {
                      padding: 14px 16px;
                      border-bottom: 1px solid #e8e4dc;
                      vertical-align: middle;
                    }
                    
                    .tasks-table tbody tr:last-child td {
                      border-bottom: none;
                    }
                    
                    .tasks-table tbody tr:hover {
                      background: #f8f5f0;
                    }
                    
                    /* 状态列样式 */
                    .status-completed {
                      color: #2e7d32;
                      font-weight: 600;
                    }
                    
                    .status-not-completed {
                      color: #b71c1c;
                      font-weight: 600;
                    }
                    
                    /* 优先级列样式 */
                    .priority-badge {
                      display: inline-block;
                      padding: 2px 12px;
                      border-radius: 12px;
                      font-size: 0.8rem;
                      font-weight: 600;
                      text-align: center;
                      min-width: 24px;
                    }
                    
                    .priority-1 {
                      background: #c8e6c9;
                      color: #1b5e20;
                    }
                    
                    .priority-2 {
                      background: #ffe0b2;
                      color: #bf360c;
                    }
                    
                    .priority-3 {
                      background: #ffcdd2;
                      color: #b71c1c;
                    }
                  </style>
                </head>
                <body>
                  <main>
                    <h1>Task Tracker</h1>
                    <p class="subtitle">Java generated this page.</p>
                    
                    <table class="tasks-table">
                      <caption>Current tasks</caption>
                      <thead>
                        <tr>
                          <th scope="col">Category</th>
                          <th scope="col">Task</th>
                          <th scope="col">Status</th>
                          <th scope="col">Priority</th>
                        </tr>
                      </thead>
                      <tbody>
                """ + rows + """
                      </tbody>
                    </table>
                  </main>
                </body>
                </html>
                """;
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
