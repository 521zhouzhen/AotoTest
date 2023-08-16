package suanfa.copy;

public class Person implements Cloneable{
    Address address;
    public Person(Address address){
        this.address=address;
    }
    public Person qianCopy(Person person) throws CloneNotSupportedException {
        Object clone = super.clone();
        return (Person)clone;
    }

}
