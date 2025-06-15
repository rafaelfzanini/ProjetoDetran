import regrasNegocio.Relatorio;
import regrasNegocio.CadastroVeiculo;
import regrasNegocio.Consulta;
import regrasNegocio.GerenciadorTransferencia;
import utilidades.Placa;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        //instânciando
        CadastroVeiculo cadastro = new CadastroVeiculo();
        GerenciadorTransferencia transferecia = new GerenciadorTransferencia();
        Consulta consulta = new Consulta();
        Relatorio relatorio = new Relatorio();

        //menu
        int opcao;
        System.out.println();

        do{
            System.out.println("==== MENU ====");
            System.out.println("1 - Cadastrar Veículo");
            System.out.println("2 - Transferir Propriedade");
            System.out.println("3 - Consultar por Placa");
            System.out.println("4 - Consultar por CPF");
            System.out.println("5 - Consultar Histórico de Transferências de um Veículo pela placa");
            System.out.println("6 - Relatório: Veículos por Marca");
            System.out.println("7 - Relatório: Transferências por Período");
            System.out.println("8 - Relatório: Veículos com Placa Antiga (não transferidos)");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();
            System.out.println();



            //opções
            switch (opcao){
                case 1: {
                    System.out.println();
                    System.out.print("Placa (deixe em branco para gerar): ");
                    String placa = sc.nextLine();
                    if (placa.isBlank()) placa = null;

                    System.out.print("Marca: ");
                    String marca = sc.nextLine();

                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();

                    System.out.print("Ano: ");
                    int ano = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Cor: ");
                    String cor = sc.nextLine();

                    System.out.print("CPF do proprietário: ");
                    String cpf = sc.nextLine();

                    cadastro.cadastrar(placa, marca, modelo, ano, cor, cpf);
                    break;
                }


                case 2: {
                    System.out.print("Placa do veículo: ");
                    String placa = sc.nextLine();

                    System.out.print("CPF do novo proprietário: ");
                    String novoCpf = sc.nextLine();

                    transferecia.transferirVeiculo(placa, novoCpf);
                    break;
                }


                case 3: {
                    System.out.print("Informe a placa do veículo: ");
                    String placaConsulta = sc.nextLine();
                    consulta.consultarPorPlaca(placaConsulta);
                    break;
                }


                case 4: {
                    System.out.print("Informe o CPF do proprietário: ");
                    String cpfConsulta = sc.nextLine();
                    consulta.consultarPorCpf(cpfConsulta);
                    break;
                }


                case 5: {
                    System.out.print("Informe a placa do veículo: ");
                    String placaTransferencia = sc.nextLine();
                    consulta.consultarHistoricoTransferencias(placaTransferencia);
                    break;
                }


                case 6:{
                    System.out.println();
                    relatorio.relatorioPorMarca();
                    break;
                }


                case 7:{
                    System.out.print("Data de início (AAAA-MM-DD): ");
                    String inicioTexto = sc.nextLine();
                    //convertendo
                    LocalDate inicio = LocalDate.parse(inicioTexto);

                    System.out.print("Data de fim (AAAA-MM-DD): ");
                    String fimStr = sc.nextLine();
                    LocalDate fim = LocalDate.parse(fimStr);

                    relatorio.relatorioTransferenciasPorPeriodo(inicio, fim);
                    break;
                }


                case 8:{
                    relatorio.relatorioPlacasAntigasNaoTransferidas();
                    break;
                }


                case 0: {
                    System.out.println("Encerrando o sistema.");
                    break;
                }

                default:
                    System.out.println("Opção inválida.");
            }

        }while (opcao != 0);
    }
}