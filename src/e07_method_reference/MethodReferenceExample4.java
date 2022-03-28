package e07_method_reference;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.function.Function;

public class MethodReferenceExample4 {
    public static void main(String[] args) {
        /*
         * 생성자도 사용해보자.
         * Array Constructor 도 해보자.
         */
        final Section section = new Section(1);

        final Function<Integer, Section> sectionFactory = i -> new Section(i);
        final Section section1 = sectionFactory.apply(2);
        System.out.println(section);
        System.out.println(section1);

        final Function<Integer, Section> sectionFactory2 = Section::new;
        System.out.println(sectionFactory2.apply(3));

        final Product2 product = new Product2(1L, "A", new BigDecimal("100"));
        System.out.println(product);

        final Product2Creator product2Creator = Product2::new;
        System.out.println(
                product2Creator.create(1L, "A", new BigDecimal("100"))
        );

        ///////////////////////////////////////
        final ProductA a = createProduct3(1L, "A", new BigDecimal("123"), ProductA::new);
        final ProductB b = createProduct3(2L, "B", new BigDecimal("456"), ProductB::new);
        System.out.println(a);
        System.out.println(b);

        /** 출력
         * Section(number=1)
         * Section(number=2)
         * Section(number=3)
         * Product2(id=1, name=A, price=100)
         * Product2(id=1, name=A, price=100)
         * ProductA - Product3(id=1, name=A, price=123)
         * ProductB - Product3(id=2, name=B, price=456)
         */
    }

    // Product3 을 상속받은 ProductA 와 ProductB 중 어떤 것을 생성할 지 모르므로.. 일단 generic 하게 만드는 함수
    private static <T extends Product3> T createProduct3(final Long id,
                                                  final String name,
                                                  final BigDecimal price,
                                                  final Product3Creator<T> product3Creator) {
        if (id == null || id < 1L) {
            throw new IllegalArgumentException("Invalid Argument of Id");
        }

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid Argument of Name");
        }

        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid Argument of Price");
        }

        return product3Creator.create(id, name, price);
    }
}

@FunctionalInterface
interface Product2Creator {
    Product2 create(Long id, String name, BigDecimal price);
}

@FunctionalInterface
interface Product3Creator<T extends Product3> {
    T create(Long id, String name, BigDecimal price);
}

@AllArgsConstructor
@Data
class Section {
    private int number;
}

@AllArgsConstructor
@Data
class Product2 {
    private Long id;
    private String name;
    private BigDecimal price;
}

@AllArgsConstructor
@Data
abstract class Product3 {
    private Long id;
    private String name;
    private BigDecimal price;
}

class ProductA extends Product3 {
    public ProductA(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "ProductA - " + super.toString();
    }
}

class ProductB extends Product3 {
    public ProductB(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "ProductB - " + super.toString();
    }
}
