public class Test {

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.knotenHinzufuegen(new Knoten("A"));
        g.knotenHinzufuegen(new Knoten("B"));
        g.knotenHinzufuegen(new Knoten("C"));
        g.knotenHinzufuegen(new Knoten("D"));
        g.knotenHinzufuegen(new Knoten("E"));
        g.kanteHinzufuegen("A", "B", 1);
        g.kanteHinzufuegen("A", "C", 1);
        g.kanteHinzufuegen("B", "C", 1);
        g.kanteHinzufuegen("C", "D", 1);
        g.adjazenzmatrixAusgeben();
    }
}
