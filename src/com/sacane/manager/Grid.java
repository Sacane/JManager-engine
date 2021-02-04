package com.sacane.manager;
import java.util.*;
import java.util.stream.Collectors;


public class Grid {

    private final HashMap<DateMY, Leaf> management = new HashMap<>();

    public void addLeaf(DateMY date, Leaf leaf){
        Objects.requireNonNull(leaf);
        Objects.requireNonNull(date);
        management.put(date, leaf);
    }

    public double incomeSum(){
        return management.values().stream().mapToDouble(Leaf::getAmount).sum();
    }

    public Set<Leaf> borderIncome(double gapDown, double gapUp){
        return management.values().stream().filter(leaf ->
                leaf.getAmount() > gapDown && leaf.getAmount() < gapUp
        ).collect(Collectors.toSet());
    }

    public Set<Leaf> borderIncome(double gapDown){
        return management.values().stream().filter(leaf ->
            leaf.getAmount() > gapDown
        ).collect(Collectors.toSet());
    }

}
