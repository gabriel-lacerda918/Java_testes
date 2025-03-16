// Main.java (final version)
public class Main {
    public static void main(String[] args) {
        System.out.println("Gestão biblioteca");

        // Create a new library
        Library library = new Library();

        // Add books
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "1234567890"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "0987654321"));
        library.addBook(new Book("1984", "George Orwell", "1122334455"));
        library.addBook(new Book("Pride and Prejudice", "Jane Austen", "5566778899"));
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "9988776655"));

        // Register members
        library.registerMember(new Member("John Doe", "M001", "john@example.com"));
        library.registerMember(new Member("Jane Smith", "M002", "jane@example.com"));
        library.registerMember(new Member("Bob Johnson", "M003", "bob@example.com"));

        // Loan books
        System.out.println("\nEmpréstimo de livros...");
        Loan loan1 = library.loanBook("1234567890", "M001");
        if (loan1 != null) System.out.println("livro '" + loan1.getBook().getTitle() + "' emprestado para " + loan1.getMember().getName());

        Loan loan2 = library.loanBook("0987654321", "M002");
        if (loan2 != null) System.out.println("livro '" + loan2.getBook().getTitle() + "' emprestado para " + loan2.getMember().getName());

        Loan loan3 = library.loanBook("1122334455", "M001");
        if (loan3 != null) System.out.println("Livro '" + loan3.getBook().getTitle() + "' emprestado para " + loan3.getMember().getName());

        // Display library status
        System.out.println("\nStatus biblioteca:");
        System.out.println("livros disponiveis: " + library.getAvailableBooks().size());
        System.out.println("Total de livros: " + library.getBooks().size());
        System.out.println("Total de membros: " + library.getMembers().size());
        System.out.println("empréstimos ativos: " + library.getLoans().stream().filter(l -> !l.isReturned()).count());

        // Return a book
        System.out.println("\nretornado livro...");
        boolean returned = library.returnBook("1234567890", "M001");
        if (returned) System.out.println("livro com ISBN 1234567890 retornado com sucesso.");

        // Display updated library status
        System.out.println("\natualização status biblioteca:");
        System.out.println("livros disponiveis: " + library.getAvailableBooks().size());
        System.out.println("Total livros: " + library.getBooks().size());
        System.out.println("Total Membros: " + library.getMembers().size());
        System.out.println("emprestimos ativos: " + library.getLoans().stream().filter(l -> !l.isReturned()).count());

        // Display member information
        Member member1 = library.findMemberById("M001");
        System.out.println("\ninformação membro:");
        System.out.println(member1);
        System.out.println("livros emprestados: " + member1.getBorrowedBooks());

        // Run tests
        System.out.println("\nRunning tests...");
        LibraryTest test = new LibraryTest();
        test.runAllTests();
    }
}