package solid.lsp;

public abstract class OrderAbstract {
    protected int price;
    protected int qnt;


    public OrderAbstract( int qnt, int price) {
        this.qnt = qnt;
        this.price = price;
    }

    public  abstract int getAmount();
}
