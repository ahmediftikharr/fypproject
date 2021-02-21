package com.uog.smartlibraryfyp;

import android.graphics.Bitmap;

public class Book
{
    public String book_name,book_issued_date,issued_id;
    public Bitmap img_book_type;

    public String book_author,book_edition,book_shelf,book_rack,book_quantity,book_status;

    public Book()
    {

    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_issued_date() {
        return book_issued_date;
    }

    public void setBook_issued_date(String book_issued_date) {
        this.book_issued_date = book_issued_date;
    }

    public String getIssued_id() {
        return issued_id;
    }

    public void setIssued_id(String issued_id) {
        this.issued_id = issued_id;
    }

    public Bitmap getImg_book_type() {
        return img_book_type;
    }

    public void setImg_book_type(Bitmap img_book_type) {
        this.img_book_type = img_book_type;
    }


    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_edition() {
        return book_edition;
    }

    public void setBook_edition(String book_edition) {
        this.book_edition = book_edition;
    }

    public String getBook_shelf() {
        return book_shelf;
    }

    public void setBook_shelf(String book_shelf) {
        this.book_shelf = book_shelf;
    }

    public String getBook_rack() {
        return book_rack;
    }

    public void setBook_rack(String book_rack) {
        this.book_rack = book_rack;
    }

    public String getBook_quantity() {
        return book_quantity;
    }

    public void setBook_quantity(String book_quantity) {
        this.book_quantity = book_quantity;
    }

    public String getBook_status() {
        return book_status;
    }

    public void setBook_status(String book_status) {
        this.book_status = book_status;
    }
}
