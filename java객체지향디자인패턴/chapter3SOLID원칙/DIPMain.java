package java객체지향디자인패턴.chapter3SOLID원칙;

// 고수준 모듈: 주문을 처리하는 클래스
class OrderProcessor {
    private PaymentGateway paymentGateway;

    public OrderProcessor(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void processOrder(double amount) {
        // 주문 처리 로직
        paymentGateway.pay(amount);
    }
}

// 저수준 모듈: 결제 기능을 제공하는 인터페이스
interface PaymentGateway {
    void pay(double amount);
}

// 실제 결제 처리를 구현하는 저수준 모듈
class CreditCardPayment implements PaymentGateway {
    @Override
    public void pay(double amount) {
        // 실제 결제 로직
        System.out.println("Credit card payment: $" + amount);
    }
}

class PayPalPayment implements PaymentGateway {
    @Override
    public void pay(double amount) {
        // PayPal 결제 로직
        System.out.println("PayPal payment: $" + amount);
    }
}

// 메인 클래스
public class DIPMain {
    public static void main(String[] args) {
        PaymentGateway paymentGateway = new CreditCardPayment(); // 저수준 모듈 인스턴스 생성
        OrderProcessor orderProcessor = new OrderProcessor(paymentGateway); // 고수준 모듈 인스턴스 생성

        double orderAmount = 100.0;
        orderProcessor.processOrder(orderAmount);

        PaymentGateway paypalPaymentGateway = new PayPalPayment(); // 다른 저수준 모듈 인스턴스 생성
        OrderProcessor orderProcessorWithPayPal = new OrderProcessor(paypalPaymentGateway); // 고수준 모듈 인스턴스 생성

        double orderAmountWithPayPal = 150.0;
        orderProcessorWithPayPal.processOrder(orderAmountWithPayPal);
    }
}


















