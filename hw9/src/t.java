import tester.Tester;

interface List<T> {
    void addAtTail(List<Integer> t); 
    List<Integer> addAtTailHelp(List<Integer> t);
}

class Mt implements List<Integer> {

    public void addAtTail(List<Integer> t) {
        return;
    }

    public List<Integer> addAtTailHelp(List<Integer> t) {
        return t;
    }
}

class Cons implements List<Integer> {
    int first;
    List<Integer> rest;
    
    Cons(int first, List<Integer> rest) {
        this.first = first;
        this.rest = rest; 
    }

    public void addAtTail(List<Integer> t) {
        this.rest = this.rest.addAtTailHelp(t); 
    }

    public List<Integer> addAtTailHelp(List<Integer> t) {
        return new Cons(this.first, this.rest.addAtTailHelp(t));
    }   
}

class ExamplesT {
    
    List<Integer> mt = new Mt(); 
    List<Integer> l1 = new Cons(1, new Cons(2, new Cons(3, mt))); 
    List<Integer> l2 = new Cons(4, new Cons(5, new Cons(6, mt))); 
    
    void testAddAtHead(Tester t) {
        l2.addAtTail(l1);
        t.checkExpect(l2, mt);
        l2.addAtTail(l1);
        t.checkExpect(l2, mt);
    }
}



