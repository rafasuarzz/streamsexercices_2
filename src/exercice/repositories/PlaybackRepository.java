package exercice.repositories;

import exercice.Playback;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PlaybackRepository {
	List<Playback> playbacks;

	public PlaybackRepository() {
		this.playbacks = new ArrayList<>();
	}

	public Stream<Playback> stream() {
		return playbacks.stream();
	}

	public PlaybackRepository addAll(List<Playback> orders) {
		this.playbacks.addAll(orders);
		return this;
	}

	public int size() {
		return playbacks.size();
	}
}
