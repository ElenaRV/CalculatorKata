import java.util.Scanner;

public class Main { // a+b=c (+, -, *, /)/


    // нужно отделить операнды и действие(оператор)
    static int operand1;    // число1 от пользователя
    static int operand2;     // число2 от пользователя
    static int result;      // результат = operand1 operator operand2
    static boolean isArab = true;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("Введите выражение: ");
            String userInput = scan.nextLine();                // ввод выражения пользователем
            calc(userInput.toUpperCase());                                    // вызываем метод кальк, когда польз вводит выражение
            if (operand1 <= 0 || operand1 > 10 || operand2 <=0 || operand2 > 10){
                throw new RuntimeException("Калькулятор принимает Римские или Арабские числа от 1 до 10 включительно");
            }
            System.out.println(isArab  ? String.valueOf(result) : rome(result));
        }
    }

    public static String calc(String input) {
        int operatorIndex = -1;
        String[] operator = {"+", "-", "/", "*"};          // знак действия
        String[] regexOperator = {"\\+", "-", "/", "\\*"};  // зачем-то экранируем плюс и умножить, зачем пока не знаю, но так надо
        for (int i = 0; i < operator.length; i++) {
            if (input.contains(operator[i])) {          // если в веденном пользователем выражении будет найден символ из массива оператор,
                operatorIndex = i;                         // тогда операторИндекс изменит значение из -1 на индекс того или иного символа
                break;                                     // и выйдет из цикла
            }
        }
        if (operatorIndex == -1) {                          // если индекс меньше 0, значит пользователь ввел не один из операторов
            throw new RuntimeException("Не корректное выражение");                                    //
        }

        String[] element = input.split(regexOperator[operatorIndex]);    // делим методом сплит  строку введенную пользователем на два элемента по индексу найденому в массиве регексОператор
        if (isRoman(element[0]) && isRoman(element[1])){
            operand1 = romanToInt(element[0]);
            operand2 = romanToInt(element[1]);
            System.out.println("Введены Римские числа");
            isArab = false;

        } else {
            operand1 = Integer.parseInt(element[0]);            // присваиваю операнду1 число под индексом 0 без уч регистра
            operand2 = Integer.parseInt(element[1]);             // присваиваю операнду2 число под индексом 2 без уч регистра
            System.out.println("Введены Арабские числа");
        }
        switch (operator[operatorIndex]) {
            case "+" -> result = operand1 + operand2;
            case "-" -> result = operand1 - operand2;
            case "/" -> result = operand1 / operand2;
            case "*" -> result = operand1 * operand2;
        }
        return  String.valueOf(result);               // конвертируем обратно число в строку;
    }


    public static int getArabian(char roman){
        if ('I' == roman) return 1;
        else if ('V' == roman) return 5;
        else if ('X' == roman) return 10;
        else if ('L' == roman) return 50;
        else if ('C' == roman) return 100;
        return 0;
    }

    public static int romanToInt(String romanString){      // римское число переводим в инт
        int temporary = romanString.length()-1; // 1. помещает в инт количество символов введенных в строку XXXIV = 4-1=3
        char[] romeArray = romanString.toCharArray(); // 2. разбиваем строку XXXIV на массив из символов = [X, X, X, I, V]
        int result = getArabian(romeArray[temporary]); // 3. смотрим последний символ V под индексом 4, конвертируем в методе getArabian есть ли такой и какое значение,  = значение 5
        int arab;                                    // 5.  перем для хранения врем значения символа // 0 -> 4
        for (int i = temporary-1; i >=0 ; i-- ){         // 4. начинаем цикл со следующего числа (4-1) = индекс 3 (цикл будет идти до 0)
            arab = getArabian(romeArray[i]);         // 6. если ТРУ, то arab = I = 1 и переходим к //7.

            if (arab < getArabian(romeArray[i+1])){                      // 7. если 1 < 5, 5-1, и переход к //8
                result -= arab;                       //8. result = 5-1 = 4
            }else {
                result += arab;                       // 8 иначе result = 5+1
            }
        }

        return result;
    }
    public static boolean isRoman (String operand){
        switch (operand){
            case "I":
            case "II":
            case "III":
            case "IV":
            case "V":
            case "VI":
            case "VII":
            case "VIII":
            case "IX":
            case "X":
                return true;
            default: return false;
        }
    }

    public static String rome(int res) {
        if (res == 0){
            throw new RuntimeException("Ошибка! Результатом работы калькулятора с римскими числами могут быть только положительные числа.");
        }
        String[] roman = {"N", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        return roman[res];
    }
}