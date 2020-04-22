package co.edu.ff.orders.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {

    @Test
    @DisplayName("No debería crear descirpcion para casos inválidos")
    void isShouldNotPass()  {
        String p1 = null;
        String p2 = "";
        String p3="a";
        int i = 281;
        for(int n=0; n<=i; n++){
            p3=p3+"a";
        }

        String finalP = p3;
        assertAll(
                () -> assertThrows(NullPointerException.class, () -> Description.of(p1)),
                () -> assertThrows(IllegalArgumentException.class, () -> Description.of(p2)),
                () -> assertThrows(IllegalArgumentException.class, () -> Description.of(finalP))
        );
    }

    @Test
    @DisplayName("Debería crear descripciones validas")
    void isShouldPass() {
        String p1 = "Descripcion 1";

        assertAll(
                () -> assertDoesNotThrow(() -> Description.of(p1)),
                () -> assertNotNull(Description.of(p1).getValue())

        );


    }


    @Test
    @DisplayName("retorna el mismo valor ingresado para descripcion")
    void valueOfSameValue() {
        String descripcion = "descripcion para producto";


        Description descripcionInstance = Description.of(descripcion);
        String message = String.format("Se esperaba retornar el valor %s para una instancia creada con %s", descripcion, descripcion);
        assertEquals(descripcionInstance.valueOf(), descripcion, message);
    }

}