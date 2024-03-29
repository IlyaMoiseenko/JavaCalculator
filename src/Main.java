import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();
        calc(exp);
    }

    public static String calc(String input) throws Exception {
        Converter converter = new Converter();
        String[] actions = Util.CreateActions();
        String[] regexActions = Util.CreateRegexActions();

        //Определяем арифметическое действие:
        int actionIndex = Util.ArithmeticAction(actions, input);

        //Если не нашли арифметического действия, завершаем программу
        if (actionIndex == -1){
            throw new Exception("Некорректное выражение");
        }

        //Делим строчку по найденному арифметическому знаку
        String[] data = input.split(regexActions[actionIndex]);

        if (Util.CheckNumberOfOperands(data))
            throw new Exception("Должно быть 2 операнда");

        int result = 0;

        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;

            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {
                //если римские, то конвертируем их в арабские
                //X+V
                //x-10-
                //v - 5

                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

                if (Util.CheckNumbers(a, b))
                    throw new Exception("Числа должны быть больше 1 и меньше 10");
            } else {
                //если арабские, конвертируем их из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);

                if (Util.CheckNumbers(a, b))
                    throw new Exception("Числа должны быть больше 1 и меньше 10");

            }

            //выполняем с числами арифметическое действие
            result = switch (actions[actionIndex]) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> a / b;
            };

            if (isRoman) {
                //если числа были римские, возвращаем результат в римском числе
                System.out.println(converter.intToRoman(result));
            }
            else {
                //если числа были арабские, возвращаем результат в арабском числе
                System.out.println(result);
            }
        } else {
            System.out.println("Числа должны быть в одном формате");
        }

        return String.valueOf(result);
    }
}