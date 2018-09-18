package com.example.jos.shopcart;

public class User {
    public String texto;

    public User(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "User{" +
                "texto='" + texto + '\'' +
                '}';
    }
}
