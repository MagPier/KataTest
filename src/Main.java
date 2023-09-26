import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        //Считываем инпут
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        char operator;
        //Проверяем регулярным выражением есть ли подходящие математические операторы и ровно ли 2 слагаемых и 1 оператор.
        String[] parts = input.split("[+\\-*/]");
        if (parts.length != 2) throw new Exception("Строка не является математической");
        //Присваеваем соответствующий оператор переменной
        if (input.contains("+")) {
            operator = '+';
        } else if (input.contains("-")) {
            operator = '-';
        } else if (input.contains("*")) {
            operator = '*';
        } else if (input.contains("/")) {
            operator = '/';
        } else throw new Exception("Не верный оператор");
        //Проверяем оба слагаемых на предмет соответствия римским/арабским цифрам
        if (checkArabian(parts[0]) && checkArabian(parts[1])) {
            //По условиям задания дополнительно сравниваем арабские цифры больше 1 и меньше 10
            if (Integer.parseInt(parts[0]) > 10 || Integer.parseInt(parts[0]) > 10 || Integer.parseInt(parts[0]) < 1 || Integer.parseInt(parts[0]) < 1)
                throw new Exception("Арабское число больше 10 или меньше 1");
            System.out.println("Результат для арабских цифр " + calculating(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), operator));
            //Для римских чисел преобразуем из в арабские, выполняем операцию и преобразуем обратно
        } else if (checkRoman(parts[0].toUpperCase()) && checkRoman(parts[1].toUpperCase())) {
            System.out.println("Результат для римских цифр " + getRoman(calculating(romanToInt(parts[0].toUpperCase()), romanToInt(parts[1].toUpperCase()), operator)));
        } else throw new Exception("Разные форматы данных");

    }

    //Метод помогающий в преобразовании арабских чисел в римские.
    private static int getArabian(char roman) {
        if ('I' == roman) return 1;
        else if ('V' == roman) return 5;
        else if ('X' == roman) return 10;
        return 0;
    }

//Метод непосредственно преобразующий римские числа в арабские проходя массив символов справа на лево.
    private static int romanToInt(String s) {
        int end = s.length() - 1;
        char[] arr = s.toCharArray();
        int arabian;
        int result = getArabian(arr[end]);
        for (int i = end - 1; i >= 0; i--) {
            arabian = getArabian(arr[i]);
            if (arabian < getArabian(arr[i + 1])) {
                result -= arabian;
            } else {
                result += arabian;
            }
        }
        return result;
    }

    //Метод для обратного преобразования римских в арабские, построен на конкатенации строк.
    // Подходит для чисел до 100, т.к по условиям задачи произведение двух чисел не более 10 каждое не может дать более 100 в ответе.
    private static String getRoman(int arabian) throws Exception {
        String result = "";
        if (arabian < 0) throw new Exception("Римское число ниже нуля");
        while (arabian != 0) {
            if (arabian == 100) {
                result = result.concat("C");
                break;
            } else if (arabian / 50 >= 1) {
                result = result.concat("L");
                arabian = arabian - 50;
            } else if (arabian / 40 >= 1) {
                result = result.concat("XL");
                arabian = arabian - 40;
            } else if (arabian / 10 >= 1) {
                result = result.concat("X");
                arabian = arabian - 10;
            } else if (arabian / 9 >= 1) {
                result = result.concat("IX");
                arabian = arabian - 9;
            } else if (arabian / 5 >= 1) {
                result = result.concat("V");
                arabian = arabian - 5;
            } else if (arabian / 4 >= 1) {
                result = result.concat("IV");
                arabian = arabian - 4;
            } else if (arabian >= 1) {
                result = result.concat("I");
                arabian = arabian - 1;
            }
        }
        return result;
    }
    // Метод для проверки римских символов. Подходит до 10 включительно по условию задачи.
    private static boolean checkRoman(String roman) {
        if (roman == null) {
            return false;
        }
        for (int i = 0; i < roman.length(); i++) {
            char c = roman.charAt(i);
            if (c == 'I' || c == '0' || c == 'X' || c == 'V') {
                return true;
            }
        }
        return false;
    }
    // Метод для проверки арабских символов, что число состоит только из цифр.
    private static boolean checkArabian(String arabian) {
        if (arabian == null) {
            return false;
        }

        for (int i = 0; i < arabian.length(); i++) {
            char c = arabian.charAt(i);
            if (c >= '0' && c <= '9') {
                return true;
            }
        }
        return false;
    }
    //Метод непосредственно выполняющий арифметические операции в зависимости от значения оператора.
    private static int calculating(int number1, int number2, char operator) {
        int result = switch (operator) {
            case '+' -> number1 + number2;
            case '-' -> number1 - number2;
            case '*' -> number1 * number2;
            case '/' -> number1 / number2;
            default -> 0;
        };
        return result;
    }

}