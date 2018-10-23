package com.erickSA.programaProductos.baseDeDatos;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TMProducto extends AbstractTableModel {

    private List<Producto> lista= new ArrayList<>();

    public TMProducto(List<Producto> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();

    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "id";
        }
        if (column == 1) {
            return "Nombre";
        }
        if (column == 2) {
            return "Marca";

        }
        if (column == 3) {
            return "Precio";
        }
        if (column == 4) {
            return "Stock";
        }else{
            return null;
        }

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Producto p = lista.get(rowIndex);
        if (columnIndex == 0) {
            return p.getId();
        }

        if (columnIndex == 1) {
            return p.getNombre();
        }
        if (columnIndex == 2) {
            return p.getMarca();
        }
        if (columnIndex == 3) {
            return p.getPrecio();
        }
        if (columnIndex == 4) {
            return p.getStock();
        }else{
            return null;
        }

    }
}
