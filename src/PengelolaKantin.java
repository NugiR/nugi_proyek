import java.util.Scanner;

public class PengelolaKantin extends User {
    private Kantin kantin;

    public PengelolaKantin(String username, String password) {
        super(username, password, "PengelolaKantin");
        this.kantin = kantin;
    }

    public Kantin getKantin() {
        return kantin;
    }

    public void checkPemasukan() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Pilih opsi:");
        System.out.println("1. Check Total Pemasukan");
        System.out.println("2. Check Pemasukan per Item");
        int option = scanner.nextInt();
        scanner.nextLine(); // Membersihkan newline

        Kantin kantin = getKantin();

        if (option == 1) {
            double totalPemasukan = Pembeli.calculateTotalPayment(kantin);
            System.out.println("Total Pemasukan: Rp" + totalPemasukan);
        } else if (option == 2) {
            System.out.println("Masukkan nama item untuk check pemasukan:");
            String itemName = scanner.nextLine();
            double pemasukanPerItem = Pembeli.calculateTotalPayment(kantin);
            System.out.println("Pemasukan untuk item " + itemName + ": Rp" + pemasukanPerItem);
        } else {
            System.out.println("Opsi tidak valid.");
        }
    }
}
