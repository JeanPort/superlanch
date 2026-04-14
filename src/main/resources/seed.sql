

INSERT IGNORE INTO category (name, created_at) VALUES
('Lanches', NOW()),
('Bebidas', NOW()),
('Sobremesas', NOW()),
('Combos', NOW());

-- LANCHES (category_id = 1)
-- =========================
INSERT INTO product (name, price, allows_addons, category_id) VALUES
('X-Burger', 20.00, true, 1),
('X-Salada', 22.00, true, 1),
('X-Bacon', 25.00, true, 1),
('X-Egg', 23.00, true, 1),
('X-Tudo', 30.00, true, 1);

-- =========================
-- BEBIDAS (category_id = 2)
-- =========================
INSERT INTO product (name, price, allows_addons, category_id) VALUES
('Coca-Cola Lata', 6.00, false, 2),
('Coca-Cola 2L', 12.00, false, 2),
('Guaraná Lata', 5.50, false, 2),
('Suco Natural Laranja', 8.00, false, 2),
('Água Mineral', 4.00, false, 2);

-- =========================
-- SOBREMESAS (category_id = 3)
-- =========================
INSERT INTO product (name, price, allows_addons, category_id) VALUES
('Sorvete Chocolate', 10.00, false, 3),
('Sorvete Baunilha', 10.00, false, 3),
('Açaí 300ml', 12.00, true, 3),
('Petit Gateau', 15.00, false, 3),
('Brownie', 9.00, false, 3);

-- =========================
-- COMBOS (category_id = 4)
-- =========================
INSERT INTO product (name, price, allows_addons, category_id) VALUES
('Combo X-Burger + Refri', 28.00, false, 4),
('Combo X-Salada + Refri', 30.00, false, 4),
('Combo X-Bacon + Refri', 32.00, false, 4),
('Combo Família', 60.00, false, 4),
('Combo Casal', 45.00, false, 4);