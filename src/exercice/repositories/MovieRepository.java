package exercice.repositories;

import exercice.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MovieRepository {
	private List<Movie> movies;

	public MovieRepository() {
		movies = new ArrayList<>();
	}

	public Stream<Movie> stream() {
		return movies.stream();
	}


	public MovieRepository addAll(List<Movie> products) {
		this.movies.addAll(products);
		return this;
	}

	public Movie get(int index) {
		return movies.get(index);
	}


	public int size() {
		return movies.size();
	}
}
