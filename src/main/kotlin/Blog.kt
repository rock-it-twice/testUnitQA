import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class Blog(val driver: WebDriver = ChromeDriver()) {

    fun findNote(note: List<String>): Boolean {
        try {
            driver.findElement( By.xpath("//a[text()='${note[0]}']") )
            println("Созданная запись отображается на сайте")
            return true
        } catch (e: org.openqa.selenium.NoSuchElementException){
            println("Созданная запись не найдена")
            return false
        } finally {
            driver.close()
        }




    }
}