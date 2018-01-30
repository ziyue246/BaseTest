package common.pojo;

/**
 * Created by ziyue on 2018/1/29.
 */
public class NameTest {

    private  InsideId insideId;
    private String name;



    public InsideId getInsideId() {
        return insideId;
    }

    public void setInsideId(InsideId insideId) {
        this.insideId = insideId;
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
                "\ninsideId=" + insideId +
                ",\n name='" + name + '\'' +
                '}';
    }
}



class InsideId {
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "InsideId{" +
                "id=" + id +
                '}';
    }
}