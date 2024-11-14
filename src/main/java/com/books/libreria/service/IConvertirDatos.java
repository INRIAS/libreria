package com.books.libreria.service;

public interface IConvertirDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
