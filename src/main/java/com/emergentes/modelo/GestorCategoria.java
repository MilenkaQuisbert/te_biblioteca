
package com.emergentes.modelo;

import java.util.ArrayList;
import java.util.Iterator;


public class GestorCategoria {
     private ArrayList<Categoria> lista;

    public GestorCategoria() {
        lista = new ArrayList<Categoria>();
    }

    public ArrayList<Categoria> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Categoria> lista) {
        this.lista = lista;
    }

    public void insertarCategoria(Categoria item) {
        lista.add(item);
    }

    public void editarCategoria(int pos, Categoria item) {
        lista.set(pos, item);
    }

    public void eliminarCategoria(int pos) {
        lista.remove(pos);
    }

    public int obtieneId() {
        int idaux = 0;
        for (Categoria item : lista) {
            idaux = item.getId();
        }
        return idaux + 1;
    }

    public int ubicarCategoria(int id) {
        int pos = -1;
        Iterator<Categoria> it = lista.iterator();
        while (it.hasNext()) {
            ++pos;
            Categoria aux = it.next();

            if (aux.getId() == id) {
                break;
            }
        }
        return pos;
    }
    
    
}
