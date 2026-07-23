package course.tasktracker;

public record Task(long id, String title, String category, boolean completed, int priority) {
}
