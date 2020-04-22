package co.edu.ff.orders.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    @Test
    @DisplayName("No debería crear nombres para casos inválidos")
    void isShouldNotPass()  {
        String p1 = null;
        String p2 = "";
        String p3="a";
        int i = 101;
        for(int n=0; n<=i; n++){
            p3=p3+"a";
        }

        String finalP = p3;
        assertAll(
                () -> assertThrows(NullPointerException.class, () -> Name.of(p1)),
                () -> assertThrows(IllegalArgumentException.class, () -> Name.of(p2)),
                () -> assertThrows(IllegalArgumentException.class, () -> Name.of(finalP))
        );
    }

    @Test
    @DisplayName("Debería crear nombres validos")
    void isShouldPass() {
        String p1 = "nombre";

        assertAll(
                () -> assertDoesNotThrow(() -> Name.of(p1)),
                () -> assertNotNull(Name.of(p1).getValue())

        );


    }


    @Test
    @DisplayName("retorna el mismo valor ingresado para nombre")
    void valueOfSameValue() {
        String nombre = "nombre uno";

        Name nombreInstance = Name.of(nombre);
        String message = String.format("Se esperaba retornar el valor %s para una instancia creada con %s", nombre, nombre);
        assertEquals(nombreInstance.valueOf(), nombre, message);
    }

}