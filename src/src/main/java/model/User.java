package model;

public class User {
    String name;
    String surname;
    String email;
    String position;

    public User(String name, String surname, String email, String position) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.position = position;
    }

    @Override
    public String toString() {
        return "User{" +
                "position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
