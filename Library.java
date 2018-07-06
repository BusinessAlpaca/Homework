
public class Library {

	private String address;
	
	// I'm not sure "static" makes sense here. Are you implying that this is true for all libraries and can't be changd? If so - omit the variable and have the "printOpeningHours" method just write this string.
	//Reply: You're right that it doesn't make sense! It's only this way because the file is a homework excercise, and the homework instructions specify not to write extra methods (the one's I've written just fill in a pre-circulated template) and to use a static variable "where needed"...
	static String openingHours = "Libraries are open daily from 9am to 5pm.";
	
	Book[] holdings = new Book[4];

	public Library(String address) {
		this.address = address;
	}

	//Also - your "addBook" method takes a Book, and the "borrowBook" takes a title. You need to decide if the Book object is the way you want people to communicate with your Library object, or is it just an internal "support" object.
	//Reply: I know. The reason its written this way is that the homework instructions are to write methods that work for the intructor-written tests that you see inside the main method. Thus want a book argument for method, and a string argument for the other...
	public void addBook(Book book) {
		if (holdings[holdings.length - 1] != null) System.out.println("Library holdings are full. Book was not added.");
		for (int i = 0; i < holdings.length; i++) {
			if (holdings[i] == null) {
				holdings[i] = book;
				return;
			}			
		}
	}

	// *Ususally* you'd like to avoid objects doing things "they are not responsible for". A library is good at knowing when it is open, and not so good at deciding how to print things. I would try to have a "getOpeningHours" method, that returns a string.
	//Reply: Agreed. I explained why I wrote it this way above.
	public static void printOpeningHours() {
		System.out.println(openingHours);
	}

	public void printAddress() {
		System.out.println(address);
	}

	// I would again try to express the success of the method in the return value. In this case it can be a Book object or null.
	// Reply: Would it be equally good if the method just printed out a string saying that it succeeded? (This method is a void method for aforementioned reasons.)
	public void borrowBook(String title) {		
		for (int i = 0; i < holdings.length; i++) {
			// Opt for "title == collection[i].getTitle()". And if relevant - check that "title" is not null or empty at the start of the method.
			// Reply: Changed to "==". I would not have even known the .equals method exists unless the MIT instructions explicitly said to use the .equals method here! Will do in future!
			if (holdings[i] == null) break;
			if (holdings[i].getTitle() == title) {
				if (holdings[i].isBorrowed()) {
					System.out.println("Sorry, this book is already borrowed.");
					return;
				} else {
					// Try to name methods with action verbs - "markAsRented" is more telling than "rented" which can mean "isMarkedAsRented?".
					// Reply: Will do! :)
					holdings[i].rented();
					System.out.println("You successfully borrowed " + holdings[i].getTitle() + ".");
					return;
				}
			}
		}
		System.out.println("Sorry, this book is not in our catalog.");
	}

	public void printAvailableBooks() {			
		if (holdings[0] == null) {
			System.out.println("No book in catalog.");
			return;
		}
		for (int i = 0; i < holdings.length; i++) {
			if (holdings[i] != null && !holdings[i].isBorrowed()) {
					System.out.println(holdings[i].getTitle());
			}		
		}
	}

	public void returnBook(String title) {		
		for (int i = 0; i < holdings.length; i++) {
			if (holdings[i] != null && holdings[i].getTitle().equals(title)) {
				holdings[i].returned();
				System.out.println("You successfully returned " + holdings[i].getTitle() + ".");
				return;
			}
		}		
		System.out.println("Sorry, this book is not in our catalog.");
	}
	
	// Let's discuss testing. I think you're ready. :)
	// Cool!

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
