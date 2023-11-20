package model;

import java.util.Objects;

/**
 * Used to store weights on graph edges.
 */
public class Weight {
  private double cost;
  private String id;

  public Weight(String id, double cost) {
    this.id = id;
    this.cost = cost;
  }

  public double getCost() {
    return cost;
  }

  public String getId() {
    return id;
  }

  public double getCost(TypeOfCost type) {
    return type==TypeOfCost.LEAST_COST_PATH ? cost : 1.0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Weight weight = (Weight) o;
    return getId().equals(weight.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }

  @Override
  public String toString() {
    return String.format("[%s]-%f", id, cost);
  }
}
