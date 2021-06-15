module com.example.bot{
    requires by.kasyan.database;
    requires spring.context;
    requires telegrambots;
    requires spring.beans;
    requires telegrambots.meta;
    uses by.kasyan.database.bean.Product;
    uses by.kasyan.database.service.ProductService;
}