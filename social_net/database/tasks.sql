// создание представления на получечния пользователя с его покупками
create VIEW all_users_and_goods
  AS
    SELECT u.NAME as "UserName", it.NAME as "ItemName", ord.DATE as "Date" FROM
      "order" ord LEFT JOIN users u on ord.userId = u.ID LEFT JOIN ITEM it on ord.itemId = it.ID;

// запрос на получение покупок пользователя на дату
SELECT DISTINCT allUsers."ItemName" from ALL_USERS_AND_GOODS allUsers
WHERE extract(YEAR FROM allUsers."Date") = 2018 AND
      extract(MONTH FROM allUsers."Date") = 7 AND
      extract(DAY FROM allUsers."Date") = 24 AND
      allUsers."UserName" = 'Петр';

// Запрос получения товаров на дату
SELECT DISTINCT (SELECT i.NAME
                 FROM ITEM i RIGHT JOIN "order" ord on i.ID = ord.ITEMID WHERE ord.ID = o.ID) as "Res" from "order" o WHERE
  extract(YEAR FROM o.DATE) = 2018 AND
  extract(MONTH FROM o.DATE) = 7 AND
  extract(DAY FROM o.DATE) = 24;

