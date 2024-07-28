package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication //Это основной класс спринг бут приложения
@EnableJpaRepositories //включает поддержку репозиториев Java Persistence API , что позволяет работать с БД
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args); //Запускает спринг бут приложение, передавая основной класс и аргументы командной строки
    }
}