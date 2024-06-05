
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Pembeli extends User {
    private static Map<String, Integer> cart;

    public Pembeli(String username, String password) {
        super(username, password, "Pembeli");

        this.cart = new HashMap<>();
    }

    public void addToCart(String itemName, int quantity) {
        cart.put(itemName, quantity);
    }

    public static Map<String, Integer> getCart() {
        return cart;
    }

    public static double calculateTotalPayment(Kantin kantin) {
        double totalPayment = 0;

        for (Map.Entry<String, Integer> entry : getCart().entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            double itemPrice = kantin.getPrices().get(itemName);

            totalPayment += itemPrice * quantity;
        }

        return totalPayment;
    }

    public static void checkout() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Keranjang Pesanan Anda:");
        for (Map.Entry<String, Integer> entry : getCart().entrySet()) {
            System.out.println(entry.getKey() + " x" + entry.getValue());
        }

        Kantin kantin = new Kantin("Kantin A");
        new Kantin("Kantin B");
        new Kantin("Kantin C");

        double totalPayment = calculateTotalPayment(kantin);

        System.out.println("\nTotal Pembayaran: Rp" + totalPayment);

        System.out.println("\nPilihan pengambilan pesanan:");
        System.out.println("1. Ambil Sendiri");
        System.out.println("2. Antar");

        int deliveryChoice = scanner.nextInt();
        scanner.nextLine();

        if (deliveryChoice == 1) {
            System.out.println("Pesanan Anda dapat diambil sendiri. Terima kasih!");
        } else if (deliveryChoice == 2) {
            System.out.println("Pesanan Anda akan diantarkan. Terima kasih!");
        } else {
            System.out.println("Pilihan pengambilan tidak valid.");
        }

        System.out.println("\nPilihan pembayaran:");
        System.out.println("1. Bayar Sekarang");
        System.out.println("2. Bayar Nanti");

        int paymentChoice = scanner.nextInt();
        scanner.nextLine();

        if (paymentChoice == 1) {
            System.out.println("Pembayaran sebesar Rp" + totalPayment + " berhasil diterima. Terima kasih!");
        } else if (paymentChoice == 2) {
            System.out.println("Silakan bayar segera saat pesanan diantarkan. Terima kasih!");
        } else {
            System.out.println("Pilihan pembayaran tidak valid.");
        }
    }
}
