package regrasNegocio;
import entidades.Veiculo;
import entidades.Transferencia;
import armazenamento.ArmazenaVeiculo;
import armazenamento.ArmazenaTransferencia;
import utilidades.Placa;
import utilidades.CPF;

import java.time.LocalDate;
import java.util.List;
import java.io.IOException;

public class GerenciadorTransferencia {
    ArmazenaVeiculo veiculoArmazenado = new ArmazenaVeiculo();
    ArmazenaTransferencia transferenciaArmazenada = new ArmazenaTransferencia();

    public void transferirVeiculo(String placaEntrada, String novoCpf) throws IOException {
        List<Veiculo> veiculos = veiculoArmazenado.lerArquivo();

        // Validar CPF
        if (!CPF.validarCPF(novoCpf)) {
            System.out.println("Erro: CPF inválido.");
            return;
        }

        // Formatar placa
        String placaFinal = Placa.formatarPlaca(placaEntrada);
        boolean veiculoEncontrado = false;

        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo veiculo = veiculos.get(i);
            if (veiculo.getPlaca().equalsIgnoreCase(placaFinal)) {
                veiculoEncontrado = true;

                //verificar se o novo proprietario tem CPF diferente
                if (veiculo.getCpfProprietario().equals(novoCpf)) {
                    System.out.println("Erro: O novo proprietário deve ser diferente do atual.");
                    return;
                }

                // Converter placa se for padrão antigo
                if (placaEntrada.matches("[A-Z]{3}-?[0-9]{4}")) {
                    placaFinal = Placa.converterPlaca(placaEntrada);
                    veiculo.setPlaca(placaFinal);
                }
                // Criar transferência
                Transferencia nova = new Transferencia(placaFinal, veiculo.getCpfProprietario(), novoCpf, LocalDate.now());

                // Atualizar CPF do proprietário
                veiculo.setCpfProprietario(novoCpf);


                // Adicionar transferência e salvar
                veiculo.getTransferencias().add(nova);
                transferenciaArmazenada.salvarNoArquivo(nova);
                break;
            }
        }

        // Salvar veículos atualizados ou exibir erro
        if (veiculoEncontrado) {
            veiculoArmazenado.salvarNoArquivo(veiculos);
            System.out.println("Transferência realizada com sucesso para a placa " + placaFinal + ".");
        } else {
            System.out.println("Erro: Veículo com a placa " + placaFinal + " não encontrado.");
        }
    }
}
