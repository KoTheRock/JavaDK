import static java.lang.Double.sum;

public class Main {

    public static void main(String[] args) {

        System.out.println("Сложение: " + sum(10, 20.5));
        System.out.println("Умножение: " + Calculator.multiply(5, 2.2));
        System.out.println("Деление: " + Calculator.divide(15, 3));
        System.out.println("Вычитание: " + Calculator.subtract(10, 4));

        try {
            System.out.println("Деление на 0: " + Calculator.divide(10, 0));
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: " + e.getMessage());
    }
}

    public class Calculator {

        public static <T extends Number, U extends Number> double sum(T a, U b) {
            return a.doubleValue() + b.doubleValue();
        }

        public static <T extends Number, U extends Number> double multiply(T a, U b) {
            return a.doubleValue() * b.doubleValue();
        }

        public static <T extends Number, U extends Number> double  divide(T a, U b) {
            if (b.doubleValue() == 0) {
                throw new ArithmeticException("На ноль делишь, друг");
            }
            return a.doubleValue() / b.doubleValue();
        }

        public static <T extends Number, U extends Number> double  subtract(T a, U b) {
            return a.doubleValue() - b.doubleValue();
        }
    }



}
