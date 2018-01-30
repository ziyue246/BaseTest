package common.pojo;

/**
 * Created by ziyue on 2018/1/29.
 */
public class NameTest {

    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "NameTest{" +
                "\nid=" + id +
                ",\nname='" + name + '\'' +
                '}';
    }
}
