package com.books.libreria.principal;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.books.libreria.model.DataLibros;
import com.books.libreria.model.Datos;
import com.books.libreria.service.ConsumoApi;
import com.books.libreria.service.ConvertirDatos;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvertirDatos conversor = new ConvertirDatos();

    public void muestraLibreria() {

        var json = consumoApi.obtenerDatos(URL_BASE);
        var datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println("******************************************");
        // System.out.println(datos);
        System.out.println("******************************************");

        // Top 10 libros mas descargados
        System.out.println("*****Top 10 Episodios*****");
        datos.resultado().stream()
                .sorted(Comparator.comparing(DataLibros::descargas).reversed())
                .limit(10)
                .map(l -> l.titulo().toUpperCase())
                .forEach(System.out::println);

        // Ingrese el titulo del libro de desea leer:
        System.out.println("Ingrese el titulo del libro de desea leer: ");
        var pedazoTitulo = teclado.nextLine();
        json = consumoApi.obtenerDatos(URL_BASE + "?search=" + pedazoTitulo.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        Optional<DataLibros> libroBuscado = datosBusqueda.resultado().stream()
                .filter(l -> l.titulo().toUpperCase().contains(pedazoTitulo.toUpperCase()))
                .findFirst();

        if (libroBuscado.isPresent()) {
            System.out.println("LibroEncontrado...!!!");
            System.out.println("Los datos encontrados son: " + libroBuscado.get().titulo() + " "
                    + libroBuscado.get().autor().get(0).autores());
        } else {
            System.out.println("Libro no encontrado");
        }

        // Estadisticas
        DoubleSummaryStatistics est = datosBusqueda.resultado().stream()
                .filter(l -> l.descargas() > 0.0)
                .collect(Collectors.summarizingDouble(DataLibros::descargas));

        System.out.println("Media de las evaluacione: " + est.getAverage());
        System.out.println("Episodio peor evaluado: " + est.getMin());
        System.out.println("Episodio mejor evaluado: " + est.getMax());

    }
}
