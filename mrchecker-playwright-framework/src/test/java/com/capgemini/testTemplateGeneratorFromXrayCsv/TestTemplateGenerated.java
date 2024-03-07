//imports
import com.capgemini.framework.logger.AllureStepLogger;
import com.capgemini.framework.playwright.BaseTest;
import com.capgemini.framework.playwright.PlaywrightFactory;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.Assert.fail;
import com.capgemini.framework.tags.Status;
public class TestTemplateGenerated extends BaseTest {


@TmsLink("TmsLink-TODO")
@Epic("") // TODO add epic
@Story("") // TODO add story
@Description("") // TODO add description
@Test
@Tag(Status.IN_PROGRESS)
public void TestTemplateGenerated_test(){


 //calls to methods:

		goInStepperToStepStep1();
		clickOnRadioButtonJaDerUmfangWirdMitFolgenderIdFieldNachgewiesen();
		fillTextboxIdFieldWithABlank();
		fillTextboxIdFieldWith20AlphanumericCharacters();
		fillTextboxIdFieldWithMoreThan20AlphanumericCharacters();
		clickOnRadioButtonJaDerAlteBerichtMitDerFolgendenIdFieldWirdErsetzt();
		fill2ndTextboxIdFieldWithABlank();
		fill2ndTextboxIdFieldWith20AlphanumericCharacters();
		fill2ndTextboxIdFieldWithMoreThan20AlphanumericCharacters();
		clickOnRadioButtonNeinDasSchmetterlingsblattIstInDemNeuenBerichtEnthalten();
		clickOnRadioButtonJaDerNeueNachweisIstImNeuenBerichtEnthalten();
		clickOnRadioButtonNeinDerBerichtIstWeiterhinGueltig();
		clickOnRadioButtonJaDerAlteBerichtMitDerFolgendenIdFieldWirdErsetzt1() /* TODO remove or change the method's name */ ;
		clickOnRadioButtonNeinDerUmfangIstNichtSelbstzertifizierungsrelevant();
		clickOnRadioButtonJaDerUmfangWirdImSelbstzertifizierungsberichtMitFolgenderIdFieldNachgewiesen();
		fillTextboxIdFieldWithSomeText();
		clickOnRadioButtonNeinDerBerichtIstWeiterhinGueltig1() /* TODO remove or change the method's name */ ;
		methodFromHellSgTlnrDdfgoejjHhhhywgZghhkkkk();
		methodFromHellSgTlnrDdfgoejjHhhhywgZghhkkkk1() /* TODO remove or change the method's name */ ;
		methodFromHellSgTlnrDdfgoejjHhhhywgZghhkkkk2() /* TODO remove or change the method's name */ ;
		methodFromHellSgTlnrDdfgoejjQuestionMarkQuestionMarkQuestionMarkHhhhywgZghhkkkk();
		fail( "This is test case template generated from Xray exported CSV. Please fill it with test implementation.");
}


 //definitions of methods:

	@Step("Go in stepper to step \"STEP1\"")
	private void goInStepperToStepStep1() {
		AllureStepLogger.info("In part \"Selbstzertifizierungsbericht\" the following texts and options are shown:\n"+
			"* <text> Ist die Änderung selbstzertifizierungsrelevant \n"+
			"und wird in einem Selbstzertifizierungsbericht durch den Fachbereich nachgewiesen?\n"+
			" * <radio button> Nein - der Umfang ist nicht selbstzertifizierungsrelevant .\n"+
			" * <radio button> Ja - der Umfang wird mit folgender ID-Field nachgewiesen:\n"+
			"By default none of the radio buttons is set.\n"+
			"The texts are correct.");
		AllureStepLogger.makeScreenshot();
	}


	@Step("Click on radio button \"Ja - der Umfang wird mit folgender ID-Field nachgewiesen:\"")
	private void clickOnRadioButtonJaDerUmfangWirdMitFolgenderIdFieldNachgewiesen() {
		AllureStepLogger.info("The element can be selected.\n"+
			"Only the radio button for this element is set.\n"+
			"Following elements expand:\n"+
			"* <field> \"ID-Field\"\n"+
			"* <text> Erfordert die Änderung eine Anpassung des Selbstzertifizierungsberichts?\n"+
			"* <radio button> Nein - der Bericht ist weiterhin gültig.\n"+
			"* <radio button> Ja - der alte Bericht mit der folgenden ID-Field wird ersetzt:\n"+
			"Field \"ID-Field\" does NOT show a green frame with a green hook.\n"+
			"By default none of the radio buttons is set.\n"+
			"The texts are correct.");
		AllureStepLogger.makeScreenshot();
	}


