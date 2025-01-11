public class Main {
    public static void main(String[] args) {

        Calculator calculator = new Calculator();

        System.out.println("Сложение: " + calculator.sum(10 , 5));
        System.out.println("Умножение: " + calculator.multiply(10 , 5));
        System.out.println("Деление: " + calculator.divide(10 , 0));
        System.out.println("Вычитание: " + calculator.subtract(10 , 5));
    }
}
