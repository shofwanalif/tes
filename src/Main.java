import java.util.Scanner;
public class Main {
    String Book, User, Student;
    Book[] bookList = new Book[100];
    int bookCount = 0;
    User[] userStudent = new User[100];
    int studentCount = 0;
    Admin admin = new Admin();
    Student student;

    Main() {
        bookList[bookCount++] = new Book("1", "Black House ", "Patric Kellan", 2);
        bookList[bookCount++] = new Book("2", "Solo Leveling", "Chu-Gong", 10);
        bookList[bookCount++] = new Book("3", "Stephen King", "Ican", 20);

    }

    void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== Library System =====");
            System.out.println("1. Login Admin");
            System.out.println("2. Login Student");
            System.out.println("3. Keluar");
            System.out.print("Pilih opsi (1-3) : ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    if (loginAdmin()) {
                        menuAdmin();
                    } else {
                        System.out.println("Username atau password salah.");
                    }
                    break;
                case 2:
                    if (inputNim()) {
                        menuStudent();
                    } else {
                        System.out.println("NIM tidak ditemukan.");
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("maaf, inputan tidak valid");
                    System.out.println();
            }
        }
    }

    boolean loginAdmin() {
        Scanner scanner = new Scanner(System.in);
        String username, password;
        do {
            System.out.print("Masukkan username: ");
            username = scanner.nextLine();
            System.out.print("Masukkan password: ");
            password = scanner.nextLine();
            if (!admin.adminUsername.equals(username) || !admin.adminPassword.equals(password)) {
                System.out.println("Username atau password salah. Silakan coba lagi.");
            }
        } while (!admin.adminUsername.equals(username) || !admin.adminPassword.equals(password));
        return true;
    }

    boolean inputNim() {
        Scanner scanner = new Scanner(System.in);
        String nim;
        do {
            System.out.print("Masukkan NIM: ");
            nim = scanner.nextLine();
            if (nim.length() != 15) {
                System.out.println("NIM tidak valid.");
            }
        } while (nim.length() != 15);
        return checkNim(nim);
    }

    boolean checkNim(String nim) {
        for (int i = 0; i < studentCount; i++) {
            if (userStudent[i].nim.equals(nim)) {
                student = new Student(userStudent[i].nama, userStudent[i].fakultas, userStudent[i].programStudi);
                return true;
            }
        }
        return false;
    }

    void menuAdmin() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("===== Admin Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Registered Student");
            System.out.println("3. back");
            System.out.print("Choose option (1-3) : ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    admin.addStudent();
                    userStudent = admin.studentList;
                    studentCount = admin.studentCount;
                    break;
                case 2:
                    admin.displayStudents();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("inputan tidak valid");
            }
        }
    }

    void menuStudent() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("===== Student Menu =====");
            System.out.println("1. Tampilkan Buku");
            System.out.println("2. Pinjam buku");
            System.out.println("3. Tampilkan buku yang dipinjam");
            System.out.println("4. Logout");
            System.out.print("Pilih opsi (1-4) : ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    student.displayBooks(bookList, bookCount);
                    break;
                case 2:
                    student.borrowBook(bookList, bookCount);
                    break;
                case 3:
                    student.displayBorrowedBooks();
                    break;
                case 4:
                    student.logout();
                    return;
                default:
                    System.out.println("Inputan tidak valid");
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }
}
class Book {
    String id;
    String judul;
    String penulis;
    int jumlah;

    public Book (String id, String judul, String penulis, int jumlah){
        this.id = id;
        this.judul = judul;
        this.penulis = penulis;
        this.jumlah = jumlah;
    }
}
class User {
    String nim;
    String nama;
    String fakultas;
    String programStudi;

    public User(String nim, String nama, String fakultas, String programStudi){
        this.nim = nim;
        this.nama = nama;
        this.fakultas = fakultas;
        this.programStudi = programStudi;
    }
}