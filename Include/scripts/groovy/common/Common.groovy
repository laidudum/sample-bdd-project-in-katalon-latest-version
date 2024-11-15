package common
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

import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration

import com.kms.katalon.core.appium.driver.AppiumDriverManager
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.remote.DesiredCapabilities
import com.kms.katalon.core.mobile.driver.MobileDriverType

class Common {

	@Given("User opens the login page")
	def openLoginPage() {
		WebUI.openBrowser('')
		WebUI.maximizeWindow()
		WebUI.navigateToUrl(GlobalVariable.url)
		WebUI.takeScreenshotAsCheckpoint("User opens the login page")
	}

	@Given("User launches the Mobile App")
	def launchApp() {
		switch (Mobile.getDeviceOS()) {
			case 'Android':
				Mobile.startApplication(RunConfiguration.getProjectDir() + '/App Files/' + GlobalVariable.androidFileAppName, true)
				Mobile.takeScreenshotAsCheckpoint('User navigates to login page')
				break
			case 'iOS':
				Mobile.startApplication(RunConfiguration.getProjectDir() + '/App Files/' + GlobalVariable.iosFileAppName, true)
				Mobile.takeScreenshotAsCheckpoint('User navigates to login page')
				break
			case null:
				DesiredCapabilities capabilities = new DesiredCapabilities();
				switch (true) {
					case AppiumDriverManager.getRemoteWebDriverServerUrl().contains('kobiton'):
						capabilities.setCapability("sessionName", GlobalVariable.kobitonSessionName);
						capabilities.setCapability("sessionDescription", GlobalVariable.kobitonSessionDescription);
						capabilities.setCapability("deviceOrientation", GlobalVariable.kobitonDeviceOrientation);
						capabilities.setCapability("captureScreenshots", GlobalVariable.kobitonCaptureScreenshots);
						capabilities.setCapability("app", GlobalVariable.kobitonApp)
						capabilities.setCapability("deviceGroup", GlobalVariable.kobitonDeviceGroup);
						capabilities.setCapability("deviceName", GlobalVariable.kobitonDeviceName);
						capabilities.setCapability("platformVersion", GlobalVariable.kobitonPlatformVersion);
						capabilities.setCapability("platformName", GlobalVariable.kobitonPlatformName);
						capabilities.setCapability("autoGrantPermissions", GlobalVariable.kobitonAutoGrantPermissions);
						capabilities.setCapability("kobi:retainDurationInSeconds", 0);
						AppiumDriverManager.createMobileDriver(MobileDriverType.ANDROID_DRIVER, capabilities, new URL(AppiumDriverManager.getRemoteWebDriverServerUrl()))
						break
					case AppiumDriverManager.getRemoteWebDriverServerUrl().contains('browserstack'):
						capabilities.setCapability("device", GlobalVariable.browserstackDevice);
						capabilities.setCapability('app', GlobalVariable.browserstackApp);
						AppiumDriverManager.createMobileDriver(MobileDriverType.ANDROID_DRIVER, capabilities, new URL(AppiumDriverManager.getRemoteWebDriverServerUrl()))
						break
				}
				break;
		}
	}
}