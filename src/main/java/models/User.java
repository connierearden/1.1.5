package models;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;

    public User() {
    }

    public User(Long id, String name, int age, String password, String role) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password = password;
        this.role = role;
    }

    public User(String name, int age, String password, String role) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    private void setRole(String role) {
        String[] array = {"admin", "user"};
        Set<String> set = new HashSet<>(Arrays.asList(array));
        this.role = set.contains(role) ? role : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (!id.equals(user.id)) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}