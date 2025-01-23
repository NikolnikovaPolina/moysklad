CREATE TABLE product (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(4096) DEFAULT '',
    price DECIMAL(10,2) CHECK (price >= 0),
    in_stock BOOLEAN DEFAULT FALSE
);

COMMENT ON COLUMN product.id IS 'Unique numerical product identifier';
COMMENT ON COLUMN product.name IS 'Product name';
COMMENT ON COLUMN product.description IS 'Product description';
COMMENT ON COLUMN product.price IS 'Product price';
COMMENT ON COLUMN product.in_stock IS 'Is the product in stock';