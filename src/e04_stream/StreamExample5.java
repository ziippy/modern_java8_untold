package e04_stream;

import lombok.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class StreamExample5 {
    public static void main(String[] args) {
        final List<Product> products = Arrays.asList(
                new Product(1L, "A", new BigDecimal("100.50")),
                new Product(2L, "B", new BigDecimal("23.00")),
                new Product(3L, "C", new BigDecimal("31.45")),
                new Product(4L, "D", new BigDecimal("80.20")),
                new Product(5L, "E", new BigDecimal("7.50"))
        );

        System.out.println("products.price >= 30 : \n" +
        products.stream()
                .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                .collect(toList())
        );

        System.out.println("products.price >= 30 (with joining) : \n" +
                products.stream()
                        .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                        .map(product -> product.toString())
                        .collect(joining("\n"))
        );

        System.out.println("products.price total price : \n" +
                products.stream()
                        .map(product -> product.getPrice())
                        .reduce(BigDecimal.ZERO, (p1, p2) -> p1.add(p2))
        );

        System.out.println("products.price total price >= 30 : \n" +
                products.stream()
                        .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                        .map(product -> product.getPrice())
                        .reduce(BigDecimal.ZERO, (p1, p2) -> p1.add(p2))
        );

        System.out.println("products.price total price case 2 >= 30 : \n" +
                products.stream()
                        .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                        .map(product -> product.getPrice())
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        final BigDecimal bigDecimal30 = new BigDecimal("30");
        System.out.println("products.price count : \n" +
                products.stream()
                        .filter(product -> product.getPrice().compareTo(bigDecimal30) >= 0)
                        .count()
        );

        final OrderedItem order1 = new OrderedItem(1L, products.get(0), 1);
        final OrderedItem order2 = new OrderedItem(2L, products.get(2), 3);
        final OrderedItem order3 = new OrderedItem(3L, products.get(4), 10);

        final List<OrderedItem> orderItems = Arrays.asList(order1, order2, order3);
        final Order order = new Order(1L, orderItems);

        System.out.println("order.totalPrice() : " + order.totalPrice());

        /** 출력
         * products.price >= 30 : 
         * [Product(id=1, name=A, price=100.50), Product(id=3, name=C, price=31.45), Product(id=4, name=D, price=80.20)]
         * products.price >= 30 (with joining) : 
         * Product(id=1, name=A, price=100.50)
         * Product(id=3, name=C, price=31.45)
         * Product(id=4, name=D, price=80.20)
         * products.price total price : 
         * 242.65
         * products.price total price >= 30 : 
         * 212.15
         * products.price total price case 2 >= 30 : 
         * 212.15
         * products.price count : 
         * 3
         * order.totalPrice() : 269.85
         */
    }
}

@Data
@AllArgsConstructor
class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}

@Data
@AllArgsConstructor
class OrderedItem {
    private Long id;
    private Product product;
    private int quantity;

    public BigDecimal getTotalPrice() {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }
}

@Data
@AllArgsConstructor
class Order {
    private Long id;
    private List<OrderedItem> items;

    public BigDecimal totalPrice() {
        return items.stream()
                .map(item -> item.getTotalPrice())
                .reduce(BigDecimal.ZERO, (i1, i2) -> i1.add(i2));
    }
}


