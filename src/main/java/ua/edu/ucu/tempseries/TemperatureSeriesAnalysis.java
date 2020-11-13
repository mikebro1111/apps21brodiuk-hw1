package ua.edu.ucu.tempseries;

import java.lang.Math;
import java.util.InputMismatchException;


public class TemperatureSeriesAnalysis {

    private double[] temperatureSeries;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries;
        for (double temp : temperatureSeries) {
            if (temp < -273) {
                throw new InputMismatchException();
            }
        }

    }

    public double average() {
        int size = temperatureSeries.length;
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double sum = 0;
        for (double temp : temperatureSeries) {
            sum += temp;
        }
        return sum / size;
    }

    public double deviation() {
        int size = temperatureSeries.length;
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double Sumsum = 0;
        for (double temp : temperatureSeries) {
            Sumsum += (average() - temp) * (average() - temp);
        }
        if (size == 1)
            return Math.sqrt(Sumsum);
        return Math.sqrt(Sumsum / (size - 1));

    }

    public double min() {
        int size = temperatureSeries.length;
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double minTemp = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (minTemp > temp) {
                minTemp = temp;
            }
        }
        return minTemp;
    }

    public double max() {
        int size = temperatureSeries.length;
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double maxTemp = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (maxTemp < temp) {
                maxTemp = temp;
            }
        }
        return maxTemp;
    }

    public double findTempClosestToZero() {
        int size = temperatureSeries.length;
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double closest = Math.abs(temperatureSeries[0]);
        for (int i = 1; i < size; i++) {
            if (closest > temperatureSeries[i]) {
                closest = Math.abs(temperatureSeries[i]);
            }
        }
        return closest;

    }

    public double findTempClosestToValue(double tempValue) {
        int size = temperatureSeries.length;
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double closest = temperatureSeries[0] - tempValue;
        for (int i = 1; i < size; i++) {
            if (Math.abs(closest) > Math.abs(temperatureSeries[i] - tempValue)) {
                closest = temperatureSeries[i] - tempValue;
            } else if (Math.abs(closest - tempValue + temperatureSeries[i]) < 0.000001) {
                closest = Math.abs(closest);
            }
        }
        return closest + tempValue;
    }

    public double[] findTempsLessThen(double tempValue) {
        int size = temperatureSeries.length;
        double[] lessTemp = new double[size];
        int i = 0;
        for (double temp : temperatureSeries) {
            if (temp < tempValue) {
                lessTemp[i++] = temp;
            }
        }
        double[] resultTemp = new double[i];
        System.arraycopy(lessTemp, 0, resultTemp, 0, i);
        return resultTemp;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        int size = temperatureSeries.length;
        double[] greaterTemp = new double[size];
        int i = 0;
        for (double temp : temperatureSeries) {
            if (temp >= tempValue) {
                greaterTemp[i++] = temp;
            }
        }
        double[] resultTemp = new double[i];
        System.arraycopy(greaterTemp, 0, resultTemp, 0, i);
        return resultTemp;

    }

    public TempSummaryStatistics summaryStatistics() {
        int size = temperatureSeries.length;
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        return new TempSummaryStatistics(
                this.average(), this.deviation(), this.min(), this.max());

    }

    public int addTemps(double... temps) {
        double[] resultTemps = new double[Math.max(2 * temperatureSeries.length,
                temperatureSeries.length + temps.length)];
        System.arraycopy(temperatureSeries, 0, resultTemps, 0, temperatureSeries.length);
        System.arraycopy(temps, 0, resultTemps, temperatureSeries.length, temps.length);
        return temperatureSeries.length + temps.length;
    }

}
