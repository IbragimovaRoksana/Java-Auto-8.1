package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.ConnectionDatabase;
import ru.netology.web.page.Login;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class ManyAuthorisationTest {

    @AfterAll
     static void deleteTables() throws SQLException {ConnectionDatabase.deleteTables();}

    @Test
    void shouldAuthorisationSecondUserAtForthTime() throws SQLException {
        //подключаемся к серверу
        open("http://localhost:9999");
        //логинимся
        val loginPageFirst = new Login();
        val authInfoFirst = ConnectionDatabase.getSecondUser();
        val verificationPageFirst = loginPageFirst.validLogin(authInfoFirst);
        //вводим верификационный код
        val verificationCodeFirst = ConnectionDatabase.getAuthCodeFromUser();
        //проверяем подключение к Dashboard и личному кабинету
        verificationPageFirst.validVerify(verificationCodeFirst);
        //снова заходим в личный кабинет со страницы логина
        //подключаемся к серверу
        open("http://localhost:9999");
        val loginPageSecond = new Login();
        val authInfoSecond = ConnectionDatabase.getSecondUser();
        val verificationPageSecond = loginPageSecond.validLogin(authInfoSecond);
        //вводим верификационный код
        val verificationCodeSecond = ConnectionDatabase.getAuthCodeFromUser();
        //проверяем подключение к Dashboard и личному кабинету
        verificationPageSecond.validVerify(verificationCodeSecond);
        //снова заходим в личный кабинет со страницы логина
        //подключаемся к серверу
        open("http://localhost:9999");
        val loginPageThird = new Login();
        val authInfoThird = ConnectionDatabase.getSecondUser();
        val verificationPageThird = loginPageThird.validLogin(authInfoThird);
        //вводим верификационный код
        val verificationCodeThird = ConnectionDatabase.getAuthCodeFromUser();
        //проверяем подключение к Dashboard и личному кабинету
        verificationPageThird.validVerify(verificationCodeThird);
        //снова заходим в личный кабинет со страницы логина
        //подключаемся к серверу
        open("http://localhost:9999");
        val loginPageForth = new Login();
        val authInfoForth = ConnectionDatabase.getSecondUser();
        val verificationPageForth = loginPageForth.validLogin(authInfoForth);
        //вводим верификационный код
        val verificationCodeForth = ConnectionDatabase.getAuthCodeFromUser();
        //проверяем подключение к Dashboard и личному кабинету
        verificationPageForth.validVerify(verificationCodeForth);
    }

}
