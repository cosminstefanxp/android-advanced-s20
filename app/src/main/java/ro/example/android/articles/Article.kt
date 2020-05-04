package ro.example.android.articles

data class Article(
    val id: Int,
    val name: String,
    val category: String,
    val isRed: Boolean
)

public val testArticles =
    (1..100)
        .map { number ->
            Article(
                id = number,
                name = "Article $number",
                category = "Category ${number / 2}",
                isRed = number % 3 == 0
            )
        }
