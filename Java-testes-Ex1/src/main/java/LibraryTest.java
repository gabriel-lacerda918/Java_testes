// LibraryTest.java
public class LibraryTest {
    private Library library;
    private Book book1, book2;
    private Member member1, member2;

    // Setup method to initialize test data
    private void setup() {
        library = new Library();

        // Create test books
        book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "1234567890");
        book2 = new Book("To Kill a Mockingbird", "Harper Lee", "0987654321");

        // Create test members
        member1 = new Member("Gabriel1", "M001", "gabriel1@fiap.com");
        member2 = new Member("Gabriel2", "M002", "gabriel2@fiap.com");

        // Add books and members to the library
        library.addBook(book1);
        library.addBook(book2);
        library.registerMember(member1);
        library.registerMember(member2);
    }

    // Test method for adding a book
    private void testAddBook() {
        setup();
        int initialSize = library.getBooks().size();
        Book newBook = new Book("1984", "George Orwell", "1122334455");
        library.addBook(newBook);

        assert library.getBooks().size() == initialSize + 1 : "Livro adicionado incorretamente";
        assert library.findBookByISBN("1122334455") != null : "Livro nao encontrado via ISBN";

        System.out.println("Teste 'adicionar livro' aprovado!");
    }

    // Test method for removing a book
    private void testRemoveBook() {
        setup();
        int initialSize = library.getBooks().size();
        boolean result = library.removeBook("1234567890");

        assert result : "A remoção do livro deve retornar verdadeiro";
        assert library.getBooks().size() == initialSize - 1 : "Livro removido incorretamente";
        assert library.findBookByISBN("1234567890") == null : "O livro não deve ser encontrado após removido";

        System.out.println("Teste 'remover livro' aprovado!");
    }

    // Test method for registering a member
    private void testRegisterMember() {
        setup();
        int initialSize = library.getMembers().size();
        Member newMember = new Member("Carol", "M003", "carol@fiap.com");
        library.registerMember(newMember);

        assert library.getMembers().size() == initialSize + 1 : "Membro adicionado incorretamente";
        assert library.findMemberById("M003") != null : "Membro não encontrado pelo ID";

        System.out.println("Teste 'registro de membro' aprovado!");
    }

    // Test method for loaning a book
    private void testLoanBook() {
        setup();
        Loan loan = library.loanBook("1234567890", "M001");

        assert loan != null : "O empréstimo não pode ser nulo";
        assert !book1.isAvailable() : "O livro não deve estar disponível após ser emprestado";
        assert member1.getBorrowedBooks() == 1 : "O membro deve ter 1 livro emprestado";

        // Try to loan the same book again
        Loan secondLoan = library.loanBook("1234567890", "M002");
        assert secondLoan == null : "nao é possivel emprestar livro já emprestado";

        System.out.println("teste ' emprestimo livro' aprovado!");
    }

    // Test method for returning a book
    private void testReturnBook() {
        setup();
        library.loanBook("1234567890", "M001");
        boolean result = library.returnBook("1234567890", "M001");

        assert result : "A devolução do livro deve ser bem-sucedida";
        assert book1.isAvailable() : "Livro disponivel apos retornar";
        assert member1.getBorrowedBooks() == 0 : "O membro deve ter 0 livros emprestados após a devolução";

        System.out.println("teste ' retorno livro1 aprovado!");
    }

    // Run all tests
    public void runAllTests() {
        System.out.println("Executando testes do Sistema de Gestão de Bibliotecas...");
        testAddBook();
        testRemoveBook();
        testRegisterMember();
        testLoanBook();
        testReturnBook();
        System.out.println("todos os testes aprovados!");
    }

    public static void main(String[] args) {
        LibraryTest test = new LibraryTest();
        test.runAllTests();
    }
}