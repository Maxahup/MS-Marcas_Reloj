package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Marca_reloj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String rut;
    private int min_10;
    private int min_25;
    private int min_45;
    private int ausencia;

    /*
    public Marca_reloj(String rut, Integer min_10, Integer min_25, Integer min_45, Integer ausencia) {
        this.rut = rut;
        this.min_10 = min_10;
        this.min_25 = min_25;
        this.min_45 = min_45;
        this.ausencia = ausencia;
    }

    public Marca_reloj(Integer id, String rut, Integer min_10, Integer min_25, Integer min_45, Integer ausencia) {
        this.id = id;
        this.rut = rut;
        this.min_10 = min_10;
        this.min_25 = min_25;
        this.min_45 = min_45;
        this.ausencia = ausencia;
    }

    public Marca_reloj() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Integer getMin_10() {
        return min_10;
    }

    public void setMin_10(Integer min_10) {
        this.min_10 = min_10;
    }

    public Integer getMin_25() {
        return min_25;
    }

    public void setMin_25(Integer min_25) {
        this.min_25 = min_25;
    }

    public Integer getMin_45() {
        return min_45;
    }

    public void setMin_45(Integer min_45) {
        this.min_45 = min_45;
    }

    public Integer getAusencia() {
        return ausencia;
    }

    public void setAusencia(Integer ausencia) {
        this.ausencia = ausencia;
    }

    */
}
