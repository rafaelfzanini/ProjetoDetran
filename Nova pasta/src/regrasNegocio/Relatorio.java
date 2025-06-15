package regrasNegocio;
import armazenamento.ArmazenaVeiculo;
import armazenamento.ArmazenaTransferencia;
import entidades.Veiculo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;


public class Relatorio {
    ArmazenaVeiculo veiculoArmazenado = new ArmazenaVeiculo();
    ArmazenaTransferencia transferenciaArmazenada = new ArmazenaTransferencia();


    //relatório por marca
    public void relatorioPorMarca() throws IOException {
        List<Veiculo> veiculos = veiculoArmazenado.lerArquivo();
        List<String> marcasContadas = new ArrayList<>();
        System.out.println("Quantidade de veículos por marca:");

        //percorre lista de veículos
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo v = veiculos.get(i);
            String marcaAtual = v.getMarca();

            //Verifica se a marca já foi contada
            if (!marcasContadas.contains(marcaAtual)) {
                int contador = 0;
                //Percorre novamente contanto quantos são da mesma marca
                for (int j = 0; j < veiculos.size(); j++) {
                    if (veiculos.get(j).getMarca().equals(marcaAtual)) {
                        contador++;
                    }
                }
                System.out.println(marcaAtual + ": " + contador);
                marcasContadas.add(marcaAtual);
            }
        }
    }



   //relatório de transferencia por período
    public void relatorioTransferenciasPorPeriodo(LocalDate inicio, LocalDate fim) throws IOException {
        List<entidades.Transferencia> transferencias = transferenciaArmazenada.lerArquivo();
        System.out.println("Transferências realizadas entre " + inicio + " e " + fim + ":");
        int total = 0;

        //percorre todas transferências
        for (int i = 0; i < transferencias.size(); i++) {
            entidades.Transferencia t = transferencias.get(i);
            LocalDate data = t.getDataTransferencia();

            //verifica se a data está dentro do inetrvalo
            if ((data.isEqual(inicio) || data.isAfter(inicio)) && (data.isEqual(fim) || data.isBefore(fim))) {

                //exibe dados da transferencia
                System.out.println("Placa: " + t.getPlaca() + " | De: " + t.getCpfAnterior() + " | Para: " + t.getCpfNovo() + " | Data: " + t.getDataTransferencia());
                total++;
            }
        }
        //caso não tenha transferencia
        if (total == 0) {
            System.out.println("Nenhuma transferência registrada nesse período.");
        }
    }




    //Placas antigas
    public void relatorioPlacasAntigasNaoTransferidas() throws IOException {
        List<Veiculo> veiculos = veiculoArmazenado.lerArquivo();
        System.out.println("Veículos com placa antiga e sem transferências:");
        int total = 0;

        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo v = veiculos.get(i);

            // Verifica se a placa é antiga e se o veículo não possui transferências
            if (v.getPlaca().matches("[A-Z]{3}-?[0-9]{4}") && v.getTransferencias().isEmpty()) {
                System.out.println("Placa: " + v.getPlaca() + ", Marca: " + v.getMarca() + ", Modelo: " + v.getModelo() + ", CPF Proprietário: " + v.getCpfProprietario());
                total++;

            }
        }
        if (total == 0) {
            System.out.println("Nenhum veículo com placa antiga pendente de transferência.");
        }
    }
}
