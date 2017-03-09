/*
 * Copyright (c) 2017, University of California, Berkeley
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package jwig.util;

import java.util.Iterator;

/**
 * @author Rohan Padhye
 */
public class DoublyLinkedList<T> implements Iterable<T> {

    static class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;
        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int length;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        length = 0;
    }

    public void addFirst(T value) {
        Node node = new Node(value);
        if (head != null) {
            head.prev = node;
            node.next = head;
        }
        head = node;
        if (tail == null)
            tail = node;
        length++;
    }

    public void addLast(T value) {
        Node node = new Node(value);
        if (tail != null) {
            tail.next = node;
            node.prev = tail;
        }
        tail = node;
        if (head == null)
            head = node;
        length++;
    }

    public T removeFirst() {
        if (length == 0)
            throw new IllegalStateException("Cannot remove from empty list");

        T value = head.value;

        if (length == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        length--;
        return value;
    }


    public T removeLast() {
        if (length == 0)
            throw new IllegalStateException("Cannot remove from empty list");

        T value = head.value;

        if (length == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        length--;
        return value;
    }

    public int size() {
        return length;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator(this);
    }

    static class LinkedListIterator<T> implements Iterator<T> {

        Node<T> node;

        LinkedListIterator(DoublyLinkedList<T> list) {
            node = list.head;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            T val = node.value;
            node = node.next;
            return val;
        }
    }
}