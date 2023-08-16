package java객체지향디자인패턴.chapter4디자인패턴.chapter7스테이트패턴;

/*
 * State 인터페이스:

doAction(Context context) 메서드를 정의하고 있습니다.
이 인터페이스는 TV의 전원 상태를 나타내는 각 상태 클래스가 구현합니다.
OnState 클래스:

State 인터페이스를 구현하고, doAction 메서드를 오버라이딩합니다.
TV가 켜진 상태를 나타내며, 메서드 내에서 "TV is turned ON" 메시지를 출력하고, 현재 상태를 Context에 설정합니다.
OffState 클래스:

State 인터페이스를 구현하고, doAction 메서드를 오버라이딩합니다.
TV가 꺼진 상태를 나타내며, 메서드 내에서 "TV is turned OFF" 메시지를 출력하고, 현재 상태를 Context에 설정합니다.
Context 클래스:

State 인터페이스의 구현체를 저장하는 역할을 합니다.
setState(State state) 메서드로 현재 TV의 상태를 설정합니다.
doAction() 메서드를 호출하면 현재 상태에 따라 해당 상태의 doAction 메서드가 실행됩니다.
StatePatternExample 클래스:

Context를 생성하고, 상태 객체를 생성하여 doAction 메서드를 호출하는 예제입니다.
 */

// 상태를 정의하는 인터페이스
interface State {
    void doAction(Context context);
}

// TV가 켜진 상태를 나타내는 클래스
class OnState implements State {
    public void doAction(Context context) {
        System.out.println("TV is turned ON");
        context.setState(this);
    }
}

// TV가 꺼진 상태를 나타내는 클래스
class OffState implements State {
    public void doAction(Context context) {
        System.out.println("TV is turned OFF");
        context.setState(this);
    }
}

// TV의 상태를 관리하는 컨텍스트 클래스
class Context {
    private State state;

    // 현재 상태를 설정하는 메서드
    public void setState(State state) {
        this.state = state;
    }

    // 상태에 따라 해당 상태의 동작을 수행하는 메서드
    public void doAction() {
        state.doAction(this);
    }
}

public class StatePatternExample {
    public static void main(String[] args) {
        // 컨텍스트 생성
        Context context = new Context();

        // TV가 켜진 상태의 객체 생성 및 동작 수행
        State onState = new OnState();
        onState.doAction(context); // 출력: "TV is turned ON"

        // TV가 꺼진 상태의 객체 생성 및 동작 수행
        State offState = new OffState();
        offState.doAction(context); // 출력: "TV is turned OFF"
    }
}
