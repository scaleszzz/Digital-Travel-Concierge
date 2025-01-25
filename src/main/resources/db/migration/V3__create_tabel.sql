CREATE TABLE orders (
                        order_id BIGSERIAL PRIMARY KEY,
                        customer_name VARCHAR(255) NOT NULL,
                        status VARCHAR(50) NOT NULL,
                        total_price DOUBLE PRECISION NOT NULL
);

CREATE TABLE products (
                          product_id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          price DOUBLE PRECISION NOT NULL,
                          quantity INT NOT NULL
);

CREATE TABLE order_products (
                                order_id BIGINT NOT NULL,
                                product_id BIGINT NOT NULL,
                                PRIMARY KEY (order_id, product_id),
                                FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE,
                                FOREIGN KEY (product_id) REFERENCES products (product_id) ON DELETE CASCADE
);