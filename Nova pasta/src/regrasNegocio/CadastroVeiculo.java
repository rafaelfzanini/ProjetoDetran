package regrasNegocio;
import entidades.Veiculo;
import armazenamento.ArmazenaVeiculo;
import utilidades.Placa;
import java.time.LocalDate;
import java.util.List;
//arquivo
import java.io.IOException;


public class CadastroVeiculo {
    ArmazenaVeiculo veiculoArmazenado = new ArmazenaVeiculo();

    public void cadastrar(String placaEntrada, String marca, String modelo, int ano, String cor, String cpf) throws IOException {
        List<Veiculo> veiculos = veiculoArmazenado.lerArquivo();
        String placaFinal;


        //Erro de placa inválida.
        if (placaEntrada == null || placaEntrada.isBlank()) {               
            placaFinal = Placa.gerarPlacaMercosul();                         
        }
        else {
            placaFinal = Placa.formatarPlaca(placaEntrada);

            if (!placaEntrada.replace("-", "").equalsIgnoreCase(placaFinal)) { // <-- MANTIDO: valida formato da placa
                System.out.println("Aviso: A placa informada foi corrigida para o padrão válido: " + placaFinal); // <-- TEXTO AJUSTADO
            }
        }

        //verificar se placa já está cadstrado
        boolean placaExiste = false;
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo veiculo = veiculos.get(i);
            if (veiculo.getPlaca().equalsIgnoreCase(placaFinal)) {
                placaExiste = true;
                break;
            }
        }

        //erro de veículo já cadastrado
        if (placaExiste){
            System.out.println("ERRO: Já existe um veículo cadastrado com a placa " + placaFinal + ".");
        }
        else{
            Veiculo novo = new Veiculo(placaFinal, marca, modelo, ano, cor, cpf, LocalDate.now());
            veiculos.add(novo);
            veiculoArmazenado.salvarNoArquivo(veiculos);
        }
    }
}
