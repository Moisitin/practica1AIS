package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebAppTest {
	
	protected WebDriver driver1;
	protected WebDriver driver2;
	
	@BeforeClass
	public static void setupClass() {
		System.setProperty("webdriver.chrome.driver",
		"C:\\Users\\Moises\\Desktop\\UNIVERSIDAD\\Sistemas_Distribuidos\\Practica 1\\eclipse\\chromedriver.exe");
		WebApp.start();
	}
	
	@AfterClass
	public static void teardownClass() {
		WebApp.stop();
	}
	
	@Before
	public void setupTest() {
		driver1 = new ChromeDriver();
		driver2 = new ChromeDriver();
		
		driver1.get("http://localhost:8080/");
		driver2.get("http://localhost:8080/");
		
		driver1.findElement(By.id("nickname")).sendKeys("Jorge");
		driver1.findElement(By.id("startBtn")).click();
		
		driver2.findElement(By.id("nickname")).sendKeys("Moises");
		driver2.findElement(By.id("startBtn")).click();
	}
	
	@After
	public void teardown() {
		if (driver1 != null) {
			driver1.quit();
		}
		if (driver2 != null) {
			driver2.quit();
		}
	}
	

	@Test
	public void firstPlayerWinTest() throws InterruptedException {
		
		driver1.findElement(By.id("cell-0")).click();
		driver2.findElement(By.id("cell-1")).click();
		driver1.findElement(By.id("cell-3")).click();
		driver2.findElement(By.id("cell-2")).click();
		driver1.findElement(By.id("cell-6")).click();
		
		Thread.sleep(3500);
		String messagePlayer1 = driver1.switchTo().alert().getText();
		
		Thread.sleep(3500);
		String messagePlayer2 = driver2.switchTo().alert().getText();
		
		String expected = "Jorge wins! Moises looses.";
		
		assertEquals(expected, messagePlayer1);
		assertEquals(expected, messagePlayer2);
	}

	@Test
	public void secondPlayerWinTest() throws InterruptedException {
		
		driver1.findElement(By.id("cell-4")).click();		
		driver2.findElement(By.id("cell-2")).click();
		driver1.findElement(By.id("cell-1")).click();
		driver2.findElement(By.id("cell-5")).click();
		driver1.findElement(By.id("cell-3")).click();
		driver2.findElement(By.id("cell-8")).click();
		
		Thread.sleep(3500);
		String messagePlayer1 = driver1.switchTo().alert().getText();
		
		Thread.sleep(3500);
		String messagePlayer2 = driver2.switchTo().alert().getText();
		
		String expected = "Moises wins! Jorge looses.";
		
		assertEquals(expected, messagePlayer1);
		assertEquals(expected, messagePlayer2);
	}
	
	@Test
	public void drawTest() throws InterruptedException {
		
		driver1.findElement(By.id("cell-0")).click();
		driver2.findElement(By.id("cell-5")).click();
		driver1.findElement(By.id("cell-4")).click();
		driver2.findElement(By.id("cell-8")).click();
		driver1.findElement(By.id("cell-7")).click();
		driver2.findElement(By.id("cell-1")).click();
		driver1.findElement(By.id("cell-2")).click();
		driver2.findElement(By.id("cell-6")).click();
		driver1.findElement(By.id("cell-3")).click();
		
		Thread.sleep(3500);
		String messagePlayer1 = driver1.switchTo().alert().getText();
		
		Thread.sleep(3500);
		String messagePlayer2 = driver2.switchTo().alert().getText();
		
		String expected = "Draw!";
		
		assertEquals(expected, messagePlayer1);
		assertEquals(expected, messagePlayer2);
	}

}
