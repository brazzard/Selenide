package org.selenide.example.Proxy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.codeborne.selenide.WebDriverProvider;

import net.lightbody.bmp.client.ClientUtil;

public class BmpFirefox implements WebDriverProvider {
    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability(CapabilityType.PROXY,
                ClientUtil.createSeleniumProxy(Bmp.proxyServer));
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return new FirefoxDriver(capabilities);
    }
}
