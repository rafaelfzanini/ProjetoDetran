package armazenamento;
import entidades.Veiculo;
import java.time.LocalDate;
import java.util.*;
//arquivos
import java.nio.file.*;
import java.io.*;

public class ArmazenaVeiculo {
    //caminho do arquivo
    private final String caminho = "arquivosTexto/veiculos2.txt";
    //cria lista
    private List<Veiculo> lista = new ArrayList<>();



    //método getter que retorna a lista
    public List<Veiculo> getLista() {
        return lista;
    }



    //método que lê um arquivo e retorna uma lista
    public List<Veiculo> lerArquivo() throws IOException{
        lista.clear(); //limpar para não repetir dados anteriores

        //lê todas as linhas do arquivo e para cada linha executa:
        for(String linhaVeiculo : Files.readAllLines(Paths.get(caminho))){
            //divide a linha em um array de Strings
            String[] dados = linhaVeiculo.split(",");

            if(dados.length == 7) {
                Veiculo veiculo = new Veiculo(dados[0], dados[1], dados[2], Integer.parseInt(dados[3]), dados[4], dados[5], LocalDate.parse(dados[6]));
                lista.add(veiculo);
            }
        }
        return lista;
    }



    //método que salva a lista no arquivo
    public void salvarNoArquivo(List<Veiculo> lista) throws IOException {
        List<String> linhasFormatadas = new ArrayList<>();

        for (Veiculo veiculo : lista) {
            //formata os dados em uma única linha de texto
            String linhaVeiculo = veiculo.getPlaca() + "," + veiculo.getMarca() + "," + veiculo.getModelo() + "," + veiculo.getAno() + "," + veiculo.getCor() + "," + veiculo.getCpfProprietario() + "," + veiculo.getDataCadastro().toString();
            linhasFormatadas.add(linhaVeiculo);
        }

        //escreve linhas formatadas no arquivo
        Files.write(Paths.get(caminho), linhasFormatadas);
    }
}
