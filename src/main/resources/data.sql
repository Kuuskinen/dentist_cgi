DROP TABLE IF EXISTS dentists;

CREATE TABLE dentists (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

INSERT INTO dentists (id, name) VALUES ('1', 'Grete Paia' );
