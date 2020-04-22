package co.edu.ff.orders.product.domain;

import co.edu.ff.orders.user.domain.Password;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BasePriceTest {

    @Test
    @DisplayName("No debería crear precios para casos inválidos")
    void isShouldNotPass()  {
        BigDecimal p1 = null;
        BigDecimal p3 = new BigDecimal("-1");;

        assertAll(
                () -> assertThrows(NullPointerException.class, () -> BasePrice.of(p1)),
                () -> assertThrows(IllegalArgumentException.class, () -> BasePrice.of(p3))
        );
    }

    @Test
    @DisplayName("Debería crear precios validas")
    void isShouldPass() {
        BigDecimal p1 = new BigDecimal("0");;
        BigDecimal p2 = new BigDecimal("12345");

        assertAll(
               () -> assertDoesNotThrow(() -> BasePrice.of(p1)),
                () -> assertNotNull(BasePrice.of(p1).getValue()),
                () -> assertDoesNotThrow(() -> BasePrice.of(p2)),
                () -> assertNotNull(BasePrice.of(p2).getValue())
        );


    }


    @Test
    @DisplayName("retorna el mismo valor ingresado para precio")
    void valueOfSameValue() {
        BigDecimal price = new BigDecimal("323455");

        BasePrice priceInstance = BasePrice.of(price);
        String message = String.format("Se esperaba retornar el valor %s para una instancia creada con %s", price, price);
        assertEquals(priceInstance.valueOf(), price, message);
    }


}