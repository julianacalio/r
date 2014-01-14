/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import org.primefaces.model.ScheduleEvent;
import util.DateTools;

/**
 *
 * @author charles
 */
@Entity
public class Reserva implements Serializable, ScheduleEvent {

    @ManyToOne(fetch = FetchType.EAGER)
    private GrupoReserva grupoReserva;

    public GrupoReserva getGrupoReserva() {
        return grupoReserva;
    }

    public void setGrupoReserva(GrupoReserva grupoReserva) {
        this.grupoReserva = grupoReserva;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long iid;
    @ManyToOne
    private Operador operador;

    @ManyToOne(fetch = FetchType.EAGER)
    private Pessoa reservante;
    @ManyToOne
    private Centro centro;
    String motivo;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ReservaRecurso> reservaRecursos = new ArrayList<ReservaRecurso>();

    public List<ReservaRecurso> getReservaRecursos() {
        return reservaRecursos;
    }

    public void setReservaRecursos(List<ReservaRecurso> reservaRecursos) {
        this.reservaRecursos = reservaRecursos;
    }
    
    
    

//    @ManyToMany(fetch = FetchType.EAGER)
//    protected List<Recurso> recursos = new ArrayList<Recurso>();
    public List<Recurso> getRecursos() {
        List<Recurso> recursos = new ArrayList<Recurso>();
        for (ReservaRecurso reservaRecurso1 : reservaRecursos) {
            recursos.add(reservaRecurso1.getRecurso());
        }
        return recursos;
    }

    public void addRecurso(Recurso recurso) {
        ReservaRecurso reservaRecurso = new ReservaRecurso();
        reservaRecurso.setReserva(this);
        reservaRecurso.setRecurso(recurso);
        reservaRecursos.add(reservaRecurso);
        // this.recursos.add(recurso);
    }

    public void setRecursos(List<Recurso> recursos) {
       
//        this.recursos.clear();
//        this.recursos.addAll(recursos);
        
        this.getRecursos().clear();
        addAll(recursos);
    }

    public void addAll(List<Recurso> recursos) {

        for (Recurso recurso : recursos) {
            ReservaRecurso resRec = new ReservaRecurso();
            resRec.setReserva(this);
            resRec.setRecurso(recurso);
            reservaRecursos.add(resRec);
        }
      //  this.recursos.addAll(recursos);
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    @Transient
    String id = null;

    public Centro getCentro() {
        return centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public void limparRecursos() {
        //this.recursos.clear();
        this.getRecursos().clear();
    }

    public Pessoa getReservante() {
        return reservante;
    }

    public void setReservante(Pessoa reservante) {
        this.reservante = reservante;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public Date getRealizacao() {
        return realizacao;
    }

    public void setRealizacao(Date realizacao) {
        this.realizacao = realizacao;
    }

    public String getDataInicio() {
        return DateTools.getData(inicio);
    }

    public String getDataFim() {
        return DateTools.getData(fim);
    }

    public String getHoraInicio() {
        return DateTools.getHora(inicio);
    }

    public String getHoraFim() {
        return DateTools.getHora(fim);
    }

    public Reserva createClone() {
        Reserva res = new Reserva();
        res.centro = this.centro;
        res.fim = this.fim;
        res.inicio = this.inicio;
        res.fim = this.fim;
        res.realizacao = this.realizacao;
        //    res.recursos = this.recursos;
        res.reservaRecursos = this.reservaRecursos;
        res.reservante = this.reservante;
        res.motivo = this.motivo;
        return res;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date inicio;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fim;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date realizacao;

    public Long getIid() {
        return iid;
    }

    public void setIid(Long id) {
        this.iid = id;
    }

    @Override
    public int hashCode() {
        /*int hash = 5;
         hash = 61 * hash + (this.titulo != null ? this.titulo.hashCode() : 0);
         hash = 61 * hash + (this.inicio != null ? this.inicio.hashCode() : 0);
         hash = 61 * hash + (this.fim != null ? this.fim.hashCode() : 0);
         return hash;*/
        int hash = 0;
        hash += (iid != null ? iid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.iid == null && other.iid != null) || (this.iid != null && !this.iid.equals(other.iid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return reservante.getNome() + (motivo != null ? (" - " + motivo) : "");
    }

    // Métodos da Interface ScheduleEvent
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String string) {
        id = string;
    }

    @Override
    public Object getData() {
        return realizacao;
    }

    @Override
    public String getTitle() {
        if (getRecursos() != null && !getRecursos().isEmpty()) {
            String title = " " + reservante.getNome() + (motivo == null || motivo.isEmpty() ? "\n :: nao especificado" : " \n :: " + motivo);

            for (Recurso recursoAssociado : getRecursos()) {
                title += "\n" + recursoAssociado.toString();
            }

            return title;
        }
        return " " + reservante.getNome() + (motivo == null || motivo.isEmpty() ? "\n :: nao especificado" : " \n :: " + motivo);
    }

    @Override
    public Date getStartDate() {
        return inicio;
    }

    @Override
    public Date getEndDate() {
        return fim;
    }

    @Override
    public boolean isAllDay() {
        return false;
    }

    @Override
    public String getStyleClass() {
        return null;
    }

    @Override
    public boolean isEditable() {
        return true;
    }

}
