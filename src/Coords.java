public class Coords<X, Y> {
    private X fst; 
    private Y lst; 
    
    public Coords(X fst, Y lst) { 
      this.fst = fst; 
      this.lst = lst; 
    } 
    
    public void modify(X fst, Y lst) {
        this.fst = fst;
        this.lst = lst;
    }
    
    public X getfst() {
        return this.fst;
    }
    
    public Y getlst() {
        return this.lst;
    }
}
