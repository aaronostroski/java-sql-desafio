package paripassu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TesteQualidade {
    private FirefoxDriver driver;

    @BeforeClass
    public void inicializa() {
    	System.setProperty("webdriver.gecko.driver","C:\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("http://clicq.paripassu.com.br");
    }

    @Test
    public void login() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.id("usuario")).sendKeys("xxxxxxx@paripassu.com.br");
        driver.findElement(By.id("password")).sendKeys("xxx");

        driver.findElement(By.id("submit-login")).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(driver.findElement(By.className("logo-header"))));
        Thread.sleep(1000);
    }

    @Test(dependsOnMethods = {"login"})
    public void acessarMenuNovaAplicacao() throws InterruptedException {

        driver.findElement(By.xpath("//ul[contains(@class, 'navbar-nav')]/li[2]/a")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//a[contains(@ui-sref, 'main.aplicacao_questionario-escolha-questionario')]")).click();
        Thread.sleep(1000);
    }

    @Test(dependsOnMethods = {"acessarMenuNovaAplicacao"})
    public void escolherQuestionario() throws InterruptedException {
    	
        driver.findElement(By.xpath("//li[contains(@class, 'item-origem')][1]/a")).click();
        Thread.sleep(1000);
    }

    @Test(dependsOnMethods = {"escolherQuestionario"})
    public void escolherAvaliado() throws InterruptedException {
    	
    	WebElement chooseQuiz = driver.findElement(By.xpath("//li[(@id='tipo-avaliado-item-avaliado-8')]"));
    	chooseQuiz.click();
    	Thread.sleep(1000);
    }

    @Test(dependsOnMethods = {"escolherAvaliado"})
    public void preencherQuestionario() throws InterruptedException {
    	
    	// Primeira parte formulário
    	
    	// Primeira questão: Nome
    	WebElement nameTextArea = driver.findElement(By.xpath(".//*[@id='questao-texto-input-texto']"));
    	nameTextArea.sendKeys("Aaron de Melo Ostroski");
    	Thread.sleep(1000);
    	
    	// Primeira questão: E-mail
    	WebElement emailTextArea = driver.findElement(By.xpath(".//*[@id='questao-email-input-email']"));
    	emailTextArea.sendKeys("aaronmostroski@gmail.com");
    	Thread.sleep(1000);
    	
    	// Primeira questão: Idade
    	WebElement ageTextArea = driver.findElement(By.xpath(".//*[@id='questao-inteiro-input-numero']"));
    	ageTextArea.sendKeys("22");
    	Thread.sleep(1000);
    	
    	// Trocar página
    	
    	WebElement nextButton = driver.findElement(By.xpath(".//*[@id='aplicacao-questionario-botao-proximo-2']"));
    	nextButton.click();
    	Thread.sleep(1000);
    	
    	// Segunda parte formulário
    	
    	// Primeira questão: "Por que você está interessado em trabalhar para esta empresa?" 
    	
    	String aswnerInterest = "Trabalhar com tecnologia envolvendo desenvolvimento e se desafiar em uma área que abrange tanto para o crescimento profissional"
    			+ " e pessoal. A oportunidade de conhecer"
    			+ " uma nova equipe com profissionais para se espelhar, e crescer de acordo com a proposta da bolsa oferecida pela empresa para criar confiança. ";
    	
    	WebElement aboutInterest = driver.findElement(By.xpath("//div[@id='aplicacao-questionario-questao-0']/div/div/field-directive/textarea[@id='questao-texto-input-texto']"));
    	aboutInterest.sendKeys(aswnerInterest);
    	Thread.sleep(1000);
    	
    	//Segunda questão: "Por que você trabalha na área de qualidade de software?"
    	
    	String aswerSoftwareQA = "Já tinha usado o Selenium WebDriver anteriormente em um trabalho da faculdade, nessa época já tinha despertado uma certa curiosidade na área."
    			+ " Atualmente estou tendo contato com o Protractor em aplicações pessoais que faço com Angular, então a proximidade da área de testes com a de desenvolvimento me desperta"
    			+ " muito interesse.";
    	
    	WebElement aboutSoftwareQA = driver.findElement(By.xpath("//div[@id='aplicacao-questionario-questao-1']/div/div/field-directive/textarea[@id='questao-texto-input-texto']"));
    	aboutSoftwareQA.sendKeys(aswerSoftwareQA);
    	Thread.sleep(1000);
    	
    	//Terceira questão: "Como você lida com problemas?"
    	
    	String aswerSoftwareAnalystic = "Como tenho experiência com Suporte e Infraestrutura. Nessas passagens profissionais tinha muito contato com cliente/monitoramento e ação de"
    			+ " servidores, com essa vivência aprendi muito a detalhar tasks e solucionar problemas, sendo objetivo em captar as informações necessárias para a solução e resolver o"
    			+ " problema pela raiz para que não aconteça novamente.";
    	
    	WebElement aboutAnalystic = driver.findElement(By.xpath("//div[@id='aplicacao-questionario-questao-2']/div/div/field-directive/textarea[@id='questao-texto-input-texto']"));
    	aboutAnalystic.sendKeys(aswerSoftwareAnalystic);
    	Thread.sleep(1000);
    	
    	//Quarta questão: "Você se considera uma pessoa investigativa?" chosen-results
    	
    	WebElement aboutMe = driver.findElement(By.xpath("//div[@id='aplicacao-questionario-questao-3']//div//div//field-directive//div[@id='questao_simple_escolha_combobox_chosen']"));
    	aboutMe.click();
    	driver.findElement(By.xpath("//div[contains(@class,'chosen-drop')]/ul/li[3]")).click();
    	Thread.sleep(1000);
    	
    	//Finalizar e salvar
      
    	driver.findElement(By.id("aplicacao-questionario-botao-salvar-e-finalizar-2")).click();
    	Thread.sleep(1000);
    }

		@Test(dependsOnMethods = {"preencherQuestionario"})
		public void sair() throws InterruptedException {
		
		Thread.sleep(1000);
		driver.findElement(By.id("dropdownMenu2")).click();
		
		Thread.sleep(500);
		driver.findElement(By.xpath("//a[contains(@ng-click, 'acesso.sair()')]")).click();
    }
    
    

}
