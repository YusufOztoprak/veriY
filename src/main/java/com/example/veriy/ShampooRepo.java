package com.example.veriy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ShampooRepo {
    private ObservableList<Shampoo> shampooList = FXCollections.observableArrayList();

    public void addShampoo(Shampoo shampoo) {
        shampooList.add(shampoo);
    }

    public ObservableList<Shampoo> getShampooList() {
        return shampooList;
    }

    public void removeShampoo(Shampoo shampoo) {
        shampooList.remove(shampoo);
    }
}
