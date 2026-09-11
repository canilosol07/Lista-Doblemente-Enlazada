public class DoubleNode {
    DoubleNode anterior;
    Object dato;
    DoubleNode siguiente;

    public DoubleNode(Object dato) {
        this.anterior = null;
        this.dato = dato;
        this.siguiente = null;
    }
    @Override
    public String toString() {
        return "DoubleNode{" +
                "dato=" + dato +
                ", siguiente=" + siguiente +
                '}';

}
}
