package exercice;

public class Movie {

	public final String id;
	public final String name;
	public final Genre genre;
	public final double rating;

	public Movie(String id, String name,Genre genre, double rating) {
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.rating = rating;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Genre getGenre() {
		return genre;
	}

	public double getRating() {
		return rating;
	}
}
