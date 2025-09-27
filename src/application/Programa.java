package application;

import model.entities.AluguelDeCarro;
import model.entities.Veiculo;
import model.services.AluguelServico;
import model.services.TaxaDeServico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formato = DateTimeFormatter
                .ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Entre com os dados do aluguel");
        System.out.print("Modelo do carro: ");
        String modeloCarro = sc.nextLine();
        System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
        LocalDateTime inicio = LocalDateTime.parse(sc.nextLine(), formato);
        System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
        LocalDateTime fim = LocalDateTime.parse(sc.nextLine(), formato);

        AluguelDeCarro adc = new AluguelDeCarro(inicio, fim, new Veiculo(modeloCarro));

        System.out.print("Entre com o preço por hora: ");
        double precoPorHora = sc.nextDouble();
        System.out.print("Entre com o preço por dia: ");
        double precoPorDia = sc.nextDouble();

        AluguelServico aluguelServico = new AluguelServico(precoPorHora, precoPorDia, new TaxaDeServico());
        aluguelServico.processamentoAluguel(adc);

        System.out.println("FATURA:");
        System.out.println("Pagamento básico: " + adc.getAluguel().getPagamentoBasico());
        System.out.println("Imposto: " + adc.getAluguel().getTaxa());
        System.out.println("Pagamento total: " + adc.getAluguel().getPagamentoTotal());














        sc.close();
    }
}
