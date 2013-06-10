package io.serialization;


import java.io.Serializable;

//StringList with a reasonable custom serialized form


public final class StringList implements Serializable {
 private int size   = 0;
 private Entry head = null;

 private static class Entry implements Serializable{
     String data;
     Entry  next;
     Entry  previous;
 }

 // Appends the specified string to the list
 public final void add(String s) {
    if(head == null){
    	head = new Entry();
    	head.data = s;
    }else{
    	Entry entry = new Entry();
    	entry.data = s;
    	head.next = entry;
    	entry.previous = head;
    	head = entry;
    }
 }


}

