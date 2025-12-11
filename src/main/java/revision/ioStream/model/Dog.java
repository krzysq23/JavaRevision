package revision.ioStream.model;

import java.io.Serializable;

public class Dog implements Serializable  {

    private static final long serializeVersionUID = 1L;

    private transient String name = "fieldName";

    private String nickName = "fieldNickname";

    static int age = 10;

    {
        this.name = "defaultName";
        this.nickName = "defaultNickName";
        System.out.println("Calling init block");
    }

    static {
        age = 100;
        System.out.println("Calling static init block");
    }

    public Dog() {
        this.name = "constructorDefaultName";
        this.nickName = "constructorDefaultNickName";
        System.out.println("Calling default constructor");
    }

    public Dog(String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
        System.out.println("Calling normal constructor");
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    public static int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
