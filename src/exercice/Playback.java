package exercice;

import java.time.Instant;
import java.time.LocalDate;

public class Playback {
	public final LocalDate date;
	public final Movie movie;
	public final User user;
	public final int minutesViewed;

	public Playback(LocalDate date, Movie movie, User user, int minutesViewed) {
		this.date = date;
		this.movie = movie;
		this.user = user;
		this.minutesViewed = minutesViewed;
	}

	public LocalDate getDate() {
		return date;
	}

	public Movie getMovie() {
		return movie;
	}

	public User getUser() {
		return user;
	}

	public int getMinutesViewed() {
		return minutesViewed;
	}
}
