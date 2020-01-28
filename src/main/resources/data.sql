insert into movie(id, budget, company, country, director, genre, gross, name, rating, released, runtime_In_Min, score, star, votes, writer, year)
select * from CSVREAD('./src/main/resources/movies.csv');

update movie set version=0 where version is null