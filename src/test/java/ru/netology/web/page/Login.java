package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.User;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Login {

    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");

    private SelenideElement mistake = $("[data-test-id=error-notification]");


    public VerificationPage validLogin(User info) {
        loginField.setValue(info.getLogin());
        if (info.getLogin().equals("vasya"))passwordField.setValue("qwerty123");
        else {
            passwordField.setValue("123qwerty");
        }
        loginButton.click();
        return new VerificationPage();
    }

    public void invalidPass(User info) {
        loginField.setValue(info.getLogin());
        if (info.getLogin().equals("vasya"))passwordField.setValue("incorrect123");
        else {
            passwordField.setValue("incorrect123");
        }
        loginButton.click();
        mistake.shouldBe(visible);
    }

    public void invalidLog(User info) {
        loginField.setValue("ququ");
        if (info.getLogin().equals("vasya"))passwordField.setValue("qwerty123");
        else {
            passwordField.setValue("123qwerty");
        }
        loginButton.click();
        mistake.shouldBe(visible);
    }

    public String invalidPassThreeTimes(User info) {
        loginField.setValue(info.getLogin());
        if (info.getLogin().equals("vasya"))passwordField.setValue("incorrect123");
        else {
            passwordField.setValue("incorrect123");
        }
        loginButton.click();
        loginButton.click();
        loginButton.click();
        String status=info.getStatus();
        return status;
    }

    // TODO метод ввода пароля 1 раз неверно
    //TODO метод ввода неверного пароля 3 раза
}
