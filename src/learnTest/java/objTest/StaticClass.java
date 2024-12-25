package objTest;

class Student{
    public String name;
    public int age;
    public float height;

    public static String uni = "VNU University of Engineering and Technology";
    //biến không có static thì biến luôn mặc định khởi tạo từ giá trị được gán
    //Biến có static thì giữ nguyên là biến toàn cục, chỉ khởi tạo 1 lần sau đó giá trị của nó là giá trị thay đổi
    public static int totalStudent = 0;

    //Hàm không có datatype phải đặt theo tên class => gán biến cho class
    public Student(String name, int age, float height){
        this.name = name;
        this.age = age;
        this.height = height;
        totalStudent += 1;
    }
    public void PrtStudent(){
        System.out.println("Number student: " + totalStudent);
        System.out.println("Student name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height + "\n");
    }
}

public class StaticClass {

    public static void main(String[] args) {
        System.out.println("University (from Class): " + Student.uni);
        //Student 1
        Student s1 = new Student("Phuong Anh", 26, 1.5f);
        s1.PrtStudent();

        //Student 2
        Student s2 = new Student("Lan Anh", 25, 1.49f);
        s2.PrtStudent();

        //Student 3
        Student s3 = new Student("Phuong Thuy", 25, 1.56f);
        s3.PrtStudent();
    }

}
