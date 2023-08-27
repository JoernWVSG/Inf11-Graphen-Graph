import java.util.ArrayList;

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
     * Ermittelt den Index des Knotens mit dem angegebenen Schlüssel
     *
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
     * Fügt Knoten zum Graph hinzu
     *
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
     * Fügt eine Kante zum Graphen hinzu
     *
     * @param von     Schluessel des Ausgangsknotens
     * @param nach    Schluessel des Zielknotens
     * @param gewicht Gewicht der Kante
     */
    public void kanteHinzufuegen(String von, String nach, int gewicht) {
        int vonIndex = this.gibKnotenIndex(von);
        int nachIndex = this.gibKnotenIndex(nach);
        if (vonIndex > -1 && nachIndex > -1) {
            this.adjazenzMatrix[vonIndex][nachIndex] = gewicht;
            this.adjazenzMatrix[nachIndex][vonIndex] = gewicht;
        }
    }

    /**
     * Fügt eine gerichtete Kante zum Graphen hinzu
     *
     * @param von     Schluessel des Ausgangsknotens
     * @param nach    Schluessel des Zielknotens
     * @param gewicht Gewicht der Kante
     */
    public void gerichteteKanteHinzufuegen(String von, String nach, int gewicht) {
        int vonIndex = this.gibKnotenIndex(von);
        int nachIndex = this.gibKnotenIndex(nach);
        if (vonIndex > -1 && nachIndex > -1) {
            this.adjazenzMatrix[vonIndex][nachIndex] = gewicht;
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

    public Knoten breitenSuche(String start, String ziel) {
        int startIndex = gibKnotenIndex(start);
        int zielIndex = gibKnotenIndex(ziel);
        ArrayList<Integer> warteSchlange = new ArrayList<Integer>();
        // Ein paar nette Ausgaben
        System.out.println("\nBreitensuche mit Startknoten " + start + " und Zielknoten " + ziel + ".");
        // Alle Knoten als nicht besucht setzen
        for (int i = 0; i < this.knotenAnzahl; i++) {
            this.knoten[i].setzeBesucht(false);
        }
        // Markiere Startknoten als besucht
        this.knoten[startIndex].setzeBesucht(true);
        // Speichere StartknotenIndex in Warteschlange
        warteSchlange.add(startIndex);
        while (!warteSchlange.isEmpty()) {
            int aktiverKnotenIndex = warteSchlange.remove(0);
            if (aktiverKnotenIndex == zielIndex) {
                // Zielknoten gefunden
                System.out.println("Knoten " + this.knoten[zielIndex].gibSchluessel() + " wurde gefunden.");
                return this.knoten[aktiverKnotenIndex];
            } else {
                // Füge alle noch nicht besuchten Nachbarknoten in
                // Warteschlange ein und markiere sie als besucht
                for (int i = 0; i < this.knotenAnzahl; i++) {
                    if (this.adjazenzMatrix[aktiverKnotenIndex][i] > 0 && !this.knoten[i].istBesucht()) {
                        this.knoten[i].setzeBesucht(true);
                        warteSchlange.add(i);
                        System.out.println("Knoten " + this.knoten[i].gibSchluessel() + " wurde besucht.");
                    }
                }
            }
        }
        System.out.println("Zielknoten ist nicht erreichbar.");
        return null;
    }

}
