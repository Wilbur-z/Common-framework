package com.wilbur.controller;

import com.wilbur.pojo.Books;
import com.wilbur.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    @RequestMapping("/allBook")
    public String list(Model model) {
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list", list);
        return "allBook";
    }

    //跳转到增加书籍页面
    @RequestMapping("/toAddBook")
    public  String toAddPaper(){
        return "addBook";
    }

    //添加书籍
    @RequestMapping("/addBook")
    public  String addBook(Books books){
        bookService.addBook(books);
        return "redirect:/book/allBook";
    }

    //跳转到修改界面
    @RequestMapping("/update")
    public String toUpdatePaper(Model model,int id) {
        Books books = bookService.queryBookById(id);
        model.addAttribute("books", books);
        return "updateBooks";
    }

    //修改书籍
    @RequestMapping("/updateBook")
        public String updateBook(Books books){
         bookService.updateBook(books);
            return "redirect:/book/allBook";
    }

    @RequestMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") int id) {
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }
}
