package aulajpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@NamedQuery(name = Automovel.LISTAR_TODOS, query = "select a from Automovel a")
@Entity
public class Automovel implements Serializable {

    public static final String LISTAR_TODOS = "Automovel.listarTodos";

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String placa;
    @Column(nullable = false)
    private String modelo;
    @Column(nullable = false)
    private String marca;
    @Column(nullable = false)
    private Integer anoFabricacao;
    @Lob
    private String observacoes;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAtualizacao;
    @Transient
    private int anosDeUso;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Dono dono;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Acessorio> acessorios = new ArrayList<>();

    @Override
    public String toString() {
        return "Automovel{" + "modelo=" + modelo + ", marca="
                + marca + ", anoFabricacao=" + anoFabricacao
                + ", anos de uso=" + getAnosDeUso() + ", observacoes=" + observacoes + '}';
    }

    public List<Acessorio> getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(List<Acessorio> acessorios) {
        this.acessorios = acessorios;
    }

    
    
    public int getAnosDeUso() {
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        return anoAtual - anoFabricacao;
    }

    public void setAnosDeUso(int anosDeUso) {
        this.anosDeUso = anosDeUso;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Dono getDono() {
        return dono;
    }

    public void setDono(Dono dono) {
        this.dono = dono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

}
