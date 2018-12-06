package com.example.mstark.destiny;

import com.google.gson.annotations.SerializedName;

public class Weapon {
    @SerializedName("data")
    private String weapon;

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
}