	@Step("Fill textbox \"ID-Field\" with a blank.")
	private void fillTextboxIdFieldWithABlank() {
		AllureStepLogger.info("The blank can be entered.\n"+
			"Field \"ID-Field\" does NOT show a green frame with a green hook.");
		AllureStepLogger.makeScreenshot();
	}


	@Step("Fill textbox \"ID-Field\" with 20 alphanumeric characters.")
	private void fillTextboxIdFieldWith20AlphanumericCharacters() {
		AllureStepLogger.info("20 characters can be entered.\n"+
			"Field \"ID-Field\" shows a green frame with a green hook.");
		AllureStepLogger.makeScreenshot();
	}


	@Step("Fill textbox \"ID-Field\" with >20 alphanumeric characters.")
	private void fillTextboxIdFieldWithMoreThan20AlphanumericCharacters() {
		AllureStepLogger.info("More than 20 characters cannot be entered.\n"+
			"Field \"ID-Field\" shows a green frame with a green hook.");
		AllureStepLogger.makeScreenshot();
	}


	@Step("Click on radio button \"Ja - der alte Bericht mit der folgenden ID-Field wird ersetzt:\"")
	private void clickOnRadioButtonJaDerAlteBerichtMitDerFolgendenIdFieldWirdErsetzt() {
		AllureStepLogger.info("The element can be selected.\n"+
			"The radio button for this element is set.\n"+
			"Following elements expand:\n"+
			"* <field> \"ID-Field\"\n"+
			"* <text> Erfordert die Änderung einen neuen Nachweis?\n"+
			"* <radio button> Nein - das Schmetterlingsblatt ist in dem neuen Bericht enthalten.\n"+
			"* <radio button> Ja - der neue Nachweis ist im neuen Bericht enthalten.\n"+
			"Field \"ID-Field\" does NOT show a green frame with a green hook.\n"+
			"By default none of the radio buttons is set.\n"+
			"The texts are correct.");
		AllureStepLogger.makeScreenshot();
	}


	@Step("Fill 2nd textbox \"ID-Field\" with a blank.")
	private void fill2ndTextboxIdFieldWithABlank() {
		AllureStepLogger.info("The blank can be entered.\n"+
			"Field \"ID-Field\" does NOT show a green frame with a green hook.");
		AllureStepLogger.makeScreenshot();
	}


	@Step("Fill 2nd textbox \"ID-Field\" with 20 alphanumeric characters.")
	private void fill2ndTextboxIdFieldWith20AlphanumericCharacters() {
		AllureStepLogger.info("20 characters can be entered.\n"+
			"Field \"ID-Field\" shows a green frame with a green hook.");
		AllureStepLogger.makeScreenshot();
	}


	@Step("Fill 2nd textbox \"ID-Field\" with >20 alphanumeric characters.")
	private void fill2ndTextboxIdFieldWithMoreThan20AlphanumericCharacters() {
		AllureStepLogger.info("More than 20 characters cannot be entered.\n"+
			"Field \"ID-Field\" shows a green frame with a green hook.");
		AllureStepLogger.makeScreenshot();
	}


	@Step("Click on radio button \"Nein - das Schmetterlingsblatt ist in dem neuen Bericht enthalten.\"")
	private void clickOnRadioButtonNeinDasSchmetterlingsblattIstInDemNeuenBerichtEnthalten() {
		AllureStepLogger.info("The element can be selected.\n"+
			"The radio button for this element is set.");
		AllureStepLogger.makeScreenshot();
	}


	@Step("Click on radio button \"Ja - der neue Nachweis ist im neuen Bericht enthalten.\"")
	private void clickOnRadioButtonJaDerNeueNachweisIstImNeuenBerichtEnthalten() {
		AllureStepLogger.info("The element can be selected.\n"+
			"The radio button for this element is set.");
		AllureStepLogger.makeScreenshot();
	}


	@Step("Click on radio button \"Nein - der Bericht ist weiterhin gültig.\"")
	private void clickOnRadioButtonNeinDerBerichtIstWeiterhinGueltig() {
		AllureStepLogger.info("The element can be selected.\n"+
			"The radio button for this element is set.\n"+
			"The 2nd field \"ID-Field\" and the text and elements below disappear.");
		AllureStepLogger.makeScreenshot();
	}


