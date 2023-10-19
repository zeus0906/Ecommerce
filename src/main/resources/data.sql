INSERT INTO produit VALUES(1, 'Ordinateur portable' , 350, 120);
INSERT INTO produit VALUES(2, 'Aspirateur Robot' , 500, 200);
INSERT INTO produit VALUES(3, 'Table de Ping Pong' , 750, 400);

UPDATE produit set nom = 'Samsung', prix = 70000, prix_achat = 65000 where id = 1;
UPDATE produit set nom = 'Xiaomi', prix = 90000, prix_achat = 80000 where id = 2;
UPDATE produit set nom = 'Iphone 12', prix = 900000, prix_achat = 850000 where id = 3;
