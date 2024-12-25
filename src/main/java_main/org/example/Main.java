package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public String name = "PA";
    public static String name2 = "Phuong Anh";

    public void getName1(){
        System.out.println("Ten: " + name);
    }
    public void getName2(){
        System.out.println("Ten that: " + name2);
    }

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");
        Main pa = new Main();
        pa.getName1();

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            if(i == 1){
                pa.getName2();
            }
            else
                System.out.println("i = " + i + " & " + name2);
        }
        String name1 = pa.name;

    }
}