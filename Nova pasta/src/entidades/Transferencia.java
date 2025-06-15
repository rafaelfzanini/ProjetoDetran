package entidades;
import java.time.LocalDate;

public class Transferencia {
    private String placa;
    private String cpfAnterior;
    private String cpfNovo;
    private LocalDate dataTransferencia;


    //construtor
    public Transferencia(String placa, String cpfAnterior, String cpfNovo, LocalDate dataTransferencia) {
        this.placa = placa;
        this.cpfAnterior = cpfAnterior;
        this.cpfNovo = cpfNovo;
        this.dataTransferencia = dataTransferencia;
    }



    //getters
    public String getPlaca(){
        return placa;
    }
    public String getCpfAnterior(){
        return cpfAnterior;
    }
    public String getCpfNovo(){
        return cpfNovo;
    }
    public LocalDate getDataTransferencia(){
        return dataTransferencia;
    }
}
