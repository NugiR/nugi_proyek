import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static Object selectedKantinName;

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);


        User user;
        Kantin kantin = null; // Menyimpan objek kantin untuk pengelola atau pembeli

        // Admin credentials
        String usernameAdmin = "admin";
        String passwordAdmin = "admin123";
        Admin admin = new Admin(usernameAdmin, passwordAdmin);

        // Login Admin
        System.out.println("\nLogin Admin:");
        System.out.println("Masukkan username:");
        String usernameLoginAdmin = scanner.nextLine();
        System.out.println("Masukkan password:");
        String passwordLoginAdmin = scanner.nextLine();

        if (admin.getUsername().equals(usernameLoginAdmin) && admin.getPassword().equals(passwordLoginAdmin)) {
            System.out.println("Login Admin berhasil!");
        } else {
            System.out.println("Login Admin gagal. Username atau password salah.");
            return;
        }

        // Pilihan registrasi untuk Pengelola Kantin atau Pembeli
        System.out.println("\nPilihan registrasi:");
        System.out.println("1. Pengelola Kantin");
        System.out.println("2. Pembeli");

        int registrationChoice = scanner.nextInt();
        scanner.nextLine(); // Membersihkan newline

        if (registrationChoice == 1) {
            System.out.println("Masukkan username untuk Pengelola Kantin:");
            String managerUsername = scanner.nextLine();
            System.out.println("Masukkan password untuk Pengelola Kantin:");
            String managerPassword = scanner.nextLine();

            user = new PengelolaKantin(managerUsername, managerPassword);
            user.createTabelPengguna();
            user.insertPengguna();
            user.checkUsername();

            System.out.println("Pilih Kantin (A, B, atau C):");
            String kantinChoice = scanner.nextLine();

            if (kantinChoice.equalsIgnoreCase("A")) {
                kantin = new Kantin("Kantin A");
            } else if (kantinChoice.equalsIgnoreCase("B")) {
                kantin = new Kantin("Kantin B");
            } else if (kantinChoice.equalsIgnoreCase("C")) {
                kantin = new Kantin("Kantin C");
            } else {
                System.out.println("Pilihan Kantin tidak valid.");
                return;
            }

            PengelolaKantin manager = new PengelolaKantin(managerUsername, managerPassword);
            user = manager;
            


        } else if (registrationChoice == 2) {
            System.out.println("Masukkan username untuk Pembeli:");
            String buyerUsername = scanner.nextLine();
            System.out.println("Masukkan password untuk Pembeli:");
            String buyerPassword = scanner.nextLine();

            user = new Pembeli(buyerUsername, buyerPassword);
            user.createTabelPengguna();
            user.insertPengguna();
            user.checkUsername();

            
        } else {
            System.out.println("Pilihan registrasi tidak valid.");
            return;
        }

        System.out.println("Registrasi berhasil!");

        // Login
        System.out.println("\nLogin:");
        System.out.println("Masukkan username:");
        String loginUsername = scanner.nextLine();
        System.out.println("Masukkan password:");
        String loginPassword = scanner.nextLine();

        if (user.getUsername().equals(loginUsername) && user.getPassword().equals(loginPassword)) {
            System.out.println("Login berhasil!");

            // Tampilkan menu Kantin jika pengguna adalah Pembeli atau Pengelola Kantin
            if (user instanceof Pembeli) {
                Pembeli buyer = (Pembeli) user;
                showBuyerMenu(buyer, kantin);
                

                // Checkout setelah pembeli selesai memilih pesanan
                Pembeli.checkout();
            } else if (user instanceof PengelolaKantin) {
                PengelolaKantin manager = (PengelolaKantin) user;
                showManagerMenu(manager, kantin);
                
            }
        } else {
            System.out.println("Login gagal. Username atau password salah.");
        }
    }

    private static void showBuyerMenu(Pembeli buyer, Kantin kantin) {
        Scanner scanner = new Scanner(System.in);

        
        while (true) {
            System.out.println("\nPilihan menu:");
            System.out.println("1. Lihat Menu Kantin");
            System.out.println("2. Pesan Makanan");
            System.out.println("3. Keluar");

            int buyerChoice = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline

            if (buyerChoice == 1) {
                System.out.println("\nPilihan Kantin:");
                System.out.println("A. Kantin A");
                System.out.println("B. Kantin B");
                System.out.println("C. Kantin C");

                String kantinChoice = scanner.nextLine();
                Kantin selectedKantin = null;

                if (kantinChoice.equalsIgnoreCase("A")) {
                    selectedKantin = new Kantin("Kantin A");
                } else if (kantinChoice.equalsIgnoreCase("B")) {
                    selectedKantin = new Kantin("Kantin B");
                } else if (kantinChoice.equalsIgnoreCase("C")) {
                    selectedKantin = new Kantin("Kantin C");
                } else {
                    System.out.println("Pilihan Kantin tidak valid.");
                    break;
                }

                String selectedKantinName = selectedKantin.getNamaKantin();
                selectedKantin.displayMenu();


            } else if (buyerChoice == 2) {
                System.out.println("\nPilihan Kantin:");
                System.out.println("A. Kantin A");
                System.out.println("B. Kantin B");
                System.out.println("C. Kantin C");

                String kantinChoice = scanner.nextLine();
                Kantin selectedKantin = null;

                if (kantinChoice.equalsIgnoreCase("A")) {
                    selectedKantin = new Kantin("Kantin A");
                } else if (kantinChoice.equalsIgnoreCase("B")) {
                    selectedKantin = new Kantin("Kantin B");
                } else if (kantinChoice.equalsIgnoreCase("C")) {
                    selectedKantin = new Kantin("Kantin C");
                } else {
                    System.out.println("Pilihan Kantin tidak valid.");
                    break;
                }

                selectedKantin.displayMenu();
                createTabelKanti();
                insertKantin(selectedKantinName);

                System.out.println("\nMasukkan nama makanan yang ingin dipesan:");
                String itemName = scanner.nextLine();

                // Validasi apakah item tersebut ada di menu
                if (selectedKantin.getPrices().containsKey(itemName)) {
                    System.out.println("Masukkan jumlah " + itemName + " yang ingin dipesan:");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan newline

                    // Validasi apakah stok cukup
                    if (quantity <= selectedKantin.getStock(itemName)) {
                        buyer.addToCart(itemName, quantity);
                        selectedKantin.reduceStock(itemName, quantity);
                        System.out.println("Berhasil menambahkan " + itemName + " sebanyak " + quantity + " ke keranjang.");
                    } else {
                        System.out.println("Stok " + itemName + " tidak mencukupi.");
                    }
                } else {
                    System.out.println("Menu " + itemName + " tidak ditemukan.");
                }
                
                Pembeli.checkout();
            } else if (buyerChoice == 3) {
                break;
            }
        }
    }

    private static void insertKantin(Object selectedKantinName2) {
    }

    private static void createTabelKanti() {
    }

    private static void showManagerMenu(PengelolaKantin manager, Kantin kantin) {
        Scanner scanner = new Scanner(System.in);
    
        while (true) {
            System.out.println("\nPilihan menu:");
            System.out.println("1. Lihat Menu Kantin");
            System.out.println("2. Tambah Item ke Menu");
            System.out.println("3. Check Pemasukan");
            System.out.println("4. Keluar");
    
            int managerChoice = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline
    
            if (managerChoice == 1) {
                kantin.displayMenu();
            } else if (managerChoice == 2) {
                System.out.println("Masukkan nama item yang ingin ditambahkan ke menu:");
                String itemName = scanner.nextLine();
                System.out.println("Masukkan jumlah stok untuk item tersebut:");
                int stock = scanner.nextInt();
    
                kantin.addItemToMenu(itemName, stock);
                kantin.addStock(itemName, stock);
                System.out.println("Item berhasil ditambahkan ke menu!");
            } else if (managerChoice == 3) {
                manager.checkPemasukan();
            } else if (managerChoice == 4) {
                break;
            }
        }
    }
    
}
