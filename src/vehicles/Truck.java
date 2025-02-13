package src.vehicles;

public class Truck extends Vehicle {
    private double cargoCapacity;
    private String truckType;
    
    public Truck(String brand, String model, int year) {
        super(brand, model, year);
    }
}
