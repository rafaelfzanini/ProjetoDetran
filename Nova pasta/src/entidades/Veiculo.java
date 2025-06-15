package entidades;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int ano;
    private String cor;
    private String cpfProprietario;
    private LocalDate dataCadastro;
    private List<Transferencia> transferencias;


    //construtor
    public Veiculo(String placa, String marca, String modelo, int ano, String cor, String cpfProprietario, LocalDate dataCadastro){
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.cpfProprietario = cpfProprietario;
        this.dataCadastro = dataCadastro;
        this.transferencias = new ArrayList<>();

    }


    //getters
    public String getPlaca(){
        return placa;
    }
    public String getMarca(){
        return marca;
    }
    public String getModelo(){
        return modelo;
    }
    public int getAno(){
        return ano;
    }
    public String getCor(){
        return cor;
    }
    public String getCpfProprietario(){
        return cpfProprietario;
    }
    public LocalDate getDataCadastro(){
        return dataCadastro;
    }
    public List<Transferencia> getTransferencias() {
        return transferencias;
    }


    //setters
    public void setCpfProprietario(String cpfProprietario){
        this.cpfProprietario = cpfProprietario;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
