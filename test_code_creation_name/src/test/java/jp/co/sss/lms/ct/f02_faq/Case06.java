package jp.co.sss.lms.ct.f02_faq;

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
 * 結合テスト よくある質問機能
 * ケース06
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース06 カテゴリ検索 正常系")
public class Case06 {

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
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// TODO ここに追加
		WebElement menu = webDriver.findElement(By.cssSelector("a.dropdown-toggle"));
		String title = "ヘルプ | LMS";

		menu.click();

		WebElement help = webDriver.findElement(By.linkText("ヘルプ"));
		help.click();

		//ヘルプの画面が表示されているか検証
		assertEquals(title, webDriver.getTitle());

		//エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		// TODO ここに追加
		WebElement question = webDriver.findElement(By.linkText("よくある質問"));
		String title = "よくある質問 | LMS";

		question.click();

		// ウィンドウハンドルの取得
		Object[] windowHandles = webDriver.getWindowHandles().toArray();

		// １が割り振られたタブへ移動する。（新しく開かれたよくある質問ページのタブ）
		webDriver.switchTo().window((String) windowHandles[1]);

		//よくある質問のページが表示されているか検証
		assertEquals(title, webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 カテゴリ検索で該当カテゴリの検索結果だけ表示")
	void test05() {
		// TODO ここに追加
		WebElement categoryLink = webDriver.findElement(By.linkText("【人材開発支援助成金】"));

		categoryLink.click();

		//「Q.」を除いた全ての検索結果をリスト化する。
		List<WebElement> categoryResults = webDriver.findElements(By.cssSelector("dt.mb10 span:not(.mr10)"));

		String[] checkResults = { "セルフ・キャリアドック制度とは何か",
				"事業所が変わった場合、何かしら手続きをする必要がありますか？",
				"助成金書類の作成方法が分かりません" };

		//List化した全ての検索結果を拡張for文を用いて該当カテゴリのものか検証
		for (WebElement inspection : categoryResults) {
			assertThat(checkResults).contains(inspection.getText());
		}

		scrollBy("160");

		getEvidence(new Object() {
		});

	}

	@Test
	@Order(6)
	@DisplayName("テスト06 検索結果の質問をクリックしその回答を表示")
	void test06() {
		// TODO ここに追加
		WebElement question01 = webDriver.findElement(By.xpath("//span[text()='セルフ・キャリアドック制度とは何か']"));

		question01.click();

		WebElement answer = webDriver.findElement(By.cssSelector("dd.fs18:nth-of-type(1)"));

		//answerが画面上に表示されているか検証
		assertTrue(answer.isDisplayed());

		getEvidence(new Object() {
		});
	}

}
