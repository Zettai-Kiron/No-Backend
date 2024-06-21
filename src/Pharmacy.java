import java.util.ArrayList;
import java.util.List;

public class Pharmacy {
    private List<Medicine> inventory;

    public Pharmacy() {
        inventory = new ArrayList<>();
    }

    public void addMedicine(Medicine medicine) {
        inventory.add(medicine);
    }

    public List<Medicine> getInventory() {
        return inventory;
    }

    public boolean processSale(String medicineName, int quantity) {
        for (Medicine medicine : inventory) {
            if (medicine.getName().equalsIgnoreCase(medicineName) && medicine.getQuantity() >= quantity) {
                medicine.setQuantity(medicine.getQuantity() - quantity);
                return true;
            }
        }
        return false;
    }
}
