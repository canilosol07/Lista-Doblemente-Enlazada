public class MiListaDoble implements ListInterface {
    DoubleNode cabeza;

    @Override
    public boolean isEmpty() {
        if (this.cabeza == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getSize() {
        if (this.cabeza == null) {
            return 0;
        }
        DoubleNode iterador = this.cabeza;
        int contador = 1;
        while (iterador.siguiente != null) {
            iterador = iterador.siguiente;
            contador = contador + 1;
        }
        return contador;
    }

    @Override
    public void clear() {
        this.cabeza = null;
    }

    @Override
    public Object getHead() {
        return this.cabeza.dato;
    }

    @Override
    public Object getTail() {
        DoubleNode actual = this.cabeza;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    @Override
    public Object get(DoubleNode node) {
        DoubleNode actual = this.cabeza;
        while (actual != null) {
            if (actual == node) {
                return actual.dato;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    @Override
    public DoubleNode search(Object object) {
        DoubleNode actual = this.cabeza;
        while (actual != null) {
            if (actual.dato == object) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    @Override
    public boolean add(Object object) {
        return insertTail(object);
    }

    @Override
    public boolean insert(DoubleNode node, Object object) {
        if (node == null) {
            return false;
        }
        DoubleNode actual = this.cabeza;
        while (actual != null) {
            if (actual == node) {
                DoubleNode nuevoNodo = new DoubleNode(object);
                nuevoNodo.siguiente = node.siguiente;
                nuevoNodo.anterior = node;
                if (node.siguiente != null) {
                    node.siguiente.anterior = nuevoNodo;
                }
                node.siguiente = nuevoNodo;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public boolean insert(Object objectRef, Object object) {
        DoubleNode actual = this.cabeza;
        while (actual != null) {
            if (actual.dato == objectRef) {
                DoubleNode nuevoNodo = new DoubleNode(object);
                nuevoNodo.siguiente = actual.siguiente;
                nuevoNodo.anterior = actual;
                if (actual.siguiente != null) {
                    actual.siguiente.anterior = nuevoNodo;
                }
                actual.siguiente = nuevoNodo;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public boolean insertHead(Object object) {
        DoubleNode nuevaCabeza = new DoubleNode(object);
        nuevaCabeza.siguiente = this.cabeza;
        if (this.cabeza != null) {
            this.cabeza.anterior = nuevaCabeza;
        }
        this.cabeza = nuevaCabeza;
        return true;
    }

    @Override
    public boolean insertTail(Object object) {
        DoubleNode nuevoNodo = new DoubleNode(object);
        if (this.cabeza == null) {
            this.cabeza = nuevoNodo;
        } else {
            DoubleNode actual = this.cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
            nuevoNodo.anterior = actual;
        }
        return true;
    }

    @Override
    public boolean set(DoubleNode node, Object object) {
        node.dato = object;
        return true;
    }

    @Override
    public boolean remove(DoubleNode node) {
        if (node == null || this.cabeza == null) {
            return false;
        }
        if (node == this.cabeza) {
            this.cabeza = node.siguiente;
            if (this.cabeza != null) {
                this.cabeza.anterior = null;
            }
            return true;
        }
        DoubleNode actual = this.cabeza;
        while (actual != null) {
            if (actual == node) {
                actual.anterior.siguiente = actual.siguiente;
                if (actual.siguiente != null) {
                    actual.siguiente.anterior = actual.anterior;
                }
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public boolean contains(Object object) {
        DoubleNode actual = this.cabeza;
        while (actual != null) {
            if (actual.dato == object) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] arreglo = new Object[this.getSize()];
        DoubleNode actual = this.cabeza;
        int i = 0;
        while (actual != null) {
            arreglo[i] = actual.dato;
            i = i + 1;
            actual = actual.siguiente;
        }
        return arreglo;
    }

    @Override
    public Object[] toArray(Object[] object) {
        int size = getSize();
        if (object.length < size) {
            object = new Object[size];
        }
        DoubleNode actual = this.cabeza;
        int i = 0;
        while (actual != null) {
            object[i] = actual.dato;
            i = i + 1;
            actual = actual.siguiente;
        }
        return object;
    }

    DoubleNode buscarNodo(DoubleNode nodo) {
        DoubleNode actual = this.cabeza;
        while (actual != null) {
            if (actual == nodo) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    @Override
    public MiListaDoble subList(DoubleNode from, DoubleNode to) {
        MiListaDoble subLista = new MiListaDoble();

        DoubleNode nodoInicio = buscarNodo(from);
        DoubleNode nodoFin = buscarNodo(to);

        if (nodoInicio == null || nodoFin == null) {
            return subLista;
        }

        DoubleNode actual = nodoInicio;
        while (actual != null) {
            subLista.insertTail(actual.dato);
            if (actual == nodoFin) {
                return subLista;
            }
            actual = actual.siguiente;
        }

        return new MiListaDoble();
    }

    @Override
    public MiListaDoble sortList() {
        Object[] arreglo = this.toArray();

        int i = 0;
        while (i < arreglo.length - 1) {
            int j = 0;
            while (j < arreglo.length - 1 - i) {
                Comparable actual = (Comparable) arreglo[j];
                if (actual.compareTo(arreglo[j + 1]) > 0) {
                    Object temporal = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temporal;
                }
                j = j + 1;
            }
            i = i + 1;
        }

        MiListaDoble listaOrdenada = new MiListaDoble();
        int k = 0;
        while (k < arreglo.length) {
            listaOrdenada.insertTail(arreglo[k]);
            k = k + 1;
        }
        return listaOrdenada;
    }

    @Override
    public String toString() {
        return "MiListaDoble{" +
                "cabeza=" + cabeza +
                '}';
    }
}
