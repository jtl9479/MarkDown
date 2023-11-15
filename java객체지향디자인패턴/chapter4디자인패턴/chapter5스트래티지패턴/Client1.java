package java객체지향디자인패턴.chapter4디자인패턴.chapter5스트래티지패턴;

interface MovingStrategy {
    public void move();    
}

class WakingStrategy implements MovingStrategy{
    @Override
    public void move(){
        System.out.println("waking");
    }

}

class FlyingStrategy implements MovingStrategy{
    @Override
    public void move(){
        System.out.println("flying");
    }

}

interface AttackStrategy {
    public void attack();    
}

class PunchStrategy implements AttackStrategy{
    @Override
    public void attack(){
        System.out.println("PunchStrategy");
    }

}

class MissileStrategy implements AttackStrategy{
    @Override
    public void attack(){
        System.out.println("MissileStrategy");
    }

}


abstract class Robot{
    private String name;
    private MovingStrategy movingStrategy;
    private AttackStrategy attackStrategy;

    public Robot(String name){
        this.name = name;
    }

    public String getName(){ //Atom, TewonV 노출 
        return name;
    }

    public void move(){
        movingStrategy.move();
    }
    
    public void attack(){
        attackStrategy.attack();
    }

    public void aMovingStrategy(MovingStrategy movingStrategy){
        this.movingStrategy = movingStrategy;
    }

    public void aAttackStrategy(AttackStrategy attackStrategy){
        this.attackStrategy = attackStrategy;
    }
}

class Atom extends Robot{

    public Atom(String name) {
        super(name);
    }
}

class TaewonV extends Robot{

    public TaewonV(String name) {
        super(name);
    }
}

public class Client1 {
    
    public static void main(String[] args) {
        
        Robot taekwonV = new TaewonV("taewonV");

        System.out.println(taekwonV.getName());
        taekwonV.aMovingStrategy(new FlyingStrategy());
        taekwonV.aAttackStrategy(new PunchStrategy());
        taekwonV.move();
        

        
    }

}