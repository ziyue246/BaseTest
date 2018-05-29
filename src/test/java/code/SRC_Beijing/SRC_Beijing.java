package code.SRC_Beijing;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by ziyue on 2018/5/27.
 */
public class SRC_Beijing {



    @Test
    public void testMain(){
        createStackR(null,null);
    }

    //递归法
    public  ArrayList<Box> createStackR(Box[] boxes, Box bottom){
        int maxHeight=0;
        ArrayList<Box> maxStack=null;

        for(int i=0;i<boxes.length;i++){
            if(boxes[i].canBeAbove(bottom)){
                ArrayList<Box> newStack=createStackR(boxes,boxes[i]);
                int newHeight=stackHeight(newStack);

                if(newHeight>maxHeight){
                    maxHeight=newHeight;
                    maxStack=newStack;
                }
            }
        }
        if(maxStack==null)
            maxStack=new ArrayList<Box>();

        if(bottom!=null)
            maxStack.add(0,bottom);

        return maxStack;
    }

    public int stackHeight(ArrayList<Box> stack){
        int height=0;
        for(int i=0;i<stack.size();i++){
            height+=stack.get(i).heigth;
        }
        return height;
    }




    //动态规划
    public ArrayList<Box> createStackDP(Box[] boxes,Box bottem,HashMap<Box,ArrayList<Box>> stackMap){
        if(bottem!=null&&stackMap.containsKey(bottem))
            return stackMap.get(bottem);

        int maxHeight=0;
        ArrayList<Box> maxStack=null;

        for(int i=0;i<boxes.length;i++){
            if(boxes[i].canBeAbove(bottem)){
                ArrayList<Box> newStack=createStackDP(boxes, boxes[i], stackMap);
                int newHeight=stackHeight(newStack);

                if(newHeight>maxHeight){
                    maxStack=newStack;
                    maxHeight=newHeight;
                }
            }
        }

        if(maxStack==null)
            maxStack=new ArrayList<Box>();
        if(bottem!=null)
            maxStack.add(0, bottem);
        stackMap.put(bottem, maxStack);

        /**
         * 方法clone()来自Object类，其方法签名如下：重写方法时，可以调整参数，但不得改动返回类型。
         * 因此，如果继承自Object的类重写了clone()方法，它的clone()方法仍将返回Object实例。因此必须转型返回值。
         */

        return (ArrayList<Box>) maxStack.clone();//返回副本
    }




    @Test
    public void testMyArrayList(){


        MyArrayList<Integer> intMyArrayList = new MyArrayList(3);

        for(int i=0;i<10;i++) {
            intMyArrayList.add((i%5));
        }

        for(int i=0;i<intMyArrayList.size();i++) {
            System.out.print("i:"+i+"   "+intMyArrayList.get(i)+"   size:");
            System.out.println(intMyArrayList.size());
        }
        System.out.println(intMyArrayList.remove(2));

        for(int i=0;i<intMyArrayList.size();i++) {
            System.out.print("i:"+i+"   "+intMyArrayList.get(i)+"   size:");
            System.out.println(intMyArrayList.size());
        }
        intMyArrayList.remove((Integer) 2);
        for(int i=0;i<intMyArrayList.size();i++) {
            System.out.print("i:"+i+"   "+intMyArrayList.get(i)+"   size:");
            System.out.println(intMyArrayList.size());
        }
        System.out.print(intMyArrayList.get(2));

    }
}


class MyArrayList<T>{

    private T[] datas;
    private int size;


    public int size() {
        return size;
    }


    public MyArrayList() {
        datas = (T[])new Object[10];
        size=0;
    }
    public MyArrayList(int n) {
        datas = (T[])new Object[n];
        size=0;
    }


    public T get(int index){
        if(index>=size){
            System.err.println("index:" +index+" is too large");
            return null;
        }
        return datas[index];
    }
    public void add(T data){
        if(size>=datas.length){
            T[] newarr=(T[])new Object[datas.length*2];
            System.arraycopy(datas,(0),newarr,(0),datas.length);
            datas=newarr;
        }
        datas[size++]=data;
    }
    public T remove(int index){
        if(index>size){
            System.err.println("index:" +index+" is too large");
            return null;
        }
        for(int i=index;i<size;i++) {
            datas[i] = datas[i+1];
        }
        size-=1;
        return datas[index];
    }

    public void remove(Object item){


        int currIndex=0;
        int removeCount = 0;
        for(int i=0;i<size&&currIndex<size;++i,++currIndex) {
            while(currIndex<size&&datas[currIndex].equals(item)){
                ++currIndex;++removeCount;
            }
            datas[i] = datas[currIndex];
        }
        size-=removeCount;
    }
}

class Box{
    int width;
    int heigth;
    int depth;

    public Box(int width,int heigth,int depth){
        this.width=width;
        this.heigth=heigth;
        this.depth=depth;
    }

    public boolean canBeAbove(Box box){
        if(box.width>this.width&&box.heigth>this.heigth&&box.depth>this.depth)
            return true;
        return false;
    }
}