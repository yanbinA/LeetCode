package ParkingSystem;

/**
 * @author Depp
 */
public class ParkingSystem {
    private int[] parking;
    public ParkingSystem(int big, int medium, int small) {
        this.parking = new int[]{big, medium, small};
    }

    public boolean addCar(int carType) {
        return parking[carType - 1]-- > 0;
    }
}
