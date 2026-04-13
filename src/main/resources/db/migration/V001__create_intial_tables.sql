
CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    created_at NOT NULL TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    category_id NOT NULL,
    allows_addons BOOLEAN NOT NULL DEFAULT FALSE,
    created_at NOT NULL TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_product_addon_product
            FOREIGN KEY (category_id) REFERENCES category(id)
            ON DELETE CASCADE
);

CREATE TABLE addon (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    created_at NOT NULL TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE product_addon (
    product_id BIGINT NOT NULL,
    addon_id BIGINT NOT NULL,

    PRIMARY KEY (product_id, addon_id),

    CONSTRAINT fk_product_addon_product
        FOREIGN KEY (product_id) REFERENCES product(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_product_addon_addon
        FOREIGN KEY (addon_id) REFERENCES addon(id)
        ON DELETE CASCADE
);

CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    total_price DECIMAL(10,2),
    created_at NOT NULL TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE order_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,

    CONSTRAINT fk_order_item_order
        FOREIGN KEY (order_id) REFERENCES orders(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_order_item_product
        FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE order_item_addon (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_item_id BIGINT NOT NULL,
    addon_id BIGINT NOT NULL,
    price DECIMAL(10,2) NOT NULL,

    CONSTRAINT fk_order_item_addon_item
        FOREIGN KEY (order_item_id) REFERENCES order_item(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_order_item_addon_addon
        FOREIGN KEY (addon_id) REFERENCES addon(id)
);