public class Coords implements Comparable<Coords> {
    private int fst; 
    private int lst; 
    
    public Coords(int fst, int lst) { 
        this.fst = fst;
        this.lst = lst;
    } 
    
    public void modify(int fst, int lst) {
        this.fst = fst;
        this.lst = lst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coords coords = (Coords) o;

        if (fst != coords.fst) return false;
        if (lst != coords.lst) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fst;
        result = 31 * result + lst;
        return result;
    }

    public int getfst() {
        return this.fst;
    }

    public int getlst() {
        return this.lst;
    }

    @Override
    public int compareTo(Coords o) {
        return (fst == o.getfst() && lst == o.getlst()) ? 0 : -1;
    }
    
    @Override
    public String toString() {
        return "(" + Integer.toString(fst) + ", " + Integer.toString(lst) + ")";
    }
}
