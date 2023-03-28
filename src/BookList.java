
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
public class BookList {
    static ArrayList<AddressBook> books = new ArrayList<AddressBook>();


    void addBook(String name, AddressBook book) {
        book.bookName = name;
        this.books.add(book);
        System.out.println("Book " + name + " added successfully");

    }

    void addInfo(Contact value) {
        Scanner scan = new Scanner(System.in);
        System.out.println("First Name :");
        value.firstName = scan.nextLine();
        System.out.println("Last Name :");
        value.lastName = scan.nextLine();
        System.out.println("Enter the address :");
        value.address = scan.nextLine();
        System.out.println("Enter city : ");
        value.city = scan.nextLine();
        System.out.println("Enter state : ");
        value.state = scan.nextLine();
        System.out.println("Enter Phone Number : ");
        value.phoneNumber = scan.nextLine();
        System.out.println("Enter Email : ");
        value.email = scan.nextLine();
        System.out.println("Enter zip : ");
        value.zip = scan.nextLine();
    }

    void showPersonsByCity(String placeName) {
        int count = 0;
        if(books.size() == 0) {
            System.out.println("Booklist is empty");
            return;
        }
        for (int i = 0; i < books.size(); i++) {
            List<Contact> matchedContact = books.get(i).list.stream().filter(x -> x.city.equals(placeName))
                    .collect(Collectors.toList());
            count += books.get(i).list.stream().filter(x -> x.city.equals(placeName)).count();
            matchedContact.stream().forEach(x -> System.out.println(x.firstName));

        }
        System.out.println("Number of persons are : " + count);
    }

    void showPersonsByState(String placeName) {
        int count = 0;
        if(books.size() == 0) {
            System.out.println("Booklist is empty");
            return;
        }
        for (int i = 0; i < books.size(); i++) {
            List<Contact> matchedContact = books.get(i).list.stream().filter(x -> x.state.equals(placeName))
                    .collect(Collectors.toList());
            count += books.get(i).list.stream().filter(x -> x.state.equals(placeName)).count();
            matchedContact.stream().forEach(x -> System.out.println(x.firstName));
        }
        System.out.println("Number of persons are : " + count);
    }

    void operations(ArrayList<AddressBook> books, int i) {
        Scanner input = new Scanner(System.in);
        int condition1 = 0;/// This is for checking the contact name exist or not
        int condition = 0; /// This is condition for running while loop
        while (condition == 0) {
            System.out.println("Do you want to add/edit/delete contact (0/1/2) :Press 3 to go back to main menu: Press 4 to sort contact");
            int response = input.nextInt();
            switch (response) {
                case 0:
                    Contact contact = new Contact();
                    contact.addContact();
                    boolean duplicateContact = books.get(i).list.stream()
                            .anyMatch(x -> x.firstName.equals(contact.firstName));
                    if (duplicateContact == true) {
                        System.out.println("It is a duplicate contact.");
                        return;
                    } else {
                        books.get(i).list.add(contact);
                        System.out.println("Contact added successfully");
                    }
                    break;
                case 1:
                    if (books.get(i).list.size() == 0) {
                        System.out.println("Addressbook is empty");
                    } else {
                        System.out.println("Enter the first name of person you want to edit :");
                        Scanner scan1 = new Scanner(System.in);
                        String name1 = scan1.nextLine();
                        for (Contact value : books.get(i).list) {
                            if (value.firstName.equals(name1)) {
                                addInfo(value);
                                System.out.println("Contact updated successfully");
                                condition1 = 1;
                                break;
                            }

                            if (condition1 == 0) {
                                System.out.println("Contact doesn't exist with the given name " + name1);
                            }

                        }
                    }
                    break;
                case 2:
                    if (books.get(i).list.size() == 0) {
                        System.out.println("Addressbook is empty");
                    } else {
                        System.out.println("Enter the first name of person you want to delete :");
                        Scanner scan2 = new Scanner(System.in);
                        String name2 = scan2.nextLine();
                        for (Contact value : books.get(i).list) {
                            if (value.firstName.equals(name2)) {
                                books.get(i).list.remove(value);
                                System.out.println("Contact deleted successfully");
                                condition1 = 1;
                                break;
                            }
                        }
                        if (condition1 == 0) {
                            System.out.println("Contact doesn't exist with the given name " + name2);
                        }
                    }
                    break;
                case 3:
                    condition = 1;
                    break;

                case 4:
                    if (books.get(i).list.size() == 0) {
                        System.out.println("Addressbook is empty");
                    }
                    else {
                        Scanner scan = new Scanner(System.in);
                        while (true) {
                            System.out.println(
                                    "Press \n 0 to sort by contact name \n 1 to sort by city \n 2 to sort by state \n 3 to sort by zip");
                            int response1 = scan.nextInt();
                            int a = books.get(i).sort(response1, books.get(i));
                            if(a == 0) {
                                break;
                            }
                        }

                    }
                    break;
                default:
                    System.out.println("Enter valid command");
                    break;
            }
        }
    }

    int checkBook(String name) {
        int result = 0;
        if (this.books.size() == 0) {
            System.out.println("Booklist was empty. " + name + " is created.");

        } else {
            int track = 0;
            for (int i = books.size() - 1; i >= 0; --i) {
                if (books.get(i).bookName.contains(name)) {

                    System.out.println("Book exist please go ahead");
                    operations(books, i);
                    track = 1;
                    result = 1;
                    break;

                }
            }
            if (track == 0) {
                System.out.println("Book doesn't exist with the given name. " + name + " is created");

            }

        }
        return result;
    }

}
