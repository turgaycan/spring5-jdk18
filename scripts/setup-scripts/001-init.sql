CREATE TABLE employees
(
    id          INT AUTO_INCREMENT NOT NULL,
    email       VARCHAR(100) NOT NULL,
    full_name   VARCHAR(100) NOT NULL,
    title       VARCHAR(100) NOT NULL,
    create_date DATE         NOT NULL,
    status      VARCHAR(20)  NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO employees
VALUES (1, 'turgay.can@inomera.com', 'Turgay Can', 'Software Engineer', NOW(), 'ACTIVE');
INSERT INTO employees
VALUES (2, 'serdar.kuzucu@inomera.com', 'Serdar Kuzucu', 'Software Architect', NOW(), 'ACTIVE');
INSERT INTO employees
VALUES (3, 'fatih.bozik@inomera.com', 'Fatih Bozik', 'Senior Software Engineer', NOW(), 'ACTIVE');
INSERT INTO employees
VALUES (4, 'ayberk.unsalan@inomera.com', 'Ali Ayberk Ünsalan', 'Senior Software Engineer', NOW(), 'ACTIVE');
INSERT INTO employees
VALUES (5, 'atakan.ulker@inomera.com', 'Atakan Ülker', 'Senior Software Engineer', NOW(), 'ACTIVE');
INSERT INTO employees
VALUES (6, 'melek.uzun@inomera.com', 'Melek Uzun', 'Senior Software Engineer', NOW(), 'ACTIVE');
INSERT INTO employees
VALUES (7, 'burak.kandil@inomera.com', 'Burak Kandil', 'Senior Software Engineer', NOW(), 'ACTIVE');
INSERT INTO employees
VALUES (8, 'kenan.kartal@inomera.com', 'Kenan Kartal', 'Ambitious Software Engineer', NOW(), 'ACTIVE');
INSERT INTO employees
VALUES (9, 'merve.ecevit@inomera.com', 'Merve Ecevit', 'Ambitious Software Engineer', NOW(), 'INACTIVE');
INSERT INTO employees
VALUES (10, 'birgul.ayaz@inomera.com', 'Birgül Ayaz', 'Principal Software Engineer', NOW(), 'INACTIVE');
