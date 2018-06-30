
public class Library {

	String address;
	static String hours = "Libraries are open daily from 9am to 5pm.";
	Book[] collection = new Book[4];
	{
		for (int i = 0; i < collection.length; i++) {
			collection[i] = new Book("");
		}
	}

	public Library(String place) {
		address = place;
	}

	public void addBook(Book bk) {
		for (int i = 0; i < collection.length; i++) {
			if (collection[i].getTitle().equals("")) {
				collection[i] = bk;
				break;
			}
		}
	}

	public static void printOpeningHours() {
		System.out.println(hours);
	}

	public void printAddress() {
		System.out.println(address);
	}

	public void borrowBook(String title) {
		boolean available = false;
		for (int i = 0; i < collection.length; i++) {
			if (collection[i].getTitle().equals(title)) {
				available = true;
				if (collection[i].isBorrowed()) {
					System.out.println("Sorry, this book is already borrowed.");
					break;
				} else {
					collection[i].rented();
					System.out.println("You successfully borrowed " + collection[i].getTitle() + ".");
					break;
				}
			}
		}
		if (!available) System.out.println("Sorry, this book is not in our catalog.");
	}

	public void printAvailableBooks() {
		boolean available = false;
		for (int i = 0; i < collection.length; i++) {
			if (!collection[i].isBorrowed() && !collection[i].getTitle().equals("")) {
				available = true;
				System.out.println(collection[i].getTitle());
			}
		}
		if (!available) System.out.println("No book in catalog");
	}

	public void returnBook(String title) {
		boolean available = false;
		for (int i = 0; i < collection.length; i++) {
			if (collection[i].getTitle().equals(title)) {
				collection[i].returned();
				System.out.println("You successfully returned " + collection[i].getTitle() + ".");
				available = true;
				break;
			}
		}
		if (!available) System.out.println("No book in catalog");

	}

	public static void main(String[] args) {
		// Create two libraries
		Library firstLibrary = new Library("10 Main St.");
		Library secondLibrary = new Library("228 Liberty St.");

		// Add four books to the first library
		firstLibrary.addBook(new Book("The Da Vinci Code"));
		firstLibrary.addBook(new Book("Le Petit Prince"));
		firstLibrary.addBook(new Book("A Tale of Two Cities"));
		firstLibrary.addBook(new Book("The Lord of the Rings"));

		// Print opening hours and the addresses
		System.out.println("Library hours:");
		printOpeningHours();
		System.out.println();

		System.out.println("Library addresses:");
		firstLibrary.printAddress();
		secondLibrary.printAddress();
		System.out.println();

		// Try to borrow The Lords of the Rings from both libraries
		System.out.println("Borrowing The Lord of the Rings:");
		firstLibrary.borrowBook("The Lord of the Rings");
		firstLibrary.borrowBook("The Lord of the Rings");
		secondLibrary.borrowBook("The Lord of the Rings");
		System.out.println();

		// Print the titles of all available books from both libraries
		System.out.println("Books available in the first library:");
		firstLibrary.printAvailableBooks();
		System.out.println();
		System.out.println("Books available in the second library:");
		secondLibrary.printAvailableBooks();
		System.out.println();

		// Return The Lords of the Rings to the first library
		System.out.println("Returning The Lord of the Rings:");
		firstLibrary.returnBook("The Lord of the Rings");
		System.out.println();

		// Print the titles of available from the first library
		System.out.println("Books available in the first library:");
		firstLibrary.printAvailableBooks();
	}
}
