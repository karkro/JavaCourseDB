
public class JavaLesson15 {

	public static void main(String[] args) {
		Vehicle car = new Vehicle(4, 100.0);
		
		// Using methods from the interface Drivable
		System.out.println("Car max speed: " + car.getSpeed());
		System.out.println("Cars number of wheels: " + car.getWheels());
		
		// Using methods from abstract method Crashable
		car.setCarStrength(10);
		System.out.println("Car strength: " + car.getCarStrength());
	}
}
