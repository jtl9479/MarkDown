package java객체지향디자인패턴.chapter1객체지향모델링.src;

public class cord2_4 {

    public static void main(String[] args) {
        ArrayStack st = new ArrayStack(10);
        //st.itemArray[++st.top] = -2;
        //System.out.println(st.itemArray[st.top]);
        //System.out.println(st.peek());
        st.push(0);
        System.out.println(st.peek());
    }

}

class ArrayStack {
    public int top;
    public int[] itemArray;
    public int stackSize;

    public ArrayStack(int stackSize) {
        itemArray = new int[stackSize];
        top = -1;
        this.stackSize = stackSize;
    }

    public boolean isEmpty() { //스택이 비어 있는지 검사
        return (top == -1);
    }

    public boolean isFull() { //스택이 꽉 차 있는지 검사
        return (top == this.stackSize - 1);
    }

    public void push(int item) {
        if(isFull()) {
            System.out.println("fail Stack is full");
        } else {
            itemArray[++top] = item;
            System.out.println("item : " + item);
        }
    }

    public int pop() {
        if(isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        } else {
            return itemArray[top--];
        }
    }

    public int peek() {
        if(isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        } else {
            return itemArray[top];
        }        
    }
}
