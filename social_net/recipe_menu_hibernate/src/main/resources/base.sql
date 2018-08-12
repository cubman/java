CREATE TABLE IF NOT EXISTS recipe
(
  id Integer AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  creation DATE NOT NULL DEFAULT now(),
  deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS ingredient
(
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS recipe_ingredient
(
  id Integer AUTO_INCREMENT PRIMARY KEY,
  recipe_id INTEGER,
  ingredient_id INTEGER,
  amount FLOAT,
  deleted BOOLEAN DEFAULT FALSE,

  CONSTRAINT recipe_ingredient_recipe_ID_fk FOREIGN KEY (recipe_id) REFERENCES recipe,
  CONSTRAINT recipe_ingredient_ingredient_ID_fk FOREIGN KEY (ingredient_id) REFERENCES ingredient
);

INSERT INTO
  recipe(name, creation)
VALUES
  ('суп', '1930-10-21'),
  ('вермишель', '1830-02-25');

INSERT INTO
  ingredient(name)
VALUES
  ('морковь'),
  ('лук'),
  ('картофель'),
  ('укроп'),
  ('огурец');

INSERT INTO
  recipe_ingredient(recipe_id, ingredient_id, amount)
VALUES
  (1, 2, 2.6),
  (1, 1, 5.5),
  (1, 4, 2.1),
  (2, 1, 0.5),
  (2, 2, 5),
  (2, 5, 1.5),
  (2, 4, 2.3);