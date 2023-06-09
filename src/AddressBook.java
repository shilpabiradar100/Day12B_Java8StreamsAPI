

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {
    ArrayList<Contact> list = new ArrayList<Contact>();//// It represents a single diary where contact has been stored
    String bookName; /// It represent the name of diary

    void addContact() {
        Contact contact = new Contact();
        contact.addContact();
        boolean duplicateContact = list.stream().anyMatch(x -> x.firstName.equals(contact.firstName));
        if (duplicateContact == true) {
            System.out.println("It is a duplicate contact.");
            return;
        } else {
            list.add(contact);
            System.out.println("Contact added successfully");
        }

    }

    void deletePerson(String name, ArrayList<Contact> list) {
        if (list.size() == 0) {
            System.out.println("Address book is empty.Please Add First");
        } else {
            int m = 0;
            for (int i = list.size() - 1; i >= 0; --i) {
                if (list.get(i).firstName.contains(name)) {
                    list.remove(i);
                    System.out.println("Contact deleted successfully");
                    m += 1;
                    break;
                }
            }
            if (m == 0) {
                System.out.println("No contact with the given name exist");
            }
        }

    }

    void editPerson(String name, ArrayList<Contact> list) {
        if (list.size() == 0) {
            System.out.println("Addressbook is empty.Please add First");
        } else {
            int m = 0;
            for (int i = list.size() - 1; i >= 0; --i) {
                if (list.get(i).firstName.contains(name)) {
                    list.get(i).addContact();
                    System.out.println("Contact Updated successfully");
                    m += 1;
                    break;
                }
            }
            if (m == 0) {
                System.out.println("No contact with the given name exist");
            }
        }

    }

    public int sort(int option, AddressBook addressBook) {
        int a = 0;
        switch (option) {
            case 0:
                addressBook.list.sort((Contact x1, Contact x2) -> x1.firstName.compareTo(x2.firstName));
                addressBook.list.forEach((s) -> System.out.println(s));
                break;

            case 1:
                addressBook.list.sort((Contact x1, Contact x2) -> x1.city.compareTo(x2.city));
                addressBook.list.forEach((s) -> System.out.println(s));
                break;
            case 2:
                addressBook.list.sort((Contact x1, Contact x2) -> x1.state.compareTo(x2.state));
                addressBook.list.forEach((s) -> System.out.println(s));
                break;
            case 3:
                addressBook.list.sort((Contact x1, Contact x2) -> x1.zip.compareTo(x2.zip));
                addressBook.list.forEach((s) -> System.out.println(s));
                break;
            default:
                System.out.println("Enter valid option");
                a = 1;
                break;
        }
        return a;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        System.out.println("Welcome to Address Book Program ");

        BookList shelf = new BookList();

        while (true) {
            AddressBook addressBook = new AddressBook();
            Scanner scan3 = new Scanner(System.in);
            System.out.println(
                    "Enter the name of Book you want to  access or add  or type 'city' to search persons by city or type 'state' to search by state or press 'q' to quit");
            String bookName = scan3.nextLine();
            if (bookName.equals("q")) {
                // if (addressBook.list.size() > 0) {
                // book.addBook(bookName, addressBook);
                // }
                System.out.println("The program is closed");
                break;
            } else if (bookName.equals("city")) {
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter the name of city  :");
                String placeName = scan.nextLine();
                shelf.showPersonsByCity(placeName);
                continue;
            } else if (bookName.equals("state")) {
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter the name of state  :");
                String placeName = scan.nextLine();
                shelf.showPersonsByState(placeName);
                continue;
            }
            int result = shelf.checkBook(bookName);//// (It can return 0 or 1)It will return 1 if book exist b and break
            //// down loop
            int condition = 0;///// It will keep check on the addressbook created or not
            while (true) {
                if (result == 1) {
                    break;
                }
                System.out.println(
                        "Do you want to add/edit/delete/  the contacts (0/1/2) :Press 3 to Go back to main menu : Press 4 to sort contact");
                Scanner scan = new Scanner(System.in);
                int input = scan.nextInt();

                if (input == 0) {
                    addressBook.addContact();

                } else if (input == 1) {
                    Scanner scan1 = new Scanner(System.in);
                    System.out.println("Enter the first name of person you to edit ");
                    String name = scan1.nextLine();
                    addressBook.editPerson(name, addressBook.list);

                } else if (input == 2) {
                    Scanner scan2 = new Scanner(System.in);
                    System.out.println("Enter the first name of the person you want to delete : ");
                    String name = scan2.nextLine();
                    addressBook.deletePerson(name, addressBook.list);
                }

                else if (input == 3) {
                    shelf.addBook(bookName, addressBook);
                    break;
                } else if (input == 4) {
                    Scanner scan4 = new Scanner(System.in);
                    while (true) {
                        System.out.println(
                                "Press \n 0 to sort by contact name \n 1 to sort by city \n 2 to sort by state \n 3 to sort by zip");
                        int response = scan4.nextInt();
                        int a = addressBook.sort(response, addressBook);
                        if(a == 0) {
                            break;
                        }
                    }

                }

                else {
                    System.out.println("Enter the valid command");
                }
            }
        }

    }

}
