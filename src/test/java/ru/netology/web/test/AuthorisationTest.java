package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.ConnectionDatabase;
import ru.netology.web.page.Login;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.*;

public class AuthorisationTest {

    @AfterAll
     static void deleteTables() throws SQLException {ConnectionDatabase.deleteTables();}


    @Test
    void shouldAuthorisationFirstUser() throws SQLException {
        //подключаемся к серверу
        open("http://localhost:9999");
        //логинимся
        val loginPage = new Login();
        val authInfo = ConnectionDatabase.getFirstUser();
        val verificationPage = loginPage.validLogin(authInfo);
        //вводим верификационный код
        val verificationCode = ConnectionDatabase.getAuthCodeFromUser();
        //проверяем подключение к Dashboard и личному кабинету
        val dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldAuthorisationSecondUser() throws SQLException {
        //подключаемся к серверу
        open("http://localhost:9999");
        //логинимся
        val loginPage = new Login();
        val authInfo = ConnectionDatabase.getSecondUser();
        val verificationPage = loginPage.validLogin(authInfo);
        //вводим верификационный код
        val verificationCode = ConnectionDatabase.getAuthCodeFromUser();
        //проверяем подключение к Dashboard и личному кабинету
        val dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldNotAuthorisationFirstUserByPassword() throws SQLException {
        //подключаемся к серверу
        open("http://localhost:9999");
        //логинимся
        val loginPage = new Login();
        val authInfo = ConnectionDatabase.getFirstUser();
        //проверяем, что появилось сообщение об ошибке
        loginPage.invalidPass(authInfo);
    }

    @Test
    void shouldNotAuthorisationSecondUserByLogin() throws SQLException {
        //подключаемся к серверу
        open("http://localhost:9999");
        //логинимся
        val loginPage = new Login();
        val authInfo = ConnectionDatabase.getSecondUser();
        //проверяем, что появилось сообщение об ошибке
        loginPage.invalidLog(authInfo);
    }

    @Test
    void shouldNotVerifyFirstUser() throws SQLException {
        //подключаемся к серверу
        open("http://localhost:9999");
        //логинимся
        val loginPage = new Login();
        val authInfo = ConnectionDatabase.getFirstUser();
        val verificationPage = loginPage.validLogin(authInfo);
        //вводим верификационный код
        val verificationCode = ConnectionDatabase.getAuthCodeFromUser();
        //проверяем подключение к Dashboard и личному кабинету
        verificationPage.invalidVerify();
    }

    @Test
    void shouldNotAuthorisationByPassThreeTimes() throws SQLException {
        //подключаемся к серверу
        open("http://localhost:9999");
        //логинимся
        val loginPage = new Login();
        val authInfo = ConnectionDatabase.getFirstUser();
        //воодим неправильный пароль три раза
        val actualStatus=loginPage.invalidPassThreeTimes(authInfo);
//        assertEquals("active", actualStatus);
        assertNotEquals("active", actualStatus);
    }

}
