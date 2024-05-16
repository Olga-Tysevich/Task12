package com.example.tables.utils;

import lombok.experimental.UtilityClass;

import java.util.function.Consumer;
import java.util.function.Supplier;

@UtilityClass
public class ExceptionManager {

    public static <T> T execute(Supplier<T> method) {
        try {
            return method.get();
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> boolean execute(Consumer<T> method, T object) {
        try {
            method.accept(object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
