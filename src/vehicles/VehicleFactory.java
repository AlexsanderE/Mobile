package src.vehicles;

public class VehicleFactory {
    public Vehicle createVehicle(String type, String brand, String model, int year) {
        switch (type.toLowerCase()) {
            case "car":
                return new Car(brand, model, year);
            case "truck":
                return new Truck(brand, model, year);
            default:
                throw new IllegalArgumentException("Unknown vehicle type");
        }
    }
} 
