package com.xing.app.diarybook;

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

        int[] arr = new int[]{1,2,3};

//        Op opPre = new Op();
        Op op = new Op();
        for (int i:arr) {
            if (op.next == null) op.next = new Op();
            op.next.value = i;
            System.out.println("来个结果吧11->"+op.next.value);
            op.next.pre = op;
            op = op.next;
        }

        while (op.pre!=null) {
            op = op.pre;
            System.out.println("是否走到->"+op.value);
        }

        op.next.pre = null;

        System.out.println("来个结果吧->"+op.next.next.next.value);

    }

    public class Op{
        Op pre;
        Op next;
        int value;
    }
}