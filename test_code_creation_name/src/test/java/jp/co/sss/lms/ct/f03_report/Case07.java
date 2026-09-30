package jp.co.sss.lms.ct.f03_report;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

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
 * 結合テスト レポート機能
 * ケース07
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース07 受講生 レポート新規登録(日報) 正常系")
public class Case07 {

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
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加
		WebElement userId = webDriver.findElement(By.id("loginId"));
		WebElement password = webDriver.findElement(By.id("password"));
		WebElement loginButton = webDriver.findElement(By.cssSelector("input.btn-primary"));
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(60));
		String title = "コース詳細 | LMS";

		userId.sendKeys("StudentAA01");
		password.sendKeys("testAA01");

		loginButton.click();

		//diのcssクラスが表示されるまで待機
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("di")));

		//ログイン後のコース詳細画面が表示されているか検証
		assertEquals(title, webDriver.getTitle());

		//エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 未提出の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// TODO ここに追加

		//各日付のコースを全件取得しリスト化
		List<WebElement> trs = webDriver.findElements(By.tagName("tr"));
		WebElement detailButton = null;
		String title = "セクション詳細 | LMS";

		//リストの中から最初に「未提出」となっているコースの詳細ボタンを取得
		for (WebElement tr : trs) {
			if (tr.getText().contains("未提出")) {
				detailButton = tr.findElement(By.cssSelector("input.btn"));
				break;
			}
		}

		detailButton.click();

		assertEquals(title, webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「提出する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// TODO ここに追加
		WebElement submitButton = webDriver.findElement(By.cssSelector("input.btn.btn-default"));
		String title = "レポート登録 | LMS";
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(60));

		submitButton.click();

		//レポート登録画面の「提出するボタン」が表示されるまで処理を待機
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn.btn-primary")));

		assertEquals(title, webDriver.getTitle());

		getEvidence(new Object() {
		});

	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を入力して「提出する」ボタンを押下し確認ボタン名が更新される")
	void test05() {
		// TODO ここに追加
		WebElement form = webDriver.findElement(By.cssSelector("textarea.form-control"));
		WebElement submitButton = webDriver.findElement(By.cssSelector("button.btn.btn-primary"));
		String text = "テスト";
		String newText = "確認する";

		form.sendKeys(text);

		submitButton.click();

		//提出ボタンが確認するボタンに切り替わっているか確認
		WebElement checkButton = webDriver.findElement(By.cssSelector("input.btn.btn-default"));
		assertThat(checkButton.getAttribute("value")).contains(newText);

		getEvidence(new Object() {
		});

	}

}
