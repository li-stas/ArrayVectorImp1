package ru.skillbench.tasks.basics.math;

import java.util.Arrays;

/**
 * ЦЕЛЬ ЗАДАЧИ - потренироваться с массивами и циклами в Java, принципами написания класса,
 *  вызовом статических методов (стандартных классов).<br/>
 * <br/>
 * ЗАДАНИЕ<br/>
 * Реализовать класс для работы с вектором (набор вещественных чисел, координат)
 *  и базовые операции векторной арифметики.<br/>
 * <br/>
 * ТРЕБОВАНИЯ<br/>
 * Экземпляр класса, реализующего данный интерфейс, должен соответствовать одному вектору
 *  и хранить элементы вектора в поле типа "массив".<br/>
 * НЕЛЬЗЯ пользоваться классами библиотеки Java, кроме классов Math и Arrays.<br/>
 * Если в коде есть конструкторы, то среди них должен быть конструктор без параметров:
 * <pre>public ArrayVectorImpl() {  }</pre>
 * Класс должен работать корректно после вызова такого конструктора и без вызова set-метода.<br/>
 * <br/>
 * ПРИМЕЧАНИЕ<br/>
 * Задачу можно решать без явной обработки и генерации исключительных ситуаций (Exceptions).<br/>
 *
 * @author Andrey Gavrilov
 * @author Alexander Kharichkin
 * @author Alexey Evdokimov
 */
public class ArrayVectorImpl implements ArrayVector {
    private double[] aRealNum = null;
    private int len = 0;

    public ArrayVectorImpl() {
    }

    public ArrayVectorImpl(double... elements ) {
        this.aRealNum = elements.clone();
        this.len = elements.length;
    }

    public void ArrayVectorImpl() {
        this.aRealNum = null;
        this.len = 0;
    }
    /**
     * Задает все элементы вектора (определяет длину вектора).
     * Передаваемый массив не клонируется.
     * @param elements Не равен null
     */
    public void set(double... elements){
        this.aRealNum = elements.clone();
        this.len = elements.length;
    }
    /**
     * Возвращает все элементы вектора. Массив не клонируется.
     */
    public double[] get() {
        return this.aRealNum;
    }
    /**
     * Возвращает копию вектора (такую, изменение элементов
     *  в которой не приводит к изменению элементов данного вектора).<br/>
     * Рекомендуется вызвать метод clone() у самого массива или использовать
     *   {@link System#arraycopy(Object, int, Object, int, int)}.
     */
    public ArrayVector clone() {
        return new ArrayVectorImpl(this.aRealNum);
    }
    /**
     * Возвращает число элементов вектора.
     */
    public  int getSize() {
        return this.len;
    }

    /**
     * Изменяет элемент по индексу.
     * @param index В случае выхода индекса за пределы массива:<br/>
     *  а) если index<0, ничего не происходит;<br/>
     *  б) если index>=0, размер массива увеличивается так, чтобы index стал последним.
     */
    public void set(int index, double value) {
        if (index >= 0) {
            if ( index > this.len - 1 ) { // больше чем есть елементов
                double [] aTmp = new double[ index + 1 ];
                System.arraycopy( this.aRealNum, 0, aTmp, 0, this.len);
                aRealNum = aTmp;
                len = index + 1;
            }
            aRealNum[index] = value;
        }
    }
    /**
     * Возвращает элемент по индексу.
     * @param index В случае выхода индекса за пределы массива
     *   должно генерироваться ArrayIndexOutOfBoundsException
     */
    public double get(int index) throws ArrayIndexOutOfBoundsException {
        if ( len > 0 ) {
            return aRealNum[index];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    /**
     * Возвращает максимальный элемент вектора.
     */
    public double getMax() {
        return cMinMax( true );
    }
    /**
     * Возвращает минимальный элемент вектора.
     */
    public double getMin(){
        return cMinMax( false );
    }

    private double cMinMax(boolean lMax) {
        double nMinMax = 0;
        if (len > 0) {
            nMinMax = aRealNum[0];
            for (int i = 0; i < len; i++) {
                if ( lMax ? aRealNum[i] > nMinMax : aRealNum[i] < nMinMax ) {
                    nMinMax = aRealNum[i];
                }
            }
        }
        return nMinMax;
    }

    /**
     * Сортирует элементы вектора в порядке возрастания.
     */
    public void sortAscending() {
        //Arrays.sort(aRealNum);
        if (len > 0) {
            double nElem;
            for (int j=1; j<=len ; j++) {
                for (int i=0; i<(len-1) ; i++) {
                    if ( aRealNum[i] > aRealNum[i+1] ) {  // < - убывания > - возрастнаия
                        nElem = aRealNum[ i ];
                        aRealNum[i] = aRealNum[i+1];
                        aRealNum[i+1] = nElem;
                    }//endif
                }//next i
            }//next j
         }

    }

    /**
     * Умножает вектор на число.<br/>
     * Замечание: не пытайтесь использовать безиндексный цикл foreach:
     *  для изменения элемента массива нужно знать его индекс.
     * @param factor
     */
    public void mult(double factor) {
        for (int i=0; i<len; i++) {
            aRealNum[i] *= factor;
        }
    }
    /**
     * Складывает вектор с другим вектором, результат запоминает в элементах данного вектора.<br/>
     * Если векторы имеют разный размер, последние элементы большего вектора не учитываются<br/>
     *  (если данный вектор - больший, его размер менять не надо, просто не меняйте последние элементы).
     * @param anotherVector Не равен null
     * @return Ссылка на себя (результат сложения)
     */
    public ArrayVector sum(ArrayVector anotherVector) {
        int nLenTmp = 0;
        // nLenTmp = (anotherVector.getSize() > len ? len : anotherVector.getSize() );
        nLenTmp = Math.min(anotherVector.getSize(), len);
        for (int i=0; i < nLenTmp; i++) {
            aRealNum[i] += anotherVector.get(i);
        }
        return this;
    }
    /**
     * Возвращает скалярное произведение двух векторов.<br/>
     * Если векторы имеют разный размер, последние элементы большего вектора не учитываются.
     * @param anotherVector Не равен null
     */
    public double scalarMult(ArrayVector anotherVector) {
        double nScalarMult = 0;
        int nLenTmp = Math.min(anotherVector.getSize(), len);
        for (int i = 0; i < nLenTmp; i++) {
            nScalarMult += aRealNum[i] * anotherVector.get(i);
        }
        return nScalarMult;
    }
    /**
     * Возвращает евклидову норму вектора (длину вектора
     *  в n-мерном евклидовом пространстве, n={@link #getSize()}).
     * Это можно подсчитать как корень квадратный от скалярного произведения вектора на себя.
     */
    public double getNorm() {
        //return Math.sqrt(scalarMult(new ArrayVectorImpl( aRealNum)));
        return Math.sqrt(scalarMult(this ));
    }
}

