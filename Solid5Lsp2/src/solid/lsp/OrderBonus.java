package solid.lsp;

public class OrderBonus extends OrderAbstract{

    public OrderBonus(int qnt, int price) {
        super(price, qnt);
    }

    @Override
    public int getAmount() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("Количество = %d, Цена = %d", qnt, price);
    }
}
