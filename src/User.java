public class User {
    private String name;
    private String age;
    private String email;
    private String id;

    public User() {

    }

    public String getEmail() {
        return email;
    }

    public User(String name, String age, String email, String id) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return name + " " + " " + age + " " + email + " " + id;
    }
}