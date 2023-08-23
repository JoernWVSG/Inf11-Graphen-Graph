public class Graph {

    private int knotenAnzahl;
    private Knoten[] knoten;
    private int[][] adjazenzMatrix;

    /**
     * @param maxKnotenAnzahl Maximale Anzahl von Knoten im Graph
     */
    public Graph(int maxKnotenAnzahl) {
        this.knoten = new Knoten[maxKnotenAnzahl];
        this.adjazenzMatrix = new int[maxKnotenAnzahl][maxKnotenAnzahl];
        this.knotenAnzahl = 0;
        // alle Matrixeintraege auf 0 setzen
        for (int i = 0; i < maxKnotenAnzahl; i++) {
            for (int j = 0; j < maxKnotenAnzahl; j++) {
                this.adjazenzMatrix[i][j] = 0;
            }

        }
    }

    /**
     * @param schluessel Bezeichner des Knotens, dessen Index gefunden werden soll
     * @return Index des Knotens; -1, falls Knoten nicht gefunden wird
     */
    private int gibKnotenIndex(String schluessel) {
        for (int i = 0; i < this.knotenAnzahl; i++) {
            if (this.knoten[i].gibSchluessel().equalsIgnoreCase(schluessel)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param knoten Referenz auf Knoten, der hinzugefügt werden soll
     */
    public void knotenHinzufuegen(Knoten knoten) {
        if (this.knotenAnzahl < this.knoten.length) {
            this.knoten[this.knotenAnzahl] = knoten;
            this.knotenAnzahl++;
        } else {
            System.out.println("Maximale Knotenanzahl erreict.");
        }
    }

    /**
     * @param von     Schluessel des Ausgangsknotens
     * @param nach    Schluessel des Zielknotens
     * @param gewicht Gewicht der Kante
     */
    public void kanteHinzufuegen(String von, String nach, int gewicht) {
        int vonIndex = this.gibKnotenIndex(von);
        int nachIndex = this.gibKnotenIndex(nach);
        if (vonIndex>-1 && nachIndex>-1) {
            this.adjazenzMatrix[vonIndex][nachIndex] = gewicht;
            this.adjazenzMatrix[nachIndex][vonIndex] = gewicht;
        }
    }

    /**
     * Ausgabe der Knotenliste
     */
    public void knotenAusgeben() {
        for (int i = 0; i < this.knotenAnzahl; i++) {
            System.out.print("\t" + this.knoten[i].gibSchluessel());
        }
        System.out.println();
    }

    /**
     * Adjazenzmatrix ausgeben
     */
    public void adjazenzmatrixAusgeben() {
        this.knotenAusgeben();
        for (int i = 0; i < this.knotenAnzahl; i++) {
            System.out.print(this.knoten[i].gibSchluessel());
            for (int j = 0; j < this.knotenAnzahl; j++) {
                System.out.print("\t" + this.adjazenzMatrix[i][j]);
            }
            System.out.println();
        }
    }
}
