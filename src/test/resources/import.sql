CREATE TABLE project
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE task
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    create_date DATE,
    finish_date DATE,
    active      BOOLEAN,
    priority    INT,
    project_id  INT,
    foreign key (project_id) references project (id) ON DELETE CASCADE
);

INSERT INTO project(id, name)
VALUES(1, 'Java Project'),
      (2, 'SQL Project'),
      (3, 'Angular project');

INSERT INTO task(id, title, description, create_date, finish_date, active, project_id, priority)
VALUES (1, 'Create ToDoApp', 'First description', '2021-05-03 00:00:00', '2021-05-03 00:00:00', false, 1, 1),
       (2, 'Learn Angular', 'Second description', '2021-05-03 00:00:00', '2021-05-03 00:00:00', false, 2, 2),
       (3, 'Go for a walk', 'Third description', '2021-05-03 00:00:00', '2021-05-03 00:00:00', true, 2, 3),
       (4, 'Buy chocolate', 'Fourth description', '2021-05-03 00:00:00', '2021-05-03 00:00:00', true, 1, 2);

INSERT INTO task(id,title, create_date,  active, project_id, priority)
VALUES (5, 'Buy pepsi', '2021-05-03 00:00:00', true, 1, 2);

SELECT setval('task_id_seq', (SELECT MAX(id) FROM task));
SELECT setval('project_id_seq', (SELECT MAX(id) FROM project));
