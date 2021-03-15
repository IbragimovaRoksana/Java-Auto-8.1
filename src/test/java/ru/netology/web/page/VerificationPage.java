package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.AuthCode;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    private SelenideElement incorrectCode = $("[data-test-id=error-notification]");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify(AuthCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }

    public void invalidVerify() {
        codeField.setValue("000000");
        verifyButton.click();
        incorrectCode.shouldBe(visible);
    }

}
