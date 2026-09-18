package jp.co.sss.lms.ct.f01_login1;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 結合テスト ログイン機能①
 * ケース02
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース02 受講生 ログイン 認証失敗")
public class Case02 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		// TODO ここに追加

		String url = "http://localhost:8080/lms/";

		String title = "ログイン | LMS";

		//トップページへ遷移
		goTo(url);

		//ページタイトルが想定のものと一致するか検証
		assertEquals(title, webDriver.getTitle());

		//エビデンス取得
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(2)
	@DisplayName("テスト02 DBに登録されていないユーザーでログイン")
	void test02() {
		// TODO ここに追加

		WebElement userId = webDriver.findElement(By.id("loginId"));
		WebElement password = webDriver.findElement(By.id("password"));
		WebElement loginButton = webDriver.findElement(By.cssSelector("input.btn-primary"));
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(60));

		userId.sendKeys("testId");
		password.sendKeys("testPass");

		loginButton.click();

		//エラーメッセージが表示されるまで処理を待機する
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("error")));

		getEvidence(new Object() {
		});

		//エラーメッセージが表示されているか検証
		assertNotNull(webDriver.findElement(By.className("error")));

	}

}
