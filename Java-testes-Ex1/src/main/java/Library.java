// Library.java
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private List<Loan> loans;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    // Book management methods
    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(String ISBN) {
        return books.removeIf(book -> book.getISBN().equals(ISBN) && book.isAvailable());
    }

    public Book findBookByISBN(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null;
    }

    // Member management methods
    public void registerMember(Member member) {
        members.add(member);
    }

    public boolean removeMember(String id) {
        return members.removeIf(member -> member.getId().equals(id) && member.getBorrowedBooks() == 0);
    }

    public Member findMemberById(String id) {
        for (Member member : members) {
            if (member.getId().equals(id)) {
                return member;
            }
        }
        return null;
    }

    // Loan management methods
    public Loan loanBook(String ISBN, String memberId) {
        Book book = findBookByISBN(ISBN);
        Member member = findMemberById(memberId);

        if (book == null || member == null) {
            return null;
        }

        if (!book.isAvailable()) {
            return null;
        }

        book.setAvailable(false);
        member.incrementBorrowedBooks();

        Loan loan = new Loan(book, member);
        loans.add(loan);

        return loan;
    }

    public boolean returnBook(String ISBN, String memberId) {
        Book book = findBookByISBN(ISBN);
        Member member = findMemberById(memberId);

        if (book == null || member == null) {
            return false;
        }

        Loan loan = findActiveLoan(ISBN, memberId);
        if (loan == null) {
            return false;
        }

        book.setAvailable(true);
        member.decrementBorrowedBooks();
        loan.setReturned(true);
        loan.setReturnDate(LocalDate.now());

        return true;
    }

    private Loan findActiveLoan(String ISBN, String memberId) {
        for (Loan loan : loans) {
            if (loan.getBook().getISBN().equals(ISBN) &&
                    loan.getMember().getId().equals(memberId) &&
                    !loan.isReturned()) {
                return loan;
            }
        }
        return null;
    }

    // Getters for lists
    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    public List<Loan> getLoans() {
        return new ArrayList<>(loans);
    }

    // Additional methods as improvements
    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public List<Loan> getOverdueLoans(int daysLimit) {
        List<Loan> overdueLoans = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Loan loan : loans) {
            if (!loan.isReturned()) {
                LocalDate dueDate = loan.getLoanDate().plusDays(daysLimit);
                if (today.isAfter(dueDate)) {
                    overdueLoans.add(loan);
                }
            }
        }

        return overdueLoans;
    }
}