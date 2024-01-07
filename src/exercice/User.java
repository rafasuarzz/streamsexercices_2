package exercice;

public class User {

	public enum Gender{Male, Female, NotSet}
	public final String id;
	public final String name;
	public final Gender gender;

	public User(String id, String name, Gender gender) {
		this.id = id;
		this.name = name;
		this.gender = gender;
	}
}
