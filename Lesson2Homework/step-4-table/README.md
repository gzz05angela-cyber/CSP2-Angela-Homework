# Step 4 — Final (escaping, error pages, extracted rendering)

This is the canonical, finished version of the Lesson 2 project: the independent `category` field is included, rendering is extracted into `TaskPage`, HTML is escaped, unknown routes return a 404 page, and unexpected errors return a 500 page.

## Run it

```bash
gradle run
```

Open <http://localhost:8080/tasks>.

## Run the smoke test

```bash
gradle smokeTest
```

## Talking points

- Point out the third sample task, `Practice <HTML>` — show that it renders as visible text, not a broken tag, because of `escapeHtml()`.
- Trace one category from the `Task` record to the rendered category paragraph.
- Visit an unknown path and show the 404 page instead of a crash.
- Run the smoke test and explain it checks the same behavior automatically instead of by hand.
