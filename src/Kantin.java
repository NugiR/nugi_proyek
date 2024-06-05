import java.beans.Statement;
import java.util.HashMap;
import java.util.Map;

public class Kantin {
    private String name;
    private Map<String, Integer> menu;
    private Map<String, Double> prices;
    private Map<String, Integer> stock;
    Statement statement;

    public Kantin(String name) {
        this.name = name;
        this.menu = new HashMap<>();
        this.prices = new HashMap<>();
        this.stock = new HashMap<>();
        initializeMenu();
    }

    private void initializeMenu() {
        if (name.equalsIgnoreCase("Kantin A")) {
            menu.put("Mie Goreng", 5);
            menu.put("Nasi Goreng", 5);
            menu.put("Bakso", 5);
            menu.put("Mie Pangsit", 5);
            menu.put("Ayam Geprek", 5);

            prices.put("Mie Goreng", 10000.0);
            prices.put("Nasi Goreng", 12000.0);
            prices.put("Bakso", 10000.0);
            prices.put("Mie Pangsit", 10000.0);
            prices.put("Ayam Geprek", 15000.0);

            stock.put("Mie Goreng", 5);
            stock.put("Nasi Goreng", 5);
            stock.put("Bakso", 5);
            stock.put("Mie Pangsit", 5);
            stock.put("Ayam Geprek", 5);
        } else if (name.equalsIgnoreCase("Kantin B")) {
            menu.put("Jasuke", 5);
            menu.put("Corndog", 5);
            menu.put("Roti Bakar", 5);
            menu.put("Burger", 5);
            menu.put("Tela-Tela", 5);

            prices.put("Jasuke", 8000.0);
            prices.put("Corndog", 6000.0);
            prices.put("Roti Bakar", 10000.0);
            prices.put("Burger", 10000.0);
            prices.put("Tela-Tela", 5000.0);

            stock.put("Jasuke", 5);
            stock.put("Corndog", 5);
            stock.put("Roti Bakar", 5);
            stock.put("Burger", 5);
            stock.put("Tela-Tela", 5);
        } else if (name.equalsIgnoreCase("Kantin C")) {
            menu.put("Pop Ice", 5);
            menu.put("Good Day", 5);
            menu.put("Greentea", 5);
            menu.put("Red Velvet", 5);
            menu.put("Air Mineral", 5);

            prices.put("Pop Ice", 5000.0);
            prices.put("Good Day", 5000.0);
            prices.put("Greentea", 7000.0);
            prices.put("Red Velvet", 10000.0);
            prices.put("Air Mineral", 5000.0);

            stock.put("Pop Ice", 5);
            stock.put("Good Day", 5);
            stock.put("Greentea", 5);
            stock.put("Red Velvet", 5);
            stock.put("Air Mineral", 5 );
        }
    }

    public Map<String, Double> getPrices() {
        return prices;
    }

    public void displayMenu() {
        System.out.println("\nMenu Kantin " + name + ":");
        for (Map.Entry<String, Integer> entry : menu.entrySet()) {
            System.out.println(
                    entry.getKey() + " (Stok: " + entry.getValue() + ", Harga: Rp" + prices.get(entry.getKey()) + ")");
        }
    }

    public int getStock(String itemName) {
        return stock.getOrDefault(itemName, 0);
    }

    public void reduceStock(String itemName, int quantity) {
        int currentStock = stock.getOrDefault(itemName, 0);
        if (currentStock >= quantity) {
            stock.put(itemName, currentStock - quantity);
        } else {
            System.out.println("Makanan tidak tersedia. Apakah ingin pesan menu lain?");
        }
    }

    public void addStock(String itemName, int quantity) {
        int currentStock = stock.getOrDefault(itemName, 0);
        stock.put(itemName, currentStock + quantity);
    }

    public void addItemToMenu(String itemName, int initialStock) {
        if (!menu.containsKey(itemName)) {
            menu.put(itemName, initialStock);
            prices.put(itemName, 0.0);
            stock.put(itemName, initialStock);
        } else {
            System.out.println("Item sudah ada dalam menu.");
        }
    }

    public void checkout() {
    }

    public void createTabelKantin() {
    }

    public String getNamaKantin() {
        return null;
    }

    
}
