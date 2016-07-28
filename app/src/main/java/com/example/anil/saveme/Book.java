package com.example.anil.saveme;

import android.content.Context;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
  Created by anil on 6/13/16.
 */
@Table
public class Book extends SugarRecord {
    String title;
    String author;
    public Book() { }
    public Book( String title, String author){

        this.title = title;
        this.author = author;
    }
}