package solid.isp;

public interface PayableTerminal {
    void payWebMoney(int amount);
    void payCreditCard(int amount);
}
