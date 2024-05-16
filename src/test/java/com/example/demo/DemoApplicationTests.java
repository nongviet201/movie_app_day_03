package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.model.enums.MovieType;
import com.example.demo.model.enums.UserRole;
import com.example.demo.repository.*;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private ActorReponsitory actorReponsitory;
    @Autowired
    private CommentReponsitory commentReponsitory;
    @Autowired
    private CountryReponsitory countryReponsitory;
    @Autowired
    private DirectorReponsitory directorReponsitory;
    @Autowired
    private EpisodeReponsitory episodeReponsitory;
    @Autowired
    private FavoriteReponsitory favoriteReponsitory;
    @Autowired
    private GenreReponsitory genreReponsitory;
    @Autowired
    private HistoryReponsitory historyReponsitory;
    @Autowired
    private ReviewReponsitory reviewReponsitory;
    @Autowired
    private UserReponsitory userReponsitory;

    @Test
    void saveGenre() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        for (int i = 0; i < 10; i++) {
            String name = faker.funnyName().name();
            Genre genre = Genre.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .build();
            genreReponsitory.save(genre);
        }
    }

    @Test
    void saveActor() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        for (int i = 0; i < 100; i++) {
            String name = faker.name().fullName();
            Actor actor = Actor.builder()
                    .name(name)
                    .avatar("https://placehold.co/600x400?text=" + String.valueOf(name.charAt(0)).toUpperCase())
                    .bio(faker.lorem().paragraph())
                    .build();
            actorReponsitory.save(actor);
        }
    }

    @Test
    void saveDirector() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        for (int i = 0; i < 30; i++) {
            String name = faker.name().fullName();
            Director director = Director.builder()
                    .name(name)
                    .avatar("https://placehold.co/600x400?text=" + String.valueOf(name.charAt(0)).toUpperCase())
                    .bio(faker.lorem().paragraph())
                    .build();
            directorReponsitory.save(director);
        }
    }

    @Test
    void saveCountries() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        for (int i = 0; i < 20; i++) {
            String name = faker.country().name();
            Country country = Country.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .build();
            countryReponsitory.save(country);
        }
    }

    @Test
    void saveUser() {
        Random random = new Random();
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        for (int i = 0; i < 30; i++) {
            String name = faker.name().fullName();
            User user = User.builder()
                    .username(name)
                    .password("12345")
                    .avatar("https://placehold.co/60x60?text=" + String.valueOf(name.charAt(0)).toUpperCase())
                    .email(faker.internet().emailAddress())
                    .role(i == 0 || i == 1 ? UserRole.ADMIN : UserRole.USER)
                    .createAt(LocalDate.now())
                    .updateAt(LocalDate.now())
                    .build();
            userReponsitory.save(user);
        }
    }


    @Test
    void test_movie_query() {
        List<Movie> movies = movieRepository.findAll();
        System.out.println("Sum movie: " + movies.size());
        //select * from movies where id (1,2,3)
        List movieById = movieRepository.findAllById(List.of(1, 2, 3));
        System.out.println("Sum movie by list id: " + movieById.size());
        //select * from movies where id = 1
        Movie movie = movieRepository.findById(1).orElse(null);
        System.out.println("movie" + movie);
        // update
        movie.setName("Death");
        movieRepository.save(movie);
        //sort
        List<Movie> movieSort = movieRepository.findByType(MovieType.PHIM_BO, Sort.by("name", "rating").descending());
    }

    @Test
    void test_pagination() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Movie> page = movieRepository.findByStatus(true, pageRequest);
        System.out.println("Total pages: " + page.getTotalPages());
        System.out.println("Total elements: " + page.getTotalElements());
        System.out.println("Content of page: " + page.getContent());
    }

    @Test
    void save_blog() {
        Random random = new Random();
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        List<User> users = userReponsitory.findByRole(UserRole.ADMIN);

        for (int i = 0; i < 20; i++) {
            String name = faker.book().title();
            Blog blog = Blog.builder()
                    .title(name)
                    .slug(slugify.slugify(name))
                    .description(faker.lorem().paragraph())
                    .content(faker.lorem().paragraph(100))
                    .thumbnail("https://placehold.co/600x400?text=" + String.valueOf(name.charAt(0)).toUpperCase())
                    .status(faker.random().nextBoolean())
                    .createdAt(LocalDate.now())
                    .updateAt(LocalDate.now())
                    .user(users.get(random.nextInt(users.size())))
                    .build();
            blogRepository.save(blog);
        }
    }

    @Test
    void saveComments() {
        Random random = new Random();
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        List<User> users = userReponsitory.findByRole(UserRole.USER);
        List<Blog> blogs = blogRepository.findAll();

        for (Blog blog : blogs) {
            for (int i = 0; i < random.nextInt(6) + 5; i++) {
                Comment comment = Comment.builder()
                        .content(faker.lorem().paragraph())
                        .createdAt(LocalDate.now())
                        .updateAt(LocalDate.now())
                        .user(users.get(random.nextInt(users.size())))
                        .blog(blog)
                        .build();
                commentReponsitory.save(comment);
            }
        }
    }


    @Test
    void saveMovie() {
        Random random = new Random();
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        List<Country> countries = countryReponsitory.findAll();
        List<Director> directors = directorReponsitory.findAll();
        List<Actor> actors = actorReponsitory.findAll();
        List<Genre> genres = genreReponsitory.findAll();

        for (int i = 0; i < 100; i++) {
            // random 1 country
            Country country = countries.get(random.nextInt(countries.size()));

            // random 1 - 3 the loai
            List<Genre> rdGenre = new ArrayList<>();
            for (int j = 0; j < random.nextInt(3); j++) {
                Genre genre = genres.get(random.nextInt(genres.size()));
                if (!rdGenre.contains(genre)) {
                    rdGenre.add(genre);
                }
            }

            // random dien vien
            List<Actor> rdActor = new ArrayList<>();
            for (int j = 0; j < random.nextInt(3) + 5; j++) {
                Actor actor = actors.get(random.nextInt(actors.size()));
                if (!rdActor.contains(actor)) {
                    rdActor.add(actor);
                }
            }

            // random dao dien
            List<Director> rdDirector = new ArrayList<>();
            for (int j = 0; j < random.nextInt(3); j++) {
                Director director = directors.get(random.nextInt(directors.size()));
                if (!rdDirector.contains(director)) {
                    rdDirector.add(director);
                }
            }

            String name = faker.book().title();
            Movie movie = Movie.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .description(faker.lorem().paragraph())
                    .poster("https://placehold.co/600x400?text=" + String.valueOf(name.charAt(0)).toUpperCase())
                    .releaseYear(faker.number().numberBetween(2021, 2024))
                    .rating(faker.number().randomDouble(1, 1, 10))
                    .type(MovieType.values()[random.nextInt(MovieType.values().length)])
                    .status(faker.bool().bool())
                    .trailer("https://www.youtube.com/embed/EzFXDvC-EwM?si=jA8PGFzhKNPyM6Nc")
                    .createdAt(LocalDate.now())
                    .updateAt(LocalDate.now())
                    .country(country)
                    .genres(rdGenre)
                    .actors(rdActor)
                    .directors(rdDirector)
                    .build();
            movieRepository.save(movie);
        }
    }

    @Test
    void saveReviews() {
        Random random = new Random();
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        List<User> users = userReponsitory.findByRole(UserRole.USER);
        List<Movie> movies = movieRepository.findAll();

        for (Movie movie : movies) {
            for (int i = 0; i < random.nextInt(6) + 5; i++) {
                Reviews reviews = Reviews.builder()
                        .content(faker.lorem().paragraph())
                        .rating(random.nextDouble(10))
                        .createAt(LocalDate.now())
                        .updateAt(LocalDate.now())
                        .user(users.get(random.nextInt(users.size())))
                        .movie(movie)
                        .build();
                reviewReponsitory.save(reviews);
            }
        }
    }

    @Test
    void saveEpisiode() {
        Random random = new Random();
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        List<Movie> movies = movieRepository.findAll();

        for (Movie movie : movies) {
            if (movie.getType() == MovieType.PHIM_BO) {
                for (int i = 0; i < random.nextInt(6) + 5; i++) {
                    Episode episode = Episode.builder()
                            .name("Episiode " + (i +1))
                            .duration(45)
                            .videoUrl("https://teacher.techmaster.vn/ded8c44b-aa96-4c22-8611-17ae8c9e7555")
                            .displayOrder(i+1)
                            .createAt(LocalDate.now())
                            .updateAt(LocalDate.now())
                            .movie(movie)
                            .build();
                    episodeReponsitory.save(episode);
                }
            } else {
                Episode episode = Episode.builder()
                        .name("Episiode Full")
                        .duration(100)
                        .videoUrl("https://teacher.techmaster.vn/ded8c44b-aa96-4c22-8611-17ae8c9e7555")
                        .displayOrder(1)
                        .createAt(LocalDate.now())
                        .updateAt(LocalDate.now())
                        .movie(movie)
                        .build();
                episodeReponsitory.save(episode);
            }
        }
    }

    @Test
    void saveFavorites() {
        Random random = new Random();
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();

        List<User> users = userReponsitory.findByRole(UserRole.USER);

    }
}

 