package com.example.examenpenagenesis.adaptador;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenpenagenesis.MainActivity;
import com.example.examenpenagenesis.R;
import com.example.examenpenagenesis.modelo.Producto;

import java.util.ArrayList;

public class ListaProductoAdapter extends RecyclerView.Adapter<ListaProductoAdapter.ProductoViewHolder> {
    ArrayList<Producto> productoArrayList;
    ArrayList<Producto> productoArrayListOriginal;

    public ListaProductoAdapter(ArrayList<Producto> productoArrayList) {
        this.productoArrayList = productoArrayList;
        productoArrayListOriginal = new ArrayList<>();
        productoArrayListOriginal.addAll(productoArrayList);
    }


    @NonNull

    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_producto, null, false);
        return new ProductoViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.viewID.setText(String.valueOf(productoArrayList.get(position).getId()));
        holder.viewNombre.setText(productoArrayList.get(position).getNombre());
        holder.viewPrecio.setText("\t\t" + productoArrayList.get(position).getPrecio() + "$");
        holder.viewStock.setText("Stock: " + productoArrayList.get(position).getStock());
        holder.viewDescripcion.setText(productoArrayList.get(position).getDescripcion());
    }

    public int getItemCount() {
        return productoArrayList.size();

    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        TextView viewID, viewNombre, viewStock, viewPrecio, viewDescripcion;
        private EditText txtNombre;

        private ImageButton vbtnEliminar, vbtnEditar;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewID = itemView.findViewById(R.id.viewID);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewStock = itemView.findViewById(R.id.viewStock);
            viewPrecio = itemView.findViewById(R.id.viewPrecio);
            viewDescripcion = itemView.findViewById(R.id.viewDescripcion);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    intent.putExtra("id", productoArrayList.get(getAdapterPosition()).getId());
                    view.getContext().startActivity(intent);

                    //Toast.makeText(view.getContext(), "ID: " + viewID.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
