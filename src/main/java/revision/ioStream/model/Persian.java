package revision.ioStream.model;

import java.io.Serializable;

public class Persian extends Cat implements Serializable {

    public Persian() {
        System.out.println("Calling Persian constructor");
    }

    public Persian(String name, String nickName) {
        super(name, nickName);
        System.out.println("Calling Persian normal constructor");
    }

}
