package proyect_U.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String lastName;
    private Date birthDate;
    private Float weight;
    private Float Height;
    private int register;

    public Usuarios(Long Id, String name, String lastName, Date birthDate, Float weight, Float height,
                    int register){
        this.Id = Id;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.weight = weight;
        this.Height = height;
        this.register = register;
    }

    public Usuarios(){

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getHeight() {
        return Height;
    }

    public void setHeight(Float height) {
        Height = height;
    }

    public int getIdRegister() {
        return register;
    }

    public void setIdRegister(int idRegister) {
        register = idRegister;
    }
}
