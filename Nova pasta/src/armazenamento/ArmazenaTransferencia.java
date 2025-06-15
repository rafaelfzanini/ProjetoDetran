package armazenamento;
import entidades.Transferencia;
import utilidades.Placa;
import java.time.LocalDate;
import java.util.*;
//arquivos
import java.nio.file.*;
import java.io.*;


public class ArmazenaTransferencia {
    //caminho do arquivo
    private final String caminho = "arquivosTexto/transferencias2.txt";
    //cria lista
    private List<Transferencia> lista = new ArrayList<>();


    //método getter que retorna a lista
    public List<Transferencia> getLista() {
        return lista;
    }


    //método que lê um arquivo e retorna uma lista
    public List<Transferencia> lerArquivo() throws IOException {
        lista.clear();

        //lê todas as linhas do arquivo e para cada linha executa:
        for (String linhaTransferencia : Files.readAllLines(Paths.get(caminho))) {
            //divide a linha em um array de Strings
            String[] dados = linhaTransferencia.split(",");

            if (dados.length == 4 && Placa.validarPlaca(dados[0])) {
                Transferencia transferencia = new Transferencia(dados[0], dados[1], dados[2], LocalDate.parse(dados[3]));
                lista.add(transferencia);
            }
        }
        return lista;
    }


    //método que salva a lista no arquivo
    public void salvarNoArquivo(Transferencia transferencia) throws IOException {
        String linha = transferencia.getPlaca() + "," + transferencia.getCpfAnterior() + "," + transferencia.getCpfNovo() + "," + transferencia.getDataTransferencia();

        //escreve linha no arquivo
        Files.write(Paths.get(caminho), List.of(linha), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}

