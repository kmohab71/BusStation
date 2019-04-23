package Malak_Khaled;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import org.bson.types.ObjectId;
public abstract class Employee extends Person {
    @Id
    private ObjectId id;
    @Property("wage")
    private int salary;

    public ObjectId getId() {
        return id;
    }


    public String getName() {
        return super.getName();
    }

    public void setName(final String name) {
        super.setName(name);
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(final int salary) {
        this.salary = salary;
    }

}


