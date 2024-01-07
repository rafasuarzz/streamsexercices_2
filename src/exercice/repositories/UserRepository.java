package exercice.repositories;

import exercice.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UserRepository {
	List<User> users;

	public UserRepository() {
		this.users = new ArrayList<>();
	}

	public Stream<User> stream() {
		return users.stream();
	}

	public UserRepository addAll(List<User> orders) {
		this.users.addAll(orders);
		return this;
	}

	public User get(int index) {
		return users.get(index);
	}

	public int size() {
		return users.size();
	}
}