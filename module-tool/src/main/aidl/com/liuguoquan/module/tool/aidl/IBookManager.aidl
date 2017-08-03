// IBookManager.aidl
package com.liuguoquan.module.tool.aidl;

import com.liuguoquan.module.tool.aidl.Book;

interface IBookManager {

    List<Book> getBookList();
    void addBook(in Book book);
}
