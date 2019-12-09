package com.example.wangyi;

import com.example.quiz.structure.Node;
import com.example.wangyi.ProxyTest.ProxyTest;

import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void textNode() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        n1.append(n2).append(n3);
        System.out.println(n1.next().next().hasNext());
    }

    @Test
    public void testFeiBoNaCi(){
//        BubbleSort.bubbleSort(new int[]{1,6,8,3,2,5,3,8,1,0,8,9,4});
    }
    @Test
    public void testRetrofit(){
        ProxyTest.test();
    }
}