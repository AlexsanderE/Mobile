package vehicles;

public record Car(
        String brand,
        String model,
        Integer year,
        boolean isManual
) {}