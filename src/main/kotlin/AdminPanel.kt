import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class AdminPanel(val userNameField: By = By.id("id_username"),
                 val passwordField: By = By.id("id_password"),
                 val loginButton: By = By.cssSelector("[type='submit']"),
                 val driver: WebDriver = ChromeDriver() ) {
    // Логин
    fun typeUsername(username: String) {
        return driver.findElement(userNameField).sendKeys(username)
    }
    fun typePassword(password: String) {
        return driver.findElement(passwordField).sendKeys(password)
    }
    fun clickLoginButton(){
        driver.findElement(loginButton).click()
    }
    fun loginAs(username: String, password: String) {
        typeUsername(username)
        typePassword(password)
        return clickLoginButton()
    }

    //Проверка элемента внутри класса
    fun checkTitle(elementToCheck: String): Boolean {
        val find = driver.findElement( By.cssSelector( "div#content h1" ) )
        val title: String = find.text
        return title == elementToCheck
    }

    //Нажатие кнопки Добавить
    fun clickAddEntries() {
        val link = "href='/admin/blog/entry/add/'"
        driver.findElement(By.cssSelector( "a[$link]" )).click()
    }

    //Заполнение полей
    fun typeTitle(title: String) {
        return driver.findElement(By.id("id_title")).sendKeys( title )
    }
    fun typeSlug(slug: String) {
        return driver.findElement(By.id("id_slug")).sendKeys( slug )
    }
    fun typeTextMarkdown(textMarkdown: String) {
        return driver.findElement(By.id("id_text_markdown")).sendKeys( textMarkdown )
    }
    fun typeText(text: String) {
        return driver.findElement(By.id("id_text")).sendKeys( text )
    }
    fun clickSaveButton() {
        driver.findElement(By.cssSelector("[name='_save']")).click()
    }

    // Создание и сохранение записи
    fun makeNote(title: String, slug: String, textMarkdown: String, text: String) {
        typeTitle( title )
        typeSlug( slug )
        typeTextMarkdown( textMarkdown )
        typeText( text )
        clickSaveButton()
        println("Запись успешно создана")
    }

    //Удаление записи
    fun deleteNote(title: String) {
        driver.findElement(By.xpath("//th[a[text() = '$title']]/preceding-sibling::td")).click()
        driver.findElement(By.xpath("//select")).click()
        driver.findElement(By.cssSelector("[value='delete_selected']")).click()
        driver.findElement(By.cssSelector("[title='Выполнить выбранное действие']")).click()
        driver.findElement(By.cssSelector("[value='Да, я уверен']")).click()
        driver.close()
        println("Запись успешно удалена")
    }



}