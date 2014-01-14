/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author charles
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "RECURSO_DETAILS_TYPE", discriminatorType = DiscriminatorType.STRING)
public class Recurso implements Serializable {

    @OneToMany(mappedBy = "recurso", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ReservaRecurso> reservaRecursos = new ArrayList<ReservaRecurso>();

    public List<ReservaRecurso> getReservaRecursos() {
        return reservaRecursos;
    }

    public void setReservaRecursos(List<ReservaRecurso> reservaRecursos) {
        this.reservaRecursos = reservaRecursos;
    }

    
    
    
//    @ManyToOne
//    private Recurso reserva;
//
//    public Recurso getReserva() {
//        return reserva;
//    }
//
//    public void setReserva(Recurso reserva) {
//        this.reserva = reserva;
//    }
//    @OneToMany(mappedBy = "recurso", fetch = FetchType.EAGER)
//    @ManyToMany(mappedBy = "recursos", fetch = FetchType.EAGER)
//    protected List<Reserva> reservas;
//
    public List<Reserva> getReservas() {
        List<Reserva> reservas = new ArrayList<Reserva>();
        for (ReservaRecurso reservaRecurso1 : reservaRecursos) {
            reservas.add(reservaRecurso1.getReserva());
        }
        return reservas;
    }
//
//    public void setReservas(List<Reserva> reservas) {
//        this.reservas = reservas;
//    }
//
//    public void addReserva(Reserva reserva) {
//        reservas.add(reserva);
//        //reserva.setRecurso(this);
//        reserva.addRecurso(this);
//    }
//

    public void remReserva(Reserva reserva) {
        // reservas.remove(reserva);
        getReservas().remove(reserva);
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @ManyToOne
    protected Centro centro;

    public Centro getCentro() {
        return centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstance() {
        return this.getClass().toString().replace("class model.", "");
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recurso)) {
            return false;
        }
        Recurso other = (Recurso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Recurso[ id=" + id + " ]";
    }
}
