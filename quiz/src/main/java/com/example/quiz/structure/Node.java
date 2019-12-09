package com.example.quiz.structure;

/**
 * 单链表节点
 */
public class Node {
    int date;
    Node next;

    public Node(int date) {
        this.date = date;
    }

    //为节点追加节点
    public Node append(Node node){
        while (this.hasNext()){
           this.next = this;
        }
        this.next = node;
        return this.next;
    }

    public void after(Node node){
        if (hasNext()){

        }else {
            this.next = node;
        }

    }

    public Node next() {
        return next;
    }

    public int getDate() {
        return date;
    }

    public boolean hasNext(){
        return next != null;
    }
}
