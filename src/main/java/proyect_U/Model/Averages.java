package proyect_U.Model;

import jakarta.persistence.*;


@Entity
public class Averages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String UserValues;
    private int userAverage;
    private int usuario;

    public Averages(Long id, String UserValues, int userAverage, int usuario) {
        Id = id;
        this.UserValues = UserValues;
        this.userAverage = userAverage;
        this.usuario = usuario;
    }

    public Averages(){

    }
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getValues() {
        return UserValues;
    }

    public void setValues(String UserValues) {
        this.UserValues = UserValues;
    }

    public int getAverage() {
        return userAverage;
    }

    public void setAverage(int userAverage) {
        this.userAverage = userAverage;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
}
