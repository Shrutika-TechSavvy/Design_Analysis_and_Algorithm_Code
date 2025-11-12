class StaticBlock{

    static int a=6;
    static int b;

    
    static public void main(String[] args) {
        StaticBlock sb = new StaticBlock();
        System.out.println(sb.a +" "+StaticBlock.b);
    }

    static{
        System.out.println("The static block initilaized");
        b = a * 2;
    }
}