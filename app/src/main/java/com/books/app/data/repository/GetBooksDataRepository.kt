package com.books.app.data.repository

import com.books.app.domain.model.Book
import com.books.app.domain.model.BookAndGenre
import com.books.app.domain.model.Books
import com.books.app.domain.repository.GetBooksRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetBooksDataRepository(
    val gson: Gson,
) : GetBooksRepository {

    override fun getBooks(): Flow<List<BookAndGenre>> {
        val book = gson.fromJson<Books>(
            "{\n" +
                    "\"books\": [\n" +
                    "{\n" +
                    "\"id\": 19,\n" +
                    "\"name\": \"The Sadndman\",\n" +
                    "\"author\": \"Neil Gdaiman\",\n" +
                    "\"summary\": \"Neil Gadiman's The Sandman was launched in 1989. This extremely popular\n" +
                    "series was bound into ten collections. Following Dream of the Endless, also known as\n" +
                    "Morpheus, Onieros and many other names, we explore a magical world filled with stories both\n" +
                    "horrific and beautiful.\",\n" +
                    "\"genre\": \"Fantasy\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/the_sandman.jpg?a\n" +
                    "lt=media&token=db0c3806-da65-4d96-ba4c-f6cf27a92cd3\",\n" +
                    "\"views\": \"400K\",\n" +
                    "\"likes\": \"30K\",\n" +
                    "\"quotes\": \"20K\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 0,\n" +
                    "\"name\": \"The Sandman\",\n" +
                    "\"author\": \"Neil Gaiman\",\n" +
                    "\"summary\": \"Neil Gaiman's The Sandman was launched in 1989. This extremely popular\n" +
                    "series was bound into ten collections. Following Dream of the Endless, also known as\n" +
                    "Morpheus, Onieros and many other names, we explore a magical world filled with stories both\n" +
                    "horrific and beautiful.\",\n" +
                    "\"genre\": \"Fantasy\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/the_sandman.jpg?a\n" +
                    "lt=media&token=db0c3806-da65-4d96-ba4c-f6cf27a92cd3\",\n" +
                    "\"views\": \"400K\",\n" +
                    "\"likes\": \"30K\",\n" +
                    "\"quotes\": \"20K\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 1,\n" +
                    "\"name\": \"American Gods\",\n" +
                    "\"author\": \"Neil Gaiman\",\n" +
                    "\"summary\": \"Locked behind bars for three years, Shadow did his time, quietly waiting for the\n" +
                    "day when he could return to Eagle Point, Indiana. A man no longer scared of what tomorrow\n" +
                    "might bring, all he wanted was to be with Laura, the wife he deeply loved, and start a new\n" +
                    "life.But just days before his release, Laura and Shadow's best friend are killed in an accident.\n" +
                    "\n" +
                    "With his life in pieces and nothing to keep him tethered, Shadow accepts a job from a beguiling\n" +
                    "stranger he meets on the way home, an enigmatic man who calls himself Mr. Wednesday. A\n" +
                    "trickster and a rogue, Wednesday seems to know more about Shadow than Shadow does\n" +
                    "himself.\",\n" +
                    "\"genre\": \"Fantasy\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/american_gods.jpg\n" +
                    "?alt=media&token=faabd9a5-8f3a-4253-b6c6-8da845d28b25\",\n" +
                    "\"views\": \"310К\",\n" +
                    "\"likes\": \"52K\",\n" +
                    "\"quotes\": \"10K\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 2,\n" +
                    "\"name\": \"Dune\",\n" +
                    "\"author\": \"Frank Herbert\",\n" +
                    "\"summary\": \"Set on the desert planet Arrakis, Dune is the story of the boy Paul Atreides,\n" +
                    "who would become the mysterious man known as Maud'dib. He would avenge the traitorous\n" +
                    "plot against his noble family - and would bring to fruition humankind's most ancient and\n" +
                    "unattainable dream.\",\n" +
                    "\"genre\": \"Fantasy\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/dune.jpg?alt=media\n" +
                    "&token=54dc04b9-665b-41de-9cf2-b3062f649296\",\n" +
                    "\"views\": \"210К\",\n" +
                    "\"likes\": \"20K\",\n" +
                    "\"quotes\": \"50K\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 3,\n" +
                    "\"name\": \"Ready Player One\",\n" +
                    "\"author\": \"Ernest Cline\",\n" +
                    "\"summary\": \"At once wildly original and stuffed with irresistible nostalgia, Ready Player One\n" +
                    "is a spectacularly genre-busting, ambitious, and charming debut - part quest novel, part love\n" +
                    "story, and part virtual space opera set in a universe where spell-slinging mages battle giant\n" +
                    "Japanese robots, entire planets are inspired by Blade Runner, and flying DeLoreans achieve\n" +
                    "light speed.\",\n" +
                    "\"genre\": \"Science\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/ready_player_one.j\n" +
                    "pg?alt=media&token=3c28d6d1-a949-4989-ae07-0218c44acdd6\",\n" +
                    "\"views\": \"610К\",\n" +
                    "\"likes\": \"40K\",\n" +
                    "\"quotes\": \"20K\"\n" +
                    "\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 4,\n" +
                    "\"name\": \"Armada: A Novel\",\n" +
                    "\"author\": \"Ernest Cline\",\n" +
                    "\"summary\": \"he new novel from the best-selling author of Ready Player One. It's just another\n" +
                    "day of high school for Zack Lightman. He's daydreaming through another boring math class,\n" +
                    "with just one more month to go until graduation and freedom - if he can make it that long without\n" +
                    "getting suspended again. Then he glances out his classroom window and spots the flying\n" +
                    "saucer.\",\n" +
                    "\"genre\": \"Science\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/armada.jpg?alt=me\n" +
                    "dia&token=6f8bf742-f306-4fdb-bdf7-b57b9d0664ed\",\n" +
                    "\"views\": \"150К\",\n" +
                    "\"likes\": \"10K\",\n" +
                    "\"quotes\": \"80K\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 5,\n" +
                    "\"name\": \"The Hitchhiker's Guide to the Galaxy\",\n" +
                    "\"author\": \"Douglas Adams\",\n" +
                    "\"summary\": \"Seconds before the Earth is demolished to make way for a galactic freeway,\n" +
                    "Arthur Dent is plucked off the planet by his friend Ford Prefect, a researcher for the revised\n" +
                    "edition of The Hitchhiker's Guide to the Galaxy who, for the last 15 years, has been posing as\n" +
                    "an out-of-work actor.\",\n" +
                    "\"genre\": \"Science\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/the_hitchhikers_gui\n" +
                    "de.jpg?alt=media&token=6a8635ea-2e7e-428b-815d-5fb77fbe978f\",\n" +
                    "\"views\": \"400К\",\n" +
                    "\"likes\": \"100K\",\n" +
                    "\"quotes\": \"50K\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 6,\n" +
                    "\"name\": \"Divergent\",\n" +
                    "\"author\": \"Veronica Roth\",\n" +
                    "\"summary\": \"In Beatrice Prior's dystopian Chicago, society is divided into five factions, each\n" +
                    "dedicated to the cultivation of a particular virtue - Candor (the honest), Abnegation (the selfless),\n" +
                    "Dauntless (the brave), Amity (the peaceful), and Erudite (the intelligent). On an appointed day of\n" +
                    "every year, all sixteen-year-olds must select the faction to which they will devote the rest of their\n" +
                    "lives. For Beatrice, the decision is between staying with her family and being who she really is -\n" +
                    "she can't have both. So she makes a choice that surprises everyone, including herself.\",\n" +
                    "\n" +
                    "\"genre\": \"Romance\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/divergent.jpg?alt=m\n" +
                    "edia&token=29f5e20a-107a-4025-9733-1f3112114551\",\n" +
                    "\"views\": \"600К\",\n" +
                    "\"likes\": \"50K\",\n" +
                    "\"quotes\": \"10K\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 7,\n" +
                    "\"name\": \"Project Hail Mary\",\n" +
                    "\"author\": \"Andy Weir\",\n" +
                    "\"summary\": \"Ryland Grace is the sole survivor on a desperate, last-chance mission - and if\n" +
                    "he fails, humanity and the Earth itself will perish. Except that right now, he doesn't know that. He\n" +
                    "can't even remember his own name, let alone the nature of his assignment or how to complete\n" +
                    "it. All he knows is that he's been asleep for a very, very long time. And he's just been awakened\n" +
                    "to find himself millions of miles from home, with nothing but two corpses for company.\",\n" +
                    "\"genre\": \"Romance\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/project_hail_mary.j\n" +
                    "pg?alt=media&token=e7e04f6d-32ad-4d17-868a-30436986ed81\",\n" +
                    "\"views\": \"210К\",\n" +
                    "\"likes\": \"20K\",\n" +
                    "\"quotes\": \"50K\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 8,\n" +
                    "\"name\": \"Leviathan Wakes\",\n" +
                    "\"author\": \"James S. A. Corey\",\n" +
                    "\"summary\": \"The first book in the landmark Expanse series. Leviathan Wakes is James S. A.\n" +
                    "Corey's first novel in the epic New York Times best-selling series The Expanse, a modern\n" +
                    "masterwork of science fiction in which humanity has colonized the solar system. Two hundred\n" +
                    "years after migrating into space, mankind is in turmoil. When a reluctant ship's captain and\n" +
                    "washed-up detective find themselves involved in the case of a missing girl, what they discover\n" +
                    "brings our solar system to the brink of civil war and exposes the greatest conspiracy in human\n" +
                    "history. \",\n" +
                    "\"genre\": \"Romance\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/leviathan_wakes.jp\n" +
                    "g?alt=media&token=cec55caf-0eb2-4c14-8d69-fce35b49a164\",\n" +
                    "\"views\": \"150К\",\n" +
                    "\"likes\": \"30K\",\n" +
                    "\"quotes\": \"50K\"\n" +
                    "}\n" +
                    "\n" +
                    "],\n" +
                    "\"top_banner_slides\": [\n" +
                    "{\n" +
                    "\"id\": 0,\n" +
                    "\"book_id\": 2,\n" +
                    "\"cover\": \"https://unsplash.it/600/300\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 1,\n" +
                    "\"book_id\": 3,\n" +
                    "\"cover\": \"https://unsplash.it/600/400\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 2,\n" +
                    "\"book_id\": 5,\n" +
                    "\"cover\": \"https://unsplash.it/600/500\"\n" +
                    "}\n" +
                    "],\n" +
                    "\"you_will_like_section\": [\n" +
                    "4,\n" +
                    "6,\n" +
                    "8\n" +
                    "]\n" +
                    "}", Books::class.java
        )

        val genres = book.books.map { it.genre }
        val booksAndGenres = mutableSetOf<BookAndGenre>()
        genres.forEach { genre ->
            val books = book.books.filter { it.genre == genre }
            val bookAndGenre = BookAndGenre(
                genre = genre,
                books = books
            )
            booksAndGenres.add(bookAndGenre)
        }
        return flowOf(booksAndGenres.toList())
    }

    override fun getBooksByGenre(genre: String): Flow<List<Book>> {
        val book = gson.fromJson(
            "{\n" +
                    "\"books\": [\n" +
                    "{\n" +
                    "\"id\": 19,\n" +
                    "\"name\": \"The Sadndman\",\n" +
                    "\"author\": \"Neil Gdaiman\",\n" +
                    "\"summary\": \"Neil Gadiman's The Sandman was launched in 1989. This extremely popular\n" +
                    "series was bound into ten collections. Following Dream of the Endless, also known as\n" +
                    "Morpheus, Onieros and many other names, we explore a magical world filled with stories both\n" +
                    "horrific and beautiful.\",\n" +
                    "\"genre\": \"Fantasy\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/the_sandman.jpg?a\n" +
                    "lt=media&token=db0c3806-da65-4d96-ba4c-f6cf27a92cd3\",\n" +
                    "\"views\": \"400K\",\n" +
                    "\"likes\": \"30K\",\n" +
                    "\"quotes\": \"20K\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 0,\n" +
                    "\"name\": \"The Sandman\",\n" +
                    "\"author\": \"Neil Gaiman\",\n" +
                    "\"summary\": \"Neil Gaiman's The Sandman was launched in 1989. This extremely popular\n" +
                    "series was bound into ten collections. Following Dream of the Endless, also known as\n" +
                    "Morpheus, Onieros and many other names, we explore a magical world filled with stories both\n" +
                    "horrific and beautiful.\",\n" +
                    "\"genre\": \"Fantasy\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/the_sandman.jpg?a\n" +
                    "lt=media&token=db0c3806-da65-4d96-ba4c-f6cf27a92cd3\",\n" +
                    "\"views\": \"400K\",\n" +
                    "\"likes\": \"30K\",\n" +
                    "\"quotes\": \"20K\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 1,\n" +
                    "\"name\": \"American Gods\",\n" +
                    "\"author\": \"Neil Gaiman\",\n" +
                    "\"summary\": \"Locked behind bars for three years, Shadow did his time, quietly waiting for the\n" +
                    "day when he could return to Eagle Point, Indiana. A man no longer scared of what tomorrow\n" +
                    "might bring, all he wanted was to be with Laura, the wife he deeply loved, and start a new\n" +
                    "life.But just days before his release, Laura and Shadow's best friend are killed in an accident.\n" +
                    "\n" +
                    "With his life in pieces and nothing to keep him tethered, Shadow accepts a job from a beguiling\n" +
                    "stranger he meets on the way home, an enigmatic man who calls himself Mr. Wednesday. A\n" +
                    "trickster and a rogue, Wednesday seems to know more about Shadow than Shadow does\n" +
                    "himself.\",\n" +
                    "\"genre\": \"Fantasy\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/american_gods.jpg\n" +
                    "?alt=media&token=faabd9a5-8f3a-4253-b6c6-8da845d28b25\",\n" +
                    "\"views\": \"310К\",\n" +
                    "\"likes\": \"52K\",\n" +
                    "\"quotes\": \"10K\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 2,\n" +
                    "\"name\": \"Dune\",\n" +
                    "\"author\": \"Frank Herbert\",\n" +
                    "\"summary\": \"Set on the desert planet Arrakis, Dune is the story of the boy Paul Atreides,\n" +
                    "who would become the mysterious man known as Maud'dib. He would avenge the traitorous\n" +
                    "plot against his noble family - and would bring to fruition humankind's most ancient and\n" +
                    "unattainable dream.\",\n" +
                    "\"genre\": \"Fantasy\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/dune.jpg?alt=media\n" +
                    "&token=54dc04b9-665b-41de-9cf2-b3062f649296\",\n" +
                    "\"views\": \"210К\",\n" +
                    "\"likes\": \"20K\",\n" +
                    "\"quotes\": \"50K\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 3,\n" +
                    "\"name\": \"Ready Player One\",\n" +
                    "\"author\": \"Ernest Cline\",\n" +
                    "\"summary\": \"At once wildly original and stuffed with irresistible nostalgia, Ready Player One\n" +
                    "is a spectacularly genre-busting, ambitious, and charming debut - part quest novel, part love\n" +
                    "story, and part virtual space opera set in a universe where spell-slinging mages battle giant\n" +
                    "Japanese robots, entire planets are inspired by Blade Runner, and flying DeLoreans achieve\n" +
                    "light speed.\",\n" +
                    "\"genre\": \"Science\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/ready_player_one.j\n" +
                    "pg?alt=media&token=3c28d6d1-a949-4989-ae07-0218c44acdd6\",\n" +
                    "\"views\": \"610К\",\n" +
                    "\"likes\": \"40K\",\n" +
                    "\"quotes\": \"20K\"\n" +
                    "\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 4,\n" +
                    "\"name\": \"Armada: A Novel\",\n" +
                    "\"author\": \"Ernest Cline\",\n" +
                    "\"summary\": \"he new novel from the best-selling author of Ready Player One. It's just another\n" +
                    "day of high school for Zack Lightman. He's daydreaming through another boring math class,\n" +
                    "with just one more month to go until graduation and freedom - if he can make it that long without\n" +
                    "getting suspended again. Then he glances out his classroom window and spots the flying\n" +
                    "saucer.\",\n" +
                    "\"genre\": \"Science\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/armada.jpg?alt=me\n" +
                    "dia&token=6f8bf742-f306-4fdb-bdf7-b57b9d0664ed\",\n" +
                    "\"views\": \"150К\",\n" +
                    "\"likes\": \"10K\",\n" +
                    "\"quotes\": \"80K\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 5,\n" +
                    "\"name\": \"The Hitchhiker's Guide to the Galaxy\",\n" +
                    "\"author\": \"Douglas Adams\",\n" +
                    "\"summary\": \"Seconds before the Earth is demolished to make way for a galactic freeway,\n" +
                    "Arthur Dent is plucked off the planet by his friend Ford Prefect, a researcher for the revised\n" +
                    "edition of The Hitchhiker's Guide to the Galaxy who, for the last 15 years, has been posing as\n" +
                    "an out-of-work actor.\",\n" +
                    "\"genre\": \"Science\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/the_hitchhikers_gui\n" +
                    "de.jpg?alt=media&token=6a8635ea-2e7e-428b-815d-5fb77fbe978f\",\n" +
                    "\"views\": \"400К\",\n" +
                    "\"likes\": \"100K\",\n" +
                    "\"quotes\": \"50K\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 6,\n" +
                    "\"name\": \"Divergent\",\n" +
                    "\"author\": \"Veronica Roth\",\n" +
                    "\"summary\": \"In Beatrice Prior's dystopian Chicago, society is divided into five factions, each\n" +
                    "dedicated to the cultivation of a particular virtue - Candor (the honest), Abnegation (the selfless),\n" +
                    "Dauntless (the brave), Amity (the peaceful), and Erudite (the intelligent). On an appointed day of\n" +
                    "every year, all sixteen-year-olds must select the faction to which they will devote the rest of their\n" +
                    "lives. For Beatrice, the decision is between staying with her family and being who she really is -\n" +
                    "she can't have both. So she makes a choice that surprises everyone, including herself.\",\n" +
                    "\n" +
                    "\"genre\": \"Romance\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/divergent.jpg?alt=m\n" +
                    "edia&token=29f5e20a-107a-4025-9733-1f3112114551\",\n" +
                    "\"views\": \"600К\",\n" +
                    "\"likes\": \"50K\",\n" +
                    "\"quotes\": \"10K\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 7,\n" +
                    "\"name\": \"Project Hail Mary\",\n" +
                    "\"author\": \"Andy Weir\",\n" +
                    "\"summary\": \"Ryland Grace is the sole survivor on a desperate, last-chance mission - and if\n" +
                    "he fails, humanity and the Earth itself will perish. Except that right now, he doesn't know that. He\n" +
                    "can't even remember his own name, let alone the nature of his assignment or how to complete\n" +
                    "it. All he knows is that he's been asleep for a very, very long time. And he's just been awakened\n" +
                    "to find himself millions of miles from home, with nothing but two corpses for company.\",\n" +
                    "\"genre\": \"Romance\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/project_hail_mary.j\n" +
                    "pg?alt=media&token=e7e04f6d-32ad-4d17-868a-30436986ed81\",\n" +
                    "\"views\": \"210К\",\n" +
                    "\"likes\": \"20K\",\n" +
                    "\"quotes\": \"50K\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 8,\n" +
                    "\"name\": \"Leviathan Wakes\",\n" +
                    "\"author\": \"James S. A. Corey\",\n" +
                    "\"summary\": \"The first book in the landmark Expanse series. Leviathan Wakes is James S. A.\n" +
                    "Corey's first novel in the epic New York Times best-selling series The Expanse, a modern\n" +
                    "masterwork of science fiction in which humanity has colonized the solar system. Two hundred\n" +
                    "years after migrating into space, mankind is in turmoil. When a reluctant ship's captain and\n" +
                    "washed-up detective find themselves involved in the case of a missing girl, what they discover\n" +
                    "brings our solar system to the brink of civil war and exposes the greatest conspiracy in human\n" +
                    "history. \",\n" +
                    "\"genre\": \"Romance\",\n" +
                    "\"cover_url\":\n" +
                    "\"https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/leviathan_wakes.jp\n" +
                    "g?alt=media&token=cec55caf-0eb2-4c14-8d69-fce35b49a164\",\n" +
                    "\"views\": \"150К\",\n" +
                    "\"likes\": \"30K\",\n" +
                    "\"quotes\": \"50K\"\n" +
                    "}\n" +
                    "\n" +
                    "],\n" +
                    "\"top_banner_slides\": [\n" +
                    "{\n" +
                    "\"id\": 0,\n" +
                    "\"book_id\": 2,\n" +
                    "\"cover\": \"https://unsplash.it/600/300\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 1,\n" +
                    "\"book_id\": 3,\n" +
                    "\"cover\": \"https://unsplash.it/600/400\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": 2,\n" +
                    "\"book_id\": 5,\n" +
                    "\"cover\": \"https://unsplash.it/600/500\"\n" +
                    "}\n" +
                    "],\n" +
                    "\"you_will_like_section\": [\n" +
                    "4,\n" +
                    "6,\n" +
                    "8\n" +
                    "]\n" +
                    "}", Books::class.java
        )

        val genres = book.books.map { it.genre }
        val booksAndGenres = mutableSetOf<BookAndGenre>()
        genres.forEach { genreType ->
            val books = book.books.filter { it.genre == genreType }
            val bookAndGenre = BookAndGenre(
                genre = genreType,
                books = books
            )
            booksAndGenres.add(bookAndGenre)
        }

        return flowOf(booksAndGenres.find { it.genre == genre }?.books ?: emptyList())
    }
}