package java객체지향디자인패턴.chapter4디자인패턴.chapter5스트래티지패턴;

public class Client {
    
    public static void main(String[] args) {
        
        Robot taekwonV = new TaewonV("taewonV");
        Atom atom = new Atom("atom");

        System.out.println(taekwonV.getName());
        taekwonV.attack();
        taekwonV.move();

        System.out.println(atom.getName());
        atom.attack();
        atom.move();
        atom.fly();
        
    }
}

abstract class Robot {
    private String name;
    
    public Robot(String name) {//생성자
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void attack();
    public abstract void move();
}

class TaewonV extends Robot {

    public TaewonV(String name) {
        super(name);
    }

    @Override
    public void attack() {
        System.out.println("TaewonV attack");
    }

    @Override
    public void move() {
        System.out.println("TaewonV move");
    }
    
}

class Atom extends Robot {

    public Atom(String name) {
        super(name);
    }

    @Override
    public void attack() {
        System.out.println("Atom attack");
    }

    @Override
    public void move() {
        System.out.println("Atom move");
    }
    
    public void fly() {
        System.out.println("Atom fly");
    }
    
}
