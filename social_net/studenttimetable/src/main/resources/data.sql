CREATE TABLE students
(
  id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  name CHAR(25) NOT NULL,
  surname char(40) NOT NULL,
  age INT NOT NULL
);
CREATE UNIQUE INDEX students_id_uindex ON students (id);

CREATE TABLE lessons
(
  id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  name CHAR(125) NOT NULL,
  date DATETIME DEFAULT now()
);
CREATE UNIQUE INDEX lessons_id_uindex ON lessons (id);

CREATE TABLE students_visit
(
  sudent_id INT NOT NULL,
  lesson_id INT NOT NULL,
  CONSTRAINT students_visit_students_ID_fk FOREIGN KEY (sudent_id) REFERENCES students (ID) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT students_visit_lessons_ID_fk FOREIGN KEY (lesson_id) REFERENCES lessons (ID) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE UNIQUE INDEX students_visit_id_uindex ON students_visit (sudent_id, lesson_id);

INSERT INTO students (name, surname, age) VALUES ('Иван', 'Иванович', 31);
INSERT INTO students (name, surname, age) VALUES ('Петр', 'Анатольевич', 32);
INSERT INTO students (name, surname, age) VALUES ('Владимир', 'Александрович', 33);
INSERT INTO students (name, surname, age) VALUES ('Кирилл', 'Сергеевич', 34);
INSERT INTO students (name, surname, age) VALUES ('Геннадий', 'Васильевич', 35);
INSERT INTO lessons (name, date) VALUES ('Химия', '2018-12-03');
INSERT INTO lessons (name, date) VALUES ('Биология', '2018-12-02');
INSERT INTO lessons (name, date) VALUES ('Математика', '2018-11-01');
INSERT INTO lessons (name, date) VALUES ('Информатика', '2018-10-02');
INSERT INTO students_visit (sudent_id, lesson_id) VALUES (1, 2);
INSERT INTO students_visit (sudent_id, lesson_id) VALUES (1, 4);
INSERT INTO students_visit (sudent_id, lesson_id) VALUES (2, 2);
INSERT INTO students_visit (sudent_id, lesson_id) VALUES (2, 1);
INSERT INTO students_visit (sudent_id, lesson_id) VALUES (3, 1);
