package regrasNegocio;
import entidades.Veiculo;
import entidades.Transferencia;
import armazenamento.ArmazenaVeiculo;
import armazenamento.ArmazenaTransferencia;
import java.util.List;

import java.io.IOException;
public class Consulta {
    private ArmazenaVeiculo veiculoArmazenado = new ArmazenaVeiculo();
    private ArmazenaTransferencia transferenciaArmazenada = new ArmazenaTransferencia();

    //consulta por placa
    public void consultarPorPlaca(String placa) throws IOException {
        List<Veiculo> veiculos = veiculoArmazenado.lerArquivo();
        boolean encontrado = false;

        //Percorre lista de veiculos
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo v = veiculos.get(i);
            //Se encontra a placa
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                System.out.println("Placa: " + v.getPlaca());
                System.out.println("Modelo: " + v.getModelo());
                System.out.println("Marca: " + v.getMarca());
                System.out.println("Ano: " + v.getAno());
                System.out.println("Cor: " + v.getCor());
                System.out.println("Proprietário (CPF): " + v.getCpfProprietario());
                encontrado = true;
                break;
            }
        }
        //Se não encontrar a placa
        if (!encontrado) {
            System.out.println("Veículo com a placa " + placa + " não encontrado.");
        }
    }




    //consulta por proprietário (mesmo modelo do anterior, só mudando os dados)
    public void consultarPorCpf(String cpf) throws IOException {
        List<Veiculo> veiculos = veiculoArmazenado.lerArquivo();
        boolean encontrou = false;

        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo v = veiculos.get(i);
            if (v.getCpfProprietario().equals(cpf)) {
                System.out.println("Placa: " + v.getPlaca() + " | Modelo: " + v.getModelo() + " | Marca: " + v.getMarca());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum veículo encontrado para o CPF " + cpf + ".");
        }
    }




    //consultar histórico da placa (mesmo modelo do anterior, só mudando os dados)
    public void consultarHistoricoTransferencias(String placa) throws IOException {
        List<Transferencia> historico = transferenciaArmazenada.lerArquivo();
        boolean encontrou = false;

        System.out.println("Histórico de transferências da placa " + placa + ":");

        for (int i = 0; i < historico.size(); i++) {
            Transferencia t = historico.get(i);
            if (t.getPlaca().equalsIgnoreCase(placa)) {
                System.out.println("De: " + t.getCpfAnterior() + " Para: " + t.getCpfNovo() + " | Data: " + t.getDataTransferencia());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma transferência registrada para a placa " + placa + ".");
        }
    }
}
