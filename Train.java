import java.util.ArrayList;

public class Train {
    private final Engine engine;
    private ArrayList<Car> cars;

    public Train(FuelType fuelType, double fuelCapacity, int nCars, int passengerCapacity) {
        this.engine = new Engine(fuelType, fuelCapacity);
        this.cars = new ArrayList<>();
        for (int i = 0; i < nCars; i++) {
            Car car = new Car(passengerCapacity);
            cars.add(car);
        }
    }

    public Engine getEngine() {
        return engine;
    }

    public Car getCar(int i) {
        return cars.get(i);
    }

    public int getMaxCapacity() {
        int maxCapacity = 0;
        for (Car car : cars) {
            maxCapacity += car.getCapacity();
        }
        return maxCapacity;
    }

    public int seatsRemaining() {
        int totalSeats = 0;
        for (Car car : cars) {
            totalSeats += car.seatsRemaining();
        }
        return totalSeats;
    }

    public void printManifest() {
        System.out.println("Train manifest:");
        for (int i = 0; i < cars.size(); i++) {
            System.out.println("Car " + (i+1) + ":");
            cars.get(i).printManifest();
        }
    }
    
    public void removeCar(int i, Train train) {
    	cars.remove(i);
    	train = new Train(train.getEngine().getFuelType(),train.getEngine().getCurrentFuelLevel(),cars.size(), train.getMaxCapacity()/cars.size());
    }
    public static void main(String[] args) {
        Train myTrain = new Train(FuelType.ELECTRIC, 200, 3, 50);
    
        Passenger alice = new Passenger("Alice");
        Passenger bob = new Passenger("Bob");
        Passenger charlie = new Passenger("Charlie");
        Passenger dave = new Passenger("Dave");
    
        alice.boardCar(myTrain.getCar(0));
        bob.boardCar(myTrain.getCar(0));
        charlie.boardCar(myTrain.getCar(1));
    
        myTrain.printManifest();
    
        myTrain.removeCar(1,myTrain);
    
        dave.boardCar(myTrain.getCar(0));
    
        myTrain.printManifest();
    }    
}
