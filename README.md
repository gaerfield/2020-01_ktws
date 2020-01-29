# 2020-01_ktws
Koans for a kotlin-workshop

* slides in [gaerfield/2018-04-26_Kotlin_jug-gr](https://gaerfield.github.io/2018-04-26_Kotlin_jug-gr/kotlin/index.html#/)
* Movie-Database obtained from [danielgrijalva/movie-stats](https://github.com/danielgrijalva/movie-stats)
* do practice with [Kotlin-Koans](https://play.kotlinlang.org/koans/Introduction/Hello,%20world!/Task.kt)

After succesful start of the app, you can visit:
* **database**: http://localhost:8080/h2-console
* **swagger**: http://localhost:8080/swagger-ui.html
* **rest-service**: http://localhost:8080/least10
* use **RestCalls.http** to do requests

# Exercises

## model the following Domain with kotlin

**Artist**: An individual or group who creates music
* name: The name of the artist (e.g., “The Beatles”)
* members: A set of other artists who comprise this group (e.g., “John Lennon”);
this field might be empty
* origin: The primary location of origin of the group (e.g., “Liverpool”).

**Track**: A single piece of music
* name: The name of the track (e.g., “Yellow Submarine”)

**Album**
A single release of music, comprising several tracks
* name: The name of the album (e.g., “Revolver”)
* tracks: A list of tracks
* musicians: A list of artists who helped create the music on this album

## Create some new REST-Services

We have Movie-Database and want some new REST-Services giving us insight to trivial infos. Create some new Services in "MovieService", for example:  
* group by rating (0-1, 1-2, 1-3, etc.) and give a count of movies within this groups
* top 10 studios by number of productions
* directors grouped by number of productions with top 3 by average score, i.e.:
```string
directors with 5 movies:
  Steven Spielberg (avg-score: 5.2), Francis Ford Coppola (avg.-score: 6.1)
directors with 4 movies: ..
```
* do the previous task with least avg-score instead
* which persons (actors, writers, directors) have acted in multiple roles for the same or for different movies