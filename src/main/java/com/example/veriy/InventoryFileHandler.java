public class InventoryFileHandler {

    private static final String BASE_DIRECTORY = "files/";

    public InventoryFileHandler() {
        File directory = new File(BASE_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public List<Product> readFromFile(String fileName) {
        List<Product> products = new ArrayList<>();
        String fullPath = BASE_DIRECTORY + fileName;

        try (BufferedReader reader = new BufferedReader(new FileReader(fullPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    int quantity = Integer.parseInt(parts[3]);
                    products.add(new Product(id, name, price, quantity));
                }
            }
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }
        return products;
    }

    public void writeToFile(String fileName, List<Product> products) {
        String fullPath = BASE_DIRECTORY + fileName;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullPath))) {
            for (Product product : products) {
                writer.write(String.format("%d,%s,%.2f,%d",
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getQuantity()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Dosya yazma hatası: " + e.getMessage());
        }
    }
}