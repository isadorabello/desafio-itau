package io.github.isadorabello.desafioitau.simple.dto;

import java.util.DoubleSummaryStatistics;

public class EstatisticasResponse {

    private long count;
    private double sum;
    private double avg;
    private double min;
    private double max;

    public EstatisticasResponse(DoubleSummaryStatistics estatisticas){
        this.count = estatisticas.getCount();
        this.sum = estatisticas.getSum();
        this.avg = estatisticas.getAverage();
        this.min = estatisticas.getMin();
        this.max = estatisticas.getMax();
    }

    public long getCount() {
        return count;
    }

    public double getSum() {
        return sum;
    }

    public double getAvg() {
        return avg;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
