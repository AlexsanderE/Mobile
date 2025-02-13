package src.vehicles;

class Car extends Vehicle {
    private String bodyType;
    private String fuelType;
    
    public Car(String brand, String model, int year) {
        super(brand, model, year);
    }
}