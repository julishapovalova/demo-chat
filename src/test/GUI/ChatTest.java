package test.GUI;

import java.io.IOException;
import java.net.UnknownHostException;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.awt.Frame;

import Client.GUI.CFrame;
import Server.StartServer;

public class ChatTest {

	private FrameFixture testFrame;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		StartServer.main(null);
	}

	@Before
	public void setUp() throws UnknownHostException, IOException {

		Frame cframe = new CFrame();
		testFrame = new FrameFixture(cframe);
	}

	@After
	public void tearDown() {

		testFrame.cleanUp();
	}

	@Test
	public void usersUI() {
		testFrame.label("lblUser").requireText("Users");
	}

	@Test
	public void sendUI() {
		testFrame.button("btnSend").requireText("Send");
	}

	@Test
	public void messagesUI() {
		testFrame.label("lblMsg").requireText("Messages");
	}

	@Test
	public void loginLabelUI() {
		testFrame.label("lblLog").requireText("Login:");
	}

	@Test
	public void logOutUI() {
		testFrame.button("btnLogOut").requireText("Log out");
	}

	@Test
	public void logInUI() {
		testFrame.button("btnLogIn").requireText("Log in");
	}

	@Test
	public void testLogIn() {

		testFrame.textBox("inputNickName").setText("sdfsf");
		testFrame.button("btnLogIn").click();
		testFrame.textBox("txtMsg").setText("Hello");
		testFrame.button("btnSend").click();
		testFrame.textBox("msgArea").requireText("Hello");

		
	}

}
