package model.services;

import model.entities.Aluguel;
import model.entities.AluguelDeCarro;

import java.time.Duration;

public class AluguelServico {

    private Double precoPorHora;
    private Double precoPorDia;
    private TaxaDeServico taxaDeServico;

    public AluguelServico(Double precoPorHora, Double precoPorDia, TaxaDeServico taxaDeServico) {
        this.precoPorHora = precoPorHora;
        this.precoPorDia = precoPorDia;
        this.taxaDeServico = taxaDeServico;
    }

    public void processamentoAluguel(AluguelDeCarro aluguelDeCarro){
        double minutos = Duration.between(aluguelDeCarro.getInicio(), aluguelDeCarro.getFim()).toMinutes();
        double horas = minutos / 60.0;

        double pagamentoBasico;
        if (horas <= 12.0){
            pagamentoBasico = precoPorHora * Math.ceil(horas);
        }
        else {
            pagamentoBasico = precoPorDia * Math.ceil(horas / 24.0);
        }

        double taxa = taxaDeServico.taxa(pagamentoBasico);

        aluguelDeCarro.setAluguel(new Aluguel(pagamentoBasico, taxa));
    }
}
