package solid;

import solid.dip.PrintReport;
import solid.dip.Report;
import solid.dip.ToPrint;

public class Main {
    public static void main(String[] args) {

        Report report = new Report();
        report.calculate();
        ToPrint print = new PrintReport();
        report.output(print);
    }
}
