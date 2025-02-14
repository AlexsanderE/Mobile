package src.search;

import src.vehicles.Vehicle;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.io.Serializable;

public class VehicleSpecification<T> implements SearchCriteria, Serializable {
    private static final long serialVersionUID = 1L;
    private transient final Function<Vehicle, T> getter;
    private final T value;
    private transient final BiFunction<T, T, Boolean> comparator;
    
    public VehicleSpecification(Function<Vehicle, T> getter, T value, BiFunction<T, T, Boolean> comparator) {
        this.getter = getter;
        this.value = value;
        this.comparator = comparator;
    }
    
    @Override
    public boolean matches(Vehicle vehicle) {
        T actualValue = getter.apply(vehicle);
        return comparator.apply(actualValue, value);
    }
} 