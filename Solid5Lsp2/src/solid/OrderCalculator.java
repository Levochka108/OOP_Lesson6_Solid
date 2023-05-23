package solid;

import solid.lsp.Order;
import solid.lsp.OrderAbstract;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderCalculator implements Iterable<OrderAbstract> {
    private List<OrderAbstract> orders = new ArrayList<>();

    public void add(OrderAbstract order) {
        orders.add(order);
    }

    public int calcAmount() {
        int sum = 0;
        for (OrderAbstract order : orders) {
            sum += order.getAmount();
        }
        return sum;
    }

    @Override
    public Iterator<OrderAbstract> iterator() {
        return orders.iterator();
    }
}
