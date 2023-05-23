package solid.lsp;

public class Order extends OrderAbstract {

    public Order( int qnt, int price) {
        super(price, qnt);
    }

    public int getAmount() {
        return qnt * price;
    }

    @Override
    public String toString() {
        return String.format("Количество = %d, Цена = %d", qnt, price);
    }
}
