fun main(){

    System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe")

    println("Начало теста")

    val username = "selenium"
    val password = "super_password"
    val urlAddress = "https://igorakintev.ru/admin/"

    val adminPanel = AdminPanel()
    adminPanel.driver.get( urlAddress )
    adminPanel.loginAs(username, password)

    // Проверка, что на новой странице присутствует заголовок "Панель управления"
    val elementToCheck = "Панель управления"
    var isTrue: Boolean = adminPanel.checkTitle( elementToCheck )
    if (isTrue) {
        println("Заголовок \"$elementToCheck\" найден.")
    } else {
        println("Не найден заголовок \"$elementToCheck\" ")
    }
    adminPanel.clickAddEntries()

    //Проверка, что на новой странице присутствует заголовок "Добавить entry"
    val elementToCheck2 = "Добавить entry"
    isTrue = adminPanel.checkTitle( elementToCheck2 )
    if (isTrue) {
        println("Заголовок \"$elementToCheck2\" найден.")
    } else {
    println("Не найден заголовок \"$elementToCheck2\" ")
    }

    //Создание записи в блоге
    val randomNumber = (0..9999999).random()

    val title = "title_" + randomNumber.toString()
    val slug = "slug_catcher" + randomNumber.toString()
    val textMarkdown = "textMarkdown" + randomNumber.toString()
    val text = "text..............................text" + randomNumber.toString()
    val note = listOf(title, slug, textMarkdown, text)

    adminPanel.makeNote(note[0], note[1], note[2], note[3])

    //Проверка записи в блоге
    val blog = Blog()
    val urlAddress2 = "https://igorakintev.ru/blog/"
    blog.driver.get( urlAddress2 )
    blog.findNote(note)

    //Удаление записи
    adminPanel.deleteNote(note[0])
    println("Конец теста")
}