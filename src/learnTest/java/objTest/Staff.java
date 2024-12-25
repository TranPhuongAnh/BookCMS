package objTest;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class Staff {
    static int code = 0;
    String name;
    int age;
    String sex;
    LocalDate dob;


    int dateWork = 0;

    public void Info(String name, LocalDate dob, String sex){
        this.name = name;
        this.dob = dob;
        this.sex = sex;
        code += 1;
    }

    public int Work(int sumDate, int offDate){
        dateWork = sumDate - offDate;
        return dateWork;
    }

    public int Salary(int basicSalary){
        int s = dateWork * basicSalary;
        return s;
    }

    public void PrtInfo(){
        System.out.println("\nMã nhân viên: " + code);
        System.out.println("Tê nhân viên: " + name);
        LocalDate currDate = LocalDate.now();
        age = currDate.get(ChronoField.YEAR) - dob.get(ChronoField.YEAR);
        System.out.println("Tuổi: " + age);
        System.out.println("Ngày sinh: " + dob);
        System.out.println("Giới tính: " + sex);
//        System.out.println("Số ngày làm việc: " + dateWork);
//        System.out.println("Lương tháng của nhân viên: " + s);
    }

    public String Password(String p, String kytu){
        int a = p.length();
        String string = "";
        if (a < 2){
            string = kytu;
        } else {
            for (int i = 0; i < a ; i++){
                string += kytu;
            }
        }
        return string;
    }

}
