package 이것이자바다.chapter11기본api클래스;

public class test {

    public static void main(String[] args) {
        
        String aaaa = "aaaaaa";
        aaaa.equals(aaaa);
        aa a = new aa("aaaß");

        System.out.println(a.equals("aa"));
    }
}

class aa {

    aa (String aaa) {
        
    }

    public boolean equals(Object obj) {
        return (this == obj);
    }
}

