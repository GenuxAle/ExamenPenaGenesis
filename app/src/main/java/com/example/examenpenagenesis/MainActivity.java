package com.example.examenpenagenesis;

import android.content.Intent;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenpenagenesis.adaptador.ListaProductoAdapter;
import com.example.examenpenagenesis.modelo.ModeloProducto;
import com.example.examenpenagenesis.modelo.Producto;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre, txtStock, txtPrecio, txtDescripcion, txtSearch;
    private Button btnCrear, btnEditar, btnEliminar;
    private TextView txtID;


    private RecyclerView listProductos;
    private ListaProductoAdapter listaProductoAdapter;

    private TextWatcher textWatcher = null;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listProductos = findViewById(R.id.listProductos);
        listProductos.setLayoutManager(new LinearLayoutManager(this));

        ModeloProducto modeloProducto = new ModeloProducto();

        listaProductoAdapter = new ListaProductoAdapter(modeloProducto.readProducto(MainActivity.this));
        listProductos.setAdapter(listaProductoAdapter);

        txtNombre = findViewById(R.id.txtNombre);
        txtStock = findViewById(R.id.txtStock);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtDescripcion = findViewById(R.id.txtDescripcion);

        btnCrear = findViewById(R.id.btnCrear);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().equals("") && !txtStock.getText().toString().equals("") && !txtPrecio.getText().toString().equals("") && !txtDescripcion.getText().toString().equals("")) {
                    Crear(txtNombre.getText().toString(), txtStock.getText().toString(), txtPrecio.getText().toString(), txtDescripcion.getText().toString());
                    Toast.makeText(MainActivity.this, "REGISTRO GUARDADO CON EXITO", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "DEBE LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnEditar = findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtSearch.getText().toString().equals("") && !txtNombre.getText().toString().equals("") && !txtStock.getText().toString().equals("") && !txtPrecio.getText().toString().equals("") && !txtDescripcion.getText().toString().equals("")) {
                    Editar(txtSearch.getText().toString(), txtNombre.getText().toString(), txtStock.getText().toString(), txtPrecio.getText().toString(), txtDescripcion.getText().toString());
                    Toast.makeText(MainActivity.this, "REGISTRO MODIFICADO CON EXITO", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "DEBE LLENAR TODOS LOS CAMPOS Y EL CAMPO DE BUSQUEDA PARA PODER EDITAR EL DATO", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnEliminar = findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar();
            }
        });

        txtSearch = findViewById(R.id.txtSearch);
        txtID = findViewById(R.id.txtID);
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //txtNombre.setText(txtSearch.getText().toString());
                txtID.setText(txtSearch.getText().toString());

                String id = txtID.getText().toString();

                System.out.println(id);

                ModeloProducto modeloProducto = new ModeloProducto();
                List<Producto> productoList = modeloProducto.SearchProducto(MainActivity.this, id);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    productoList.stream().forEach(p -> {
                        String[] producto = {p.getNombre(), p.getDescripcion(), p.getPrecio() + "", p.getStock() + ""};

                        txtNombre.setText(producto[0]);
                        txtDescripcion.setText(producto[1]);
                        txtPrecio.setText(producto[2]);
                        txtStock.setText(producto[3]);

                    });
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        txtSearch.addTextChangedListener(textWatcher);

        txtSearch.setText(getID(savedInstanceState) + "");
        System.out.println("la id es la siguiente: " + getID(savedInstanceState));


    }

    private void Crear(String nombre, String stock, String precio, String descripcion) {
        ModeloProducto modeloProducto = new ModeloProducto(nombre, descripcion, Double.parseDouble(precio), Double.parseDouble(stock));
        modeloProducto.createProducto(MainActivity.this);
    }

    private void Editar(String id, String nombre, String stock, String precio, String descripcion) {
        ModeloProducto modeloProducto = new ModeloProducto(nombre, descripcion, Double.parseDouble(precio), Double.parseDouble(stock));
        modeloProducto.updateProducto(MainActivity.this, id);

    }

    private void Eliminar() {
        ModeloProducto modeloProducto = new ModeloProducto();
        modeloProducto.deleteProducto(MainActivity.this, txtID.getText().toString());
    }

    private int getID(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                id = extras.getInt("id");
            }
        } else {
            return id = (int) savedInstanceState.getSerializable("id");
        }
        return id;

    }

}