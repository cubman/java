CREATE TABLE users
(
  id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  name CHAR(25) NOT NULL,
  age INT NOT NULL
);
CREATE UNIQUE INDEX users_id_uindex ON users (id);

CREATE TABLE item
(
  id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  name CHAR(125) NOT NULL,
  price FLOAT4 NOT NULL
);
CREATE UNIQUE INDEX item_id_uindex ON item (id);

CREATE TABLE "order"
(
  id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  orederId INT NOT NULL,
  userId INT NOT NULL,
  itemId INT NOT NULL,
  value INT NOT NULL,
  date DATETIME DEFAULT now(),
  CONSTRAINT order_USERS_ID_fk FOREIGN KEY (userId) REFERENCES USERS (ID) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT order_ITEM_ID_fk FOREIGN KEY (itemId) REFERENCES ITEM (ID) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE UNIQUE INDEX order_id_uindex ON "order" (id);

INSERT INTO users (name, age) VALUES ('Иван', 31);
INSERT INTO users (name, age) VALUES ('Петр', 32);
INSERT INTO users (name, age) VALUES ('Владимир', 33);
INSERT INTO users (name, age) VALUES ('Кирилл', 34);
INSERT INTO users (name, age) VALUES ('Геннадий', 35);
INSERT INTO item (name, price) VALUES ('Кукуруза', 5);
INSERT INTO item (name, price) VALUES ('Яйцо', 25);
INSERT INTO item (name, price) VALUES ('Молоко', 45);
INSERT INTO item (name, price) VALUES ('Колбаса', 75);
INSERT INTO "order" (orederId, userId, itemId, value) VALUES (1, 2, 3, 6);
INSERT INTO "order" (orederId, userId, itemId, value) VALUES (1, 4, 1, 16);
INSERT INTO "order" (orederId, userId, itemId, value) VALUES (2, 2, 1, 26);
INSERT INTO "order" (orederId, userId, itemId, value) VALUES (3, 1, 4, 36);
INSERT INTO "order" (orederId, userId, itemId, value) VALUES (3, 1, 2, 46);
