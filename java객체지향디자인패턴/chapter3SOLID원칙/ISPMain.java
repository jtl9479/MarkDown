package java객체지향디자인패턴.chapter3SOLID원칙;

// 인터페이스 분리 원칙을 따르지 않은 예제

interface Worker {
    void work();
    void eat();
    void sleep();
}

class Programmer implements Worker {
    @Override
    public void work() {
        System.out.println("Programming");
    }

    @Override
    public void eat() {
        System.out.println("Eating lunch");
    }

    @Override
    public void sleep() {
        System.out.println("Sleeping");
    }
}

class Chef implements Worker {
    @Override
    public void work() {
        System.out.println("Cooking");
    }

    @Override
    public void eat() {
        System.out.println("Eating dinner");
    }

    @Override
    public void sleep() {
        // Chef는 밤에 일하지 않으므로 sleep 메서드 필요 없음
    }
}

public class ISPMain {
    public static void main(String[] args) {
        Worker programmer = new Programmer();
        programmer.work();
        programmer.eat();
        programmer.sleep();

        Worker chef = new Chef();
        chef.work();
        chef.eat();
        chef.sleep();
    }
}

interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

interface Sleepable {
    void sleep();
}
/* 
class Programmer implements Workable, Eatable, Sleepable {
    // ...
}

//class Chef implements Workable, Eatable {
    // ...
}
*/