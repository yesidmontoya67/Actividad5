package co.edu.ff.orders;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Streams {

    public static void main(String[] args) {
        List<Pizza> pizzaList = Arrays.asList(
                new Pizza("Básica", Size.SMALL, 600),
                new Pizza("Familiar", Size.LARGE, 1800),
                new Pizza("Vegetariana", Size.LARGE, 860),
                new Pizza("Solo queso", Size.MEDIUM, 1000),
                new Pizza("Hawaiana", Size.SMALL, 1200),
                new Pizza("Extra carnes", Size.LARGE, 2100),
                new Pizza("Pollo", Size.SMALL, 900),
                new Pizza("Pollo + tocineta", Size.MEDIUM, 1500),
                new Pizza("Pollo + Jamon", Size.MEDIUM, 1300)
        );



        /*
         * 1. Obtener todas las pizzas de tamaño "MEDIUM"
         */
        pizzaList.stream().filter(pizza -> pizza.size.equals(Size.MEDIUM))
                .forEach(pizza -> System.out.println(pizza));

        System.out.println("-------------------------");

        /*
         * 2. Obtener todas las pizzas que las calorias esten entre 700 y 1500
         */
        pizzaList.stream().filter(pizza-> pizza.getCalories()>=700 && pizza.getCalories()<=1500)
                .forEach(pizza-> System.out.println(pizza));

        System.out.println("-------------------------");

        /*
         * 3. Obtener las 3 pizzas con más calorias
         */
        pizzaList.stream().sorted((p1,p2)->Integer.compare(p1.getCalories(),p2.getCalories()))
                .limit(3)
                .forEach(pizza-> System.out.println(pizza));

        System.out.println("-------------------------");

        /*
         * 4. Obtener las 2 pizzas con menos calorias
         */
        pizzaList.stream().sorted((p1,p2)->Integer.compare(p2.getCalories(),p1.getCalories()))
                .limit(3)
                .forEach(pizza-> System.out.println(pizza));

        System.out.println("-------------------------");


        /*
         * 5. Del numeral 2 obtener las 2 pizzas con mas calorias
         */
        pizzaList.stream().filter(pizza-> pizza.getCalories()>=700 && pizza.getCalories()<=1500)
                .sorted((p1,p2)->Integer.compare(p2.getCalories(),p1.getCalories()))
                .limit(2)
                .forEach(pizza-> System.out.println(pizza));

        System.out.println("-------------------------");

        /*
         * 5. Agrupar las pizzas por tamaño
         */

        Map<Integer, List<Pizza>> collect = pizzaList.stream()
                .collect(Collectors.groupingBy(pizza -> pizza.getCalories()));
        System.out.println(collect);

        System.out.println("-------------------------");

        /*
         * 6. Agrupar las pizzas por los siguientes grupos:
         * de 0 a 1000 calorias
         * de 1001 a 2000 calorias
         * de 2001 a 3000 calorias
         */
        Function<Pizza,String> funcion = new Function<Pizza, String>() {
            @Override
            public String apply(Pizza pizza) {
                if(pizza.getCalories() >0 && pizza.getCalories() <=1000){
                    return "0-1000";
                }else if(pizza.getCalories() >1001 && pizza.getCalories() <=2000){
                    return "1001-2000";
                }else if(pizza.getCalories() >1001 && pizza.getCalories() <=2000){
                    return "2001-3000";
                }else{
                    return "3000-infinite";
                }

            }
        };

        Map<String, List<Pizza>> collect1 = pizzaList.stream()
                .collect(Collectors.groupingBy(funcion));
        System.out.println(collect1);

        System.out.println("-------------------------");


    }

    public enum Size {
        SMALL,
        MEDIUM,
        LARGE
    }

    public static class Pizza {
        private final String name;
        private final Size size;
        private final Integer calories;

        public Pizza(String name, Size size, Integer calories) {
            this.name = name;
            this.size = size;
            this.calories = calories;
        }

        public Size getSize() {
            return size;
        }

        public String getName() {
            return name;
        }

        public Integer getCalories() {
            return calories;
        }

        @Override
        public String toString() {
            return String.format("Pizza{%s, %s, %s}", name, size, calories);
        }
    }
}
