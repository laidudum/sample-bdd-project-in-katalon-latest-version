package web
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When

class LoginSteps {

	@When("User types username (.*) into the Username field")
	def enterUsername(String username) {
		WebUI.setText(findTestObject('Object Repository/Web/Login Page/textFieldUsername'), username)
		WebUI.takeScreenshotAsCheckpoint("User types username into the Username field")
	}

	@When("User types password (.*) into the Password field")
	def enterPassword(String password) {
		WebUI.setText(findTestObject('Object Repository/Web/Login Page/textFieldPassword'), password)
		WebUI.takeScreenshotAsCheckpoint("User types password into the Username field")
	}

	@When("User clicks the Submit button")
	def clickSubmitButton() {
		WebUI.click(findTestObject('Object Repository/Web/Login Page/buttonSubmit'))
	}

	@Then("User verifies the message (.*) is displayed")
	def verifyMessageDisplayed(String expectedMessage) {
		if (expectedMessage == "Logged In Successfully") {
			WebUI.verifyElementText(findTestObject('Object Repository/Web/Homepage/headerText'), expectedMessage)
			WebUI.takeScreenshotAsCheckpoint("User verifies the Logged In Successfully message")
		} else {
			WebUI.verifyElementText(findTestObject('Object Repository/Web/Login Page/messageError'), expectedMessage)
			WebUI.takeScreenshotAsCheckpoint("User verifies the Log In Failed message")
		}
	}
}