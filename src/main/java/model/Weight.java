package model;

/**
 * Used to store weights on graph edges.
 */
public class Weight {
    private double cost;

    public Weight(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("%f", cost);
    }
}
