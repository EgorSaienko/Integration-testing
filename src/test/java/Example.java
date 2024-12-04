/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Oksana
 */
public class Example {
    int number =42;
    /**
     * Це приклад javadoc до методу,
     * який повертає 1 ділену на число.
     * Коментар містить інформацію про 
     * <ol>
     * <li>Класах та інтерфейсах</li>
     * <li>Методах</li>
     * <li>Полях</li>
     * </ol>
     * Може містити посилання:
     * {@link Example#number},
     * а також теги:
     * @author Vlad Piatachenko <vl.piatachenko@cs.sumdu.edu.ua>
     * @param x число, яке необхідно обернути
     * @return число, обернене до x
     * @throws ArithmeticException якщо x дорівнює 0
     * @see java.lang.Double
     * @deprecated 
     */
    public double reverse(double x){
        return 1/x;
    }
}
