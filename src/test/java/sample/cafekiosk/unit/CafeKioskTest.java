package sample.cafekiosk.unit;

import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Beverage;
import sample.cafekiosk.unit.beverage.Latte;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CafeKioskTest {

    @Test
    void add() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano(), 1);

        assertThat(cafeKiosk.getBeverages()).hasSize(1);
        assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("Americano");
        assertThat(cafeKiosk.getBeverages().get(0).getPrice()).isEqualTo(4000);
    }

    @Test
    void addSeveralBeverages() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano, 2);

        assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
    }

    @Test
    void addZeroBeverages() {
        CafeKiosk cafeKiosk = new CafeKiosk();

        assertThatThrownBy(() -> cafeKiosk.add(new Americano(), 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void remove() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Beverage americano = new Americano();

        cafeKiosk.add(americano, 1);
        assertThat(cafeKiosk.getBeverages()).hasSize(1);

        cafeKiosk.remove(americano);
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

    @Test
    void clear() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Beverage americano = new Americano();
        Beverage latte = new Latte();

        cafeKiosk.add(americano, 1);
        cafeKiosk.add(latte, 1);
        assertThat(cafeKiosk.getBeverages()).hasSize(2);

        cafeKiosk.clear();
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

    @Test
    void createOrder() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Beverage americano = new Americano();
        Beverage latte = new Latte();

        cafeKiosk.add(americano, 1);
        cafeKiosk.add(latte, 1);

        Order order = cafeKiosk.createOrder(LocalDateTime.of(2025, 3, 3, 10, 0));

        assertThat(order.getBeverages().size()).isEqualTo(2);
        assertThat(order.getBeverages().get(0)).isEqualTo(americano);
        assertThat(order.getBeverages().get(1)).isEqualTo(latte);
    }

    @Test
    void createOrderWithNoBusinessHours() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Beverage americano = new Americano();
        Beverage latte = new Latte();

        cafeKiosk.add(americano, 1);
        cafeKiosk.add(latte, 1);

        assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2025, 3, 3, 9, 59)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
