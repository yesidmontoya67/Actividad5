package co.edu.ff.orders.user.domain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    @DisplayName("No debería crear contraseñas para casos inválidos")
    void isShouldNotPass() {
        String p1 = null;
        String p2 = "";
        String p3 = "1234567";

        assertAll(
                () -> assertThrows(NullPointerException.class, () -> Password.of(p1)),
                () -> assertThrows(IllegalArgumentException.class, () -> Password.of(p2)),
                () -> assertThrows(IllegalArgumentException.class, () -> Password.of(p3))
        );
    }

    @TestFactory
    @DisplayName("Debería crear contraseñas validas")
    Stream<DynamicTest> isShouldPass() {
        return Stream.of(
                "password-1234",
                "password!",
                "-----11Password"
        )
                .map(password -> {
                    String testName = String.format("debería ser valido para el password: %s", password);
                    Executable executable = () -> {
                        // organizar

                        // actuar
                        ThrowingSupplier<Password> passwordThrowingSupplier = () -> Password.of(password);
                        // comprobar
                        assertAll(
                                () -> assertDoesNotThrow(passwordThrowingSupplier),
                                () -> assertNotNull(passwordThrowingSupplier.get())
                        );
                    };
                    return DynamicTest.dynamicTest(testName, executable);
                });
    }


    @Test
   // @Disabled("For demostration")
    @DisplayName("valueOf retorna el mismo valor ingresado para contraseña")
    void valueOfSameValue() {
        String password = "password-1234";

        Password passwordInstance = Password.of(password);
        String message = String.format("Se esperaba retornar el valor %s para una instancia creada con %s", password, password);
        assertEquals(passwordInstance.valueOf(), password, message);
    }


}