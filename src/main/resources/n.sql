CREATE TABLE n (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    name VARCHAR(100),
    status VARCHAR(20),
    description TEXT,
    type VARCHAR(20),
    sub_type VARCHAR(20),
    categoria VARCHAR(20),
    sub_category VARCHAR(20),
    image_url TEXT,
    created DATETIME,
    last_updated DATETIME
);
