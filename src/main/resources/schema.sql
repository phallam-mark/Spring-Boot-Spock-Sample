DROP TABLE IF EXISTS STARWARSPLANETS;

CREATE TABLE STARWARSPLANETS (
    name varchar(20) primary key,
    diameter int,
    climate varchar(20),
    terrain varchar(20)
    );