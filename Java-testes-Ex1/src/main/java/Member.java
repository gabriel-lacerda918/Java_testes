// Member.java
public class Member {
    private String name;
    private String id;
    private String email;
    private int borrowedBooks;

    public Member(String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.borrowedBooks = 0;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBorrowedBooks() {
        return borrowedBooks;
    }

    public void incrementBorrowedBooks() {
        this.borrowedBooks++;
    }

    public void decrementBorrowedBooks() {
        if (this.borrowedBooks > 0) {
            this.borrowedBooks--;
        }
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}