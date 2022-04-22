package com.example.examenpenagenesis.modelo;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class ModeloProducto extends Producto{
    DBHelper dbHelper;

    public ModeloProducto() {
    }

    public ModeloProducto(String nombre, String descripcion, double precio, double stock) {
        super(nombre, descripcion, precio, stock);
    }

    public ModeloProducto(int id, String nombre, String descripcion, String imagen, double precio, double stock) {
        super(id, nombre, descripcion, imagen, precio, stock);
    }

    public void createProducto(Context context) {
        dbHelper = new DBHelper(context);
        String nsql = "INSERT INTO productos (nombre, descripcion, precio, stock) " +
                "values ('" + getNombre() + "','" + getDescripcion() + "','" + getPrecio() + "','" + getStock() + "')";
        dbHelper.noQuery(nsql);
        dbHelper.close();
    }

    public ArrayList<Producto> readProducto(Context context) {
        dbHelper = new DBHelper(context);
        String sql = "SELECT * FROM productos";

        Cursor cursor = dbHelper.query(sql);

        ArrayList<Producto> productoArrayList = new ArrayList<>();

        while (cursor.moveToNext()) {
            Producto producto = new Producto();
            producto.setId(cursor.getInt(0));
            producto.setNombre(cursor.getString(1));
            producto.setDescripcion(cursor.getString(2));
            producto.setImagen(cursor.getString(3));
            producto.setPrecio(cursor.getInt(4));
            producto.setStock(cursor.getInt(5));
            productoArrayList.add(producto);
        }
        dbHelper.close();
        System.out.println();
        return productoArrayList;
    }

    public void updateProducto(Context context, String id) {
        dbHelper = new DBHelper(context);
        String nsql = "UPDATE productos set nombre = '" + getNombre() + "', descripcion  = '" + getDescripcion() + "' , precio = '" + getPrecio() + "', stock= '" + getStock() + "' where id='" + id + "'";
        dbHelper.noQuery(nsql);
        dbHelper.close();

    }

    public void deleteProducto(Context context, String id) {
        dbHelper = new DBHelper(context);
        String nsql = "DELETE FROM productos where id='" + id + "'";
        dbHelper.noQuery(nsql);
        dbHelper.close();
    }

    public List<Producto> SearchProducto(Context context, String id) {
        dbHelper = new DBHelper(context);
        String sql = "SELECT * FROM productos where id='" + id + "'";

        Producto producto = null;

        Cursor cursor = dbHelper.query(sql);

        ArrayList<Producto> productoArrayList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            producto = new Producto();
            producto.setId(cursor.getInt(0));
            producto.setNombre(cursor.getString(1));
            producto.setDescripcion(cursor.getString(2));
            producto.setImagen(cursor.getString(3));
            producto.setPrecio(cursor.getInt(4));
            producto.setStock(cursor.getInt(5));

            productoArrayList.add(producto);
        }
        dbHelper.close();
        return productoArrayList;
    }

    public byte[] readImageProducto(String file) {
        return new byte[0];
    }
}
