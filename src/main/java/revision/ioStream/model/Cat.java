package revision.ioStream.model;

public class Cat {

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

    public Cat() {
        this.name = "constructorDefaultName";
        this.nickName = "constructorDefaultNickName";
        System.out.println("Calling default constructor");
    }

    public Cat(String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
        System.out.println("Calling normal constructor");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
