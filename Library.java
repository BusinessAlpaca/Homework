
public class Library {

	// We tend to like adding the access qualifiers wherever possible. "private String address;"
	String address;
	
	// "openingHours" is more descriptive.
	// I'm not sure "static" makes sense here. Are you implying that this is true for all libraries and can't be changd? If so - omit the variable and have the "printOpeningHours" method just write this string.
	static String hours = "Libraries are open daily from 9am to 5pm.";
	// "collection" is a bad variable name, even though a "library collection" is a thing - in software "collections" are a general term for "things that hols other things". Avoid that word.
	Book[] collection = new Book[4];

	// no point in using two different terms. You can take a parameter called "address" and the assignment line can be "this.address = address;"
	public Library(String place) {
		address = place;
	}

	// "bk" is a bad name, and "book" is just so close. :)
	// Also - your "addBook" method takes a Book, and the "borrowBook" takes a title. You need to decide if the Book object is the way you want people to communicate with your Library object, or is it just an internal "support" object.
	public void addBook(Book bk) {
		for (int i = 0; i < collection.length; i++) {
			if (collection[i] == null) {
				collection[i] = bk;
				// Use "return" if what you're trying to say is "this method has finished it's work"
				break;
			}
		}
		// What if your collection is full and you can't add the book? The method doesn't throw an exception or even returns a boolean value for success...
	}

	// *Ususally* you'd like to avoid objects doing things "they are not responsible for". A library is good at knowing when it is open, and not so good at deciding how to print things. I would try to have a "getOpeningHours" method, that returns a string.
	public static void printOpeningHours() {
		System.out.println(hours);
	}

	public void printAddress() {
		System.out.println(address);
	}

	// I would again try to express the success of the method in the return value. In this case it can be a Book object or null.
	public void borrowBook(String title) {
		boolean inColl = false;
		for (int i = 0; i < collection.length; i++) {
			// Opt for "title == collection[i].getTitle()". And if relevant - check that "title" is not null or empty at the start of the method.
			if (collection[i] != null && collection[i].getTitle().equals(title)) {
				if (collection[i].isBorrowed()) {
					System.out.println("Sorry, this book is already borrowed.");
					// using "return" instead of "break" would allow you to avoid that state management of "inColl". Also - avoid abbriviabtions when not crucial - "titleFoundInCollection" is a better name.
					inColl = true;
					break;
				} else {
					// Try to name methods with action verbs - "markAsRented" is more telling than "rented" which can mean "isMarkedAsRented?".
					collection[i].rented();
					System.out.println("You successfully borrowed " + collection[i].getTitle() + ".");
					inColl = true;
					break;
				}
			}
		}
		if (!inColl)
			System.out.println("Sorry, this book is not in our catalog.");
	}

	public void printAvailableBooks() {
		// If you're keeping to the semantics of "the books collection doesn't have any gaps" - you can use break some loops when you first encounter a null. (ln 39)
		if (collection[0] == null)
			System.out.println("No book in catalog.");
		// Opt for "return" when you're done with this method, over using "else".
		else {
			for (int i = 0; i < collection.length; i++) {
				if (collection[i] != null && !collection[i].isBorrowed()) {
					System.out.println(collection[i].getTitle());
				}
			}
		}
	}

	public void returnBook(String title) {
		boolean inColl = false;
		for (int i = 0; i < collection.length; i++) {
			if (collection[i] != null && collection[i].getTitle().equals(title)) {
				collection[i].returned();
				System.out.println("You successfully returned " + collection[i].getTitle() + ".");
				inColl = true;
				break;
			}
		}
		if (!inColl)
			System.out.println("Sorry, this book is not in our catalog.");
	}
	
	// Let's discuss testing. I think you're ready. :)

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
