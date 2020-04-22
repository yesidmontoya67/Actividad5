package co.edu.ff.orders.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TaxRateTest {
    @Test
    @DisplayName("No debería crear taza para casos inválidos")
    void isShouldNotPass()  {
        BigDecimal p1 = null;
        BigDecimal p2 = new BigDecimal("-1");
        BigDecimal p3 = new BigDecimal("1.1");

        assertAll(
                () -> assertThrows(NullPointerException.class, () -> TaxRate.of(p1)),
                () -> assertThrows(IllegalArgumentException.class, () -> TaxRate.of(p2)),
                () -> assertThrows(IllegalArgumentException.class, () -> TaxRate.of(p3))
        );
    }

    @Test
    @DisplayName("Debería crear tazas validas")
    void isShouldPass() {
        BigDecimal p1 = new BigDecimal("0");;
        BigDecimal p2 = new BigDecimal("0.7");

        assertAll(
                () -> assertDoesNotThrow(() -> TaxRate.of(p1)),
                () -> assertNotNull(TaxRate.of(p1).getValue()),
                () -> assertDoesNotThrow(() -> TaxRate.of(p2)),
                () -> assertNotNull(TaxRate.of(p2).getValue())
        );

    }


    @Test
    @DisplayName("retorna el mismo valor ingresado para taza")
    void valueOfSameValue() {
        BigDecimal taza = new BigDecimal("0.5");

        TaxRate tazaInstance = TaxRate.of(taza);
        String message = String.format("Se esperaba retornar el valor %s para una instancia creada con %s", taza, taza);
        assertEquals(tazaInstance.valueOf(), taza, message);
    }

}