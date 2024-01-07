package exercice;

import exercice.repositories.MovieRepository;
import exercice.repositories.PlaybackRepository;
import exercice.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;

import javax.management.relation.Relation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static exercice.User.Gender.*;
import static java.util.UUID.randomUUID;

public class StreamsTest {
    private MovieRepository movieRepository;
    private PlaybackRepository playbackRepository;
    private UserRepository userRepository;

    @Before
    public void setUp() {
        movieRepository = new MovieRepository().addAll(testMovies());
        userRepository = new UserRepository().addAll(testUsers());
        playbackRepository = new PlaybackRepository().addAll(testPlaybacks());
    }

	@Test
    public void should_obtain_a_list_of_horror_movies_names() {
        List<String> horrorMovies = movieRepository.stream()
                .filter(movie -> movie.getGenre().equals(Genre.Action))
                .map(movie1 -> movie1.getName())
                .collect(Collectors.toList());
        System.out.println(horrorMovies);
    }

	@Test
	public void should_obtain_a_list_of_action_movies_rated_upper_than_5() {
        List<String> actionMovies = movieRepository.stream()
                .filter(movie -> movie.getGenre().equals(Genre.Action))
                .filter(movie -> movie.getRating() > 5)
                .map(Movie::getName)
                .collect(Collectors.toList());
        System.out.println(actionMovies);


	}

	@Test
	public void should_obtain_a_list_of_movies_viewed_after_2022_11_01() {
        List<Movie> viewedAfterDate = playbackRepository.stream().parallel()
                .filter(p -> p.getDate().isAfter(LocalDate.of(2011,11, 01)))
                .map(playback -> playback.getMovie())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(viewedAfterDate);


	}

	@Test
	public void should_count_users_with_more_than_500_minutes_viewed() {
        long count = userRepository.stream()
                .filter(p->minutesOfUser(p) > 500)
                .count();
        System.out.println(count);


	}

    private int minutesOfUser(User user){
        return playbackRepository.stream()
                .filter(playback -> playback.getUser().equals(user))
                .mapToInt(playback1 -> playback1.getMinutesViewed())
                .sum();
    }

    @Test
    public void should_obtain_relation_between_movie_and_its_playbacks() {
        Map<Movie, List<Playback>> relation = playbackRepository.stream()
                .collect(Collectors.groupingBy(playback -> playback.getMovie()));

	}

	@Test
	public void should_obtain_map_with_movies_rated_upper_6_and_its_minutes_viewed_at_all() {
        Map <Movie, Integer> minutes = playbackRepository.stream()
                .filter(playback -> playback.getMovie().getRating() > 6)
                .collect(Collectors.toMap(p -> p.getMovie(), p->p.getMinutesViewed(), (m1,m2) -> m1 + m2));
    }


    private List<Movie> testMovies() {
        return List.of(
                new Movie(newId(), "Lord of the Rings I", Genre.Action, 9),
                new Movie(newId(), "Lord of the Rings II", Genre.SciFi, 8.5),
                new Movie(newId(), "The Goodfather", Genre.Crime, 9.6),
                new Movie(newId(), "Dumb and Dumber", Genre.Comedy, 5.0),
                new Movie(newId(), "The Fast and the Furious", Genre.Action, 5.5),
                new Movie(newId(), "The Fast and the Furious II", Genre.Action, 5.3),
                new Movie(newId(), "Schindler's List", Genre.History, 8.6),
                new Movie(newId(), "The Shawshank Redemption", Genre.Drama, 8.8)
        );
    }

    private String newId() {
        return randomUUID().toString();
    }

    private List<User> testUsers() {
        Random random = new Random();
        return List.of(
                new User(newId(), "Elvira", NotSet),
                new User(newId(), "Laura", Female),
                new User(newId(), "Maria", Female),
                new User(newId(), "Antonio", Female),
                new User(newId(), "Marcos", Male),
                new User(newId(), "Andres", Male),
                new User(newId(), "Yaiza", Female)
        );
    }


    private List<Playback> testPlaybacks() {
        Random random = new Random();
        return List.of(
                testPlayback(random),
                testPlayback(random),
                testPlayback(random),
                testPlayback(random),
                testPlayback(random),
                testPlayback(random),
                testPlayback(random),
                testPlayback(random),
                testPlayback(random),
                testPlayback(random),
                testPlayback(random),
                testPlayback(random)
        );
    }

    private Playback testPlayback(Random random) {
        return new Playback(
                LocalDate.now().minus(random.nextInt(100), ChronoUnit.DAYS),
                movieRepository.get(random.nextInt(movieRepository.size())),
                userRepository.get(random.nextInt(userRepository.size())),
                random.nextInt(120));
    }
}
