package net.mindengine.galen.tests.selenium;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import net.mindengine.galen.components.validation.TestValidationListener;
import net.mindengine.galen.page.selenium.SeleniumPage;
import net.mindengine.galen.specs.Spec;
import net.mindengine.galen.specs.page.PageSection;
import net.mindengine.galen.specs.reader.page.PageSpec;
import net.mindengine.galen.specs.reader.page.PageSpecReader;
import net.mindengine.galen.validation.PageValidation;
import net.mindengine.galen.validation.SectionValidation;
import net.mindengine.galen.validation.ValidationError;
import net.mindengine.galen.validation.ValidationListener;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class GalenSeleniumTest {
    
    // TODO success tests for mobile layout
    
    // TODO negative tests for desktop, tablet and mobile layouts
    
    @Test
    public void performsValidation_inSeleniumBrowser() throws Exception {
        PageSpec pageSpec = new PageSpecReader().read(getClass().getResourceAsStream("/html/page.spec"));
        WebDriver driver = createDriver();
        driver.manage().window().maximize();
        
        SeleniumPage page = new SeleniumPage(driver);
        
        TestValidationListener validationListener = new TestValidationListener();
        try {
            List<PageSection> pageSections = pageSpec.findSections("all");
            
            assertThat("Filtered sections size should be", pageSections.size(), is(1));
            
            SectionValidation sectionValidation = new SectionValidation(pageSections, new PageValidation(page, pageSpec), validationListener);
            List<ValidationError> errors = sectionValidation.check();
            
            
            assertThat("Invokations should", validationListener.getInvokations(), is("<o header>" +
            		"<SpecContains header>" +
            		"<SpecNear header>" +
            		"<SpecWidth header>" +
            		"<SpecHeight header>" +
            		"<o header-with-corrections>" +
            		"<SpecWidth header-with-corrections>" +
                    "<SpecHeight header-with-corrections>" +
            		"<o header-text-1>" +
            		"<SpecNear header-text-1>" +
            		"<SpecInside header-text-1>" +
            		"<o header-text-2>" +
            		"<SpecNear header-text-2>" +
            		"<SpecInside header-text-2>" +
            		"<o menu>" +
            		"<SpecNear menu>" +
            		"<SpecNear menu>" +
            		"<o menu-item-home>" +
            		"<SpecHorizontally menu-item-home>" +
            		"<SpecNear menu-item-home>" +
            		"<SpecInside menu-item-home>" +
            		"<o menu-item-categories>" +
            		"<SpecInside menu-item-categories>" +
            		"<SpecNear menu-item-categories>"));
            assertThat("Errors should be empty", errors.size(), is(0));
        }
        catch (Exception ex) {
            throw ex;
        }
        finally {
            driver.quit();
        }
    }

    private WebDriver createDriver() {
        FirefoxDriver driver = new FirefoxDriver();
        
        String path = "file://" + getClass().getResource("/html/page-nice.html").getPath();
        
        driver.get(path);
        return driver; 
    }

}
