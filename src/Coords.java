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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + fst;
        result = prime * result + lst;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Coords))
            return false;
        Coords other = (Coords) obj;
        if (fst != other.fst)
            return false;
        if (lst != other.lst)
            return false;
        return true;
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
