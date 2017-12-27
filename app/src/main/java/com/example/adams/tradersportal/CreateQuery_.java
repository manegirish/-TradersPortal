package com.example.adams.tradersportal;

/**
 * Created by GirishM on 27-12-2017.
 */

class CreateQuery_ {

    /*
    * CREATE TABLE `sell_product` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`name`	TEXT NOT NULL,
	`price`	TEXT NOT NULL,
	`timestamp`	INTEGER NOT NULL
);
    * */
    static final String CREATE_TABLE_SELL_PRODUCTS = "CREATE TABLE "
            + Constants_.SELL_PRODUCT+" ("
            + Constants_.ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + Constants_.NAME + " TEXT NOT NULL, "
            + Constants_.PRICE + " TEXT NOT NULL, "
            + Constants_.TIMESTAMP + " INTEGER NOT NULL );";
}