	@Step("Click on radio button \"Ja - der alte Bericht mit der folgenden ID-Field wird ersetzt:\"")
	private void clickOnRadioButtonJaDerAlteBerichtMitDerFolgendenIdFieldWirdErsetzt1() /* TODO remove or change the method's name */  {
		AllureStepLogger.info("The element can be selected.\n"+
			"The radio button for this element is set.\n"+
			"Following elements expand:\n"+
			"* <field> \"ID-Field\"\n"+
			"* <text> Erfordert die Änderung einen neuen Nachweis?\n"+
			"* <radio button> Nein - das Schmetterlingsblatt ist in dem neuen Bericht enthalten.\n"+
			"* <radio button> Ja - der neue Nachweis ist im neuen Bericht enthalten.\n"+
			"Field \"ID-Field\" does NOT show a green frame with a green hook.\n"+
			"By default none of the radio buttons is set.");
		AllureStepLogger.makeScreenshot();
	}


	@Step("Click on radio button \"Nein - der Umfang ist nicht selbstzertifizierungsrelevant.\"")
	private void clickOnRadioButtonNeinDerUmfangIstNichtSelbstzertifizierungsrelevant() {
		AllureStepLogger.info("The element can be selected.\n"+
			"The radio button for this element is set.\n"+
			"The field \"ID-Field\" and the text and elements below disappear.");
		AllureStepLogger.makeScreenshot();
	}


	@Step("Click on radio button \"Ja - der Umfang wird im Selbstzertifizierungsbericht mit folgender ID-Field nachgewiesen:\"")
	private void clickOnRadioButtonJaDerUmfangWirdImSelbstzertifizierungsberichtMitFolgenderIdFieldNachgewiesen() {
		AllureStepLogger.info("The element can be selected.\n"+
			"Only the radio button for this element is set.\n"+
			"Following elements expand:\n"+
			"* <field> \"ID-Field\"\n"+
			"* <text> Erfordert die Änderung eine Anpassung des Selbstzertifizierungsberichts?\n"+
			"* <radio button> Nein - der Bericht ist weiterhin gültig.\n"+
			"* <radio button> Ja - der alte Bericht mit der folgenden ID-Field wird ersetzt:\n"+
			"Field \"ID-Field\" does NOT show a green frame with a green hook.\n"+
			"By default none of the radio buttons is set.\n"+
			"The texts are correct.");
		AllureStepLogger.makeScreenshot();
	}


	@Step("Fill textbox \"ID-Field\" with some text")
	private void fillTextboxIdFieldWithSomeText() {
		AllureStepLogger.info("The text can be entered.\n"+
			"Field \"ID-Field\" shows a green frame with a green hook");
		AllureStepLogger.makeScreenshot();
	}


	@Step("Click on radio button \"Nein - der Bericht ist weiterhin gültig.\"")
	private void clickOnRadioButtonNeinDerBerichtIstWeiterhinGueltig1() /* TODO remove or change the method's name */  {
		AllureStepLogger.info("The element can be selected.\n"+
			"The radio button for this element is set.");
		AllureStepLogger.makeScreenshot();
	}


	@Step("METHOD from *hell* SG-TLNR DDFGÖJJ HHHHywG \"ZGhh\" kkkk")
	private void methodFromHellSgTlnrDdfgoejjHhhhywgZghhkkkk() {
		AllureStepLogger.info("METHOD from *hell* SG-TLNR\n"+
			"	DDFGÖJJ HHHHywG \"ZGhh\" kkkk");
		AllureStepLogger.makeScreenshot();
	}


	@Step("METHOD from *hell* SG-TLNR DDFGÖJJ HHHHywG \"ZGhh\" kkkk")
	private void methodFromHellSgTlnrDdfgoejjHhhhywgZghhkkkk1() /* TODO remove or change the method's name */  {
		AllureStepLogger.info("METHOD from *hell* SG-TLNR DDFGÖJJ HHHHywG \"ZGhh\" kkkk");
		AllureStepLogger.makeScreenshot();
	}


	@Step("METHOD from *hell* SG-TLNR DDFGÖJJ HHHHywG \"ZGhh\" kkkk")
	private void methodFromHellSgTlnrDdfgoejjHhhhywgZghhkkkk2() /* TODO remove or change the method's name */  {
		AllureStepLogger.info("METHOD from *hell* SG-TLNR DDFGÖJJ HHHHywG \"ZGhh\" kkkk");
		AllureStepLogger.makeScreenshot();
	}


	@Step("METHOD from *hell* SG-TLNR & DDFGÖJJ ??? HHHHywG \"ZGhh\" kkkk")
	private void methodFromHellSgTlnrDdfgoejjQuestionMarkQuestionMarkQuestionMarkHhhhywgZghhkkkk() {
		AllureStepLogger.info("METHOD from *hell* SG-TLNR & DDFGÖJJ HHHHywG \"ZGhh\" kkkk");
		AllureStepLogger.makeScreenshot();
	}


}