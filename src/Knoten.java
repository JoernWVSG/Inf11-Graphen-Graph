public class Knoten {

    private String schluessel;
    private boolean besucht;


    /**
     * @param schluessel Eindeutiger Bezeichner des Knotens
     */
    public Knoten(String schluessel) {
        this.schluessel = schluessel;
    }

    /**
     * @return Wert des Attributs schluessel
     */
    public String gibSchluessel() {
        return this.schluessel;
    }

    /**
     * @param schluessel Eindeutiger Bezeichner des Knotens
     */
    public void setzeSchluessel(String schluessel) {
        this.schluessel = schluessel;
    }

    public boolean istBesucht() {
        return besucht;
    }

    public void setzeBesucht(boolean besucht) {
        this.besucht = besucht;
    }

}
