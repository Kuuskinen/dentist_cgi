DROP TABLE IF EXISTS dentists;
DROP TABLE IF EXISTS dentist_visit;

CREATE TABLE dentists (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

INSERT INTO dentists (name) VALUES ( 'Grete Paia' );
INSERT INTO dentists (name) VALUES ( 'John Wick' );
INSERT INTO dentists (name) VALUES ( 'Kate Middleton' );
INSERT INTO dentists (name) VALUES ( 'Jason Bourne' );
INSERT INTO dentists (name) VALUES ( 'Mark Watney' );

CREATE TABLE dentist_visit (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    dentist_id LONG NOT NULL,
    foreign key (dentist_id) REFERENCES dentists(id)
);

INSERT INTO dentist_visit (date, dentist_id) VALUES ('2021-05-03', 2 );
INSERT INTO dentist_visit (date, dentist_id) VALUES ('2021-05-08', 3 );
INSERT INTO dentist_visit (date, dentist_id) VALUES ('2021-05-03', 5 );

