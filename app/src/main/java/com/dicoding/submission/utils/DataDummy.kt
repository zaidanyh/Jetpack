package com.dicoding.submission.utils

import com.dicoding.submission.data.local.entity.MoviesEntity
import com.dicoding.submission.data.local.entity.TvEntity
import com.dicoding.submission.data.remote.response.*

object DataDummy {
    fun generateMovie(): List<MoviesEntity> {
        val movie = ArrayList<MoviesEntity>()
        movie.add(
            MoviesEntity(632357,
                "https://image.tmdb.org/t/p/w1280/wwFBRyekDcKXJwP0mImRJjAnudL.jpg",
                "The Unholy",
                "2021-03-31",
        "https://image.tmdb.org/t/p/w1280/6wxfWZxQcuv2QgxIQKj0eYTdKTv.jpg",
                    "2021-03-31", 668, 7.1)
        )
        movie.add(
            MoviesEntity(337404,
                "https://image.tmdb.org/t/p/w1280/2sbe8qmdYNLQ8wprAXKDNTMbylZ.jpg",
                "Cruella",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "/A0knvX7rlwTyZSKj8H5NiARb45.jpg",
                "2021-05-26", 880,8.8)
        )
        movie.add(
            MoviesEntity(578701,
                "https://image.tmdb.org/t/p/w1280/iDdpiUnCeXvBmrkBFpL6lKsZt33.jpg",
                "Those Who Wish Me Dead",
                "A young boy finds himself pursued by two assassins in the Montana wilderness with a survival expert determined to protecting him - and a forest fire threatening to consume them all.",
                "https://image.tmdb.org/t/p/w1280/xCEg6KowNISWvMh8GvPSxtdf9TO.jpg",
                "2021-05-05", 338, 7.0)
        )
        movie.add(
            MoviesEntity(399566,
                "https://image.tmdb.org/t/p/w1280/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "Godzilla vs. Kong",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "https://image.tmdb.org/t/p/w1280/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "2021-03-24", 5732, 8.1)
        )
        movie.add(
            MoviesEntity(615457,
                "https://image.tmdb.org/t/p/w1280/sBwGOfJtSF6hlXaEgvFfBfeLqMk.jpg",
                "Nobody",
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "https://image.tmdb.org/t/p/w1280/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "2021-03-26", 1740,8.5)
        )
        movie.add(
            MoviesEntity(637649,
                "https://image.tmdb.org/t/p/w1280/77tui163estZrQ78NBggqDB4n2C.jpg",
                "Wrath of Man",
                "A cold and mysterious new security guard for a Los Angeles cash truck company surprises his co-workers when he unleashes precision skills during a heist. The crew is left wondering who he is and where he came from. Soon, the marksman's ultimate motive becomes clear as he takes dramatic and irrevocable steps to settle a score.",
                "https://image.tmdb.org/t/p/w1280/YxopfHpsCV1oF8CZaL4M3Eodqa.jpg",
                "2021-04-22", 440,7.9)
        )
        movie.add(
            MoviesEntity(520763,
                "https://image.tmdb.org/t/p/w1280/z2UtGA1WggESspi6KOXeo66lvLx.jpg",
                "A Quiet Place Part II",
                "Following the events at home, the Abbott family now face the terrors of the outside world. Forced to venture into the unknown, they realize that the creatures that hunt by sound are not the only threats that lurk beyond the sand path.",
                "https://image.tmdb.org/t/p/w1280/4q2hz2m8hubgvijz8Ez0T2Os2Yv.jpg",
                "2021-05-21",72,7.5)
        )
        movie.add(
            MoviesEntity(681260,
                "https://image.tmdb.org/t/p/w1280/7HtvmsLrDeiAgDGa1W3m6senpfE.jp",
                "Maya the Bee: The Golden Orb",
                "When Maya, a headstrong little bee, and her best friend Willi, rescue an ant princess they find themselves in the middle of an epic bug battle that will take them to strange new worlds and test their friendship to its limits.",
                "https://image.tmdb.org/t/p/w1280/tMS2qcbhbkFpcwLnbUE9o9IK4HH.jpg",
                "2021-01-07",32,6.6)
        )
        movie.add(
            MoviesEntity(458576,
                "https://image.tmdb.org/t/p/w1280/z8TvnEVRenMSTemxYZwLGqFofgF.jpg",
                "Monster Hunter",
                "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                "https://image.tmdb.org/t/p/w1280/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                "2020-12-03",1677,7.0)
        )
        movie.add(
            MoviesEntity(412656,
                "https://image.tmdb.org/t/p/w1280/5NxjLfs7Bi07bfZCRl9CCnUw7AA.jpg",
                "Chaos Walking",
                "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",
                "https://image.tmdb.org/t/p/w1280/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                "2021-02-24", 648,7.1)
        )
        return movie
    }

    fun generateDetailMovie(): MoviesEntity {
        return MoviesEntity(
            399566,
            "https://image.tmdb.org/t/p/w1280/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
            "Godzilla vs. Kong",
            "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
            "https://image.tmdb.org/t/p/w1280/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
            "2021-03-24",
            5765,
            8.1
        )
    }

    fun generateTV(): List<TvEntity> {
        val tv = ArrayList<TvEntity>()
        tv.add(
            TvEntity(71712,
                "https://image.tmdb.org/t/p/w1280/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "https://image.tmdb.org/t/p/w1280/iDbIEpCM9nhoayUDTwqFL1iVwzb.jpg",
                "The Good Doctor",
                "2017-09-25",
        "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
            8507,8.6)
        )
        tv.add(
            TvEntity(1416,
                "https://image.tmdb.org/t/p/w1280/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "https://image.tmdb.org/t/p/w1280/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                "Grey's Anatomy",
                "2005-03-27",
        "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
            6134,8.2)
        )
        tv.add(
            TvEntity(95057,
                "https://image.tmdb.org/t/p/w1280/vlv1gn98GqMnKHLSh0dNciqGfBl.jpg",
                "https://image.tmdb.org/t/p/w1280/pPKiIJEEcV0E1hpVcWRXyp73ZpX.jpg",
                "Superman & Lois",
                "2021-02-23",
            "After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society.",
            876,8.3)
        )
        tv.add(
            TvEntity(105971,
                "https://image.tmdb.org/t/p/w1280/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg",
                "https://image.tmdb.org/t/p/w1280/sjxtIUCWR74yPPcZFfTsToepfWm.jpg",
                "The Bad Batch",
                "2021-05-04",
                    "Follow the elite and experimental Clones of the Bad Batch as they find their way in a rapidly changing galaxy in the aftermath of the Clone Wars.",
            230,8.8)
        )
        tv.add(
            TvEntity(62286,
                "https://image.tmdb.org/t/p/w1280/4UjiPdFKJGJYdxwRs2Rzg7EmWqr.jpg",
                "https://image.tmdb.org/t/p/w1280/58PON1OrnBiX6CqEHgeWKVwrCn6.jpg",
                "Fear the Walking Dead",
                "2015-08-23",
            "What did the world look like as it was transforming into the horrifying apocalypse depicted in \\\"The Walking Dead\\\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
            3574,7.6)
        )
        tv.add(
            TvEntity(69478,
                "https://image.tmdb.org/t/p/w1280/oIkxqt6ug5zT5ZSUUyc1Iqopf02.jpg",
                "https://image.tmdb.org/t/p/w1280/jXB3OoWPkojsOP2O2OoLCeAIDRS.jpg",
                "The Handmaid's Tale",
                "2017-04-26",
            "Set in a dystopian future, a woman is forced to live as a concubine under a fundamentalist theocratic dictatorship. A TV adaptation of Margaret Atwood's novel.",
            1390,8.2)
        )
        tv.add(
            TvEntity(94667,
                "https://image.tmdb.org/t/p/w1280/o1lAdiCYmCuDb25wyBCJQMeUhVA.jpg",
                "https://image.tmdb.org/t/p/w1280/18ybGiTcKVnXCYCAmVBIIy8CNhx.jpg",
                "De viaje con los Derbez",
                "2019-10-18",
            "",
            915,7.5)
        )
        tv.add(
            TvEntity(2734,
                "https://image.tmdb.org/t/p/w1280/jDCgWVlejIo8sQYxw3Yf1cVQUIL.jpg",
                "https://image.tmdb.org/t/p/w1280/cD9PxbrdWYgL7MUpD9eOYuiSe2P.jpg",
                "Law & Order: Special Victims Unit",
                "1999-09-20",
            "In the criminal justice system, sexually-based offenses are considered especially heinous. In New York City, the dedicated detectives who investigate these vicious felonies are members of an elite squad known as the Special Victims Unit. These are their stories.",
            2436,7.8)
        )
        tv.add(
            TvEntity(65334,
                "https://image.tmdb.org/t/p/w1280/qxbW47zmgFyBVmZSIqD9NA1DEjT.jpg",
                "https://image.tmdb.org/t/p/w1280/9RqliZcoDEjSEISeA0LY9meAiNv.jpg",
                "Miraculous: Tales of Ladybug & Cat Noir",
                "2015-10-19",
            "Normal high school kids by day, protectors of Paris by night! Miraculous follows the heroic adventures of Marinette and Adrien as they transform into Ladybug and Cat Noir and set out to capture akumas, creatures responsible for turning the people of Paris into villains. But neither hero knows the other’s true identity – or that they’re classmates!",
            2672,7.9)
        )
        tv.add(
            TvEntity(65820,
                "https://image.tmdb.org/t/p/w1280/r8ODGmfNbZQlNhiJl2xQENE2jsk.jpg",
                "https://image.tmdb.org/t/p/w1280/5VltHQJXdmbSD6gEJw3R8R1Kbmc.jpg",
                "Van Helsing",
                "2016-09-23",
            "Vanessa Helsing, the daughter of famous vampire hunter and Dracula nemesis Abraham Van Helsing is resurrected five years in the future to find out that vampires have taken over the world and that she possesses unique power over them. She is humanity’s last hope to lead an offensive to take back what has been lost.",
            569,6.9)
        )
        return tv
    }

    fun generateDetailTv(): TvEntity {
        return TvEntity(105971,
            "https://image.tmdb.org/t/p/w1280/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg",
            "https://image.tmdb.org/t/p/w1280/sjxtIUCWR74yPPcZFfTsToepfWm.jpg",
            "The Bad Batch",
            "2021-05-04",
            "Follow the elite and experimental Clones of the Bad Batch as they find their way in a rapidly changing galaxy in the aftermath of the Clone Wars.",
            230,8.8
        )
    }

    fun generateRemoteDummyMovie(): List<ResultMovie> {
        val movies = ArrayList<ResultMovie>()
        movies.add(
            ResultMovie(632357,
                "https://image.tmdb.org/t/p/w1280/wwFBRyekDcKXJwP0mImRJjAnudL.jpg",
                "The Unholy",
                "2021-03-31",
                "https://image.tmdb.org/t/p/w1280/6wxfWZxQcuv2QgxIQKj0eYTdKTv.jpg",
                "2021-03-31", 668, 7.1)
        )
        movies.add(
            ResultMovie(337404,
                "https://image.tmdb.org/t/p/w1280/2sbe8qmdYNLQ8wprAXKDNTMbylZ.jpg",
                "Cruella",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "https://image.tmdb.org/t/p/w1280/A0knvX7rlwTyZSKj8H5NiARb45.jpg",
                "2021-05-26", 880,8.8)
        )
        movies.add(
            ResultMovie(578701,
                "https://image.tmdb.org/t/p/w1280/iDdpiUnCeXvBmrkBFpL6lKsZt33.jpg",
                "Those Who Wish Me Dead",
                "A young boy finds himself pursued by two assassins in the Montana wilderness with a survival expert determined to protecting him - and a forest fire threatening to consume them all.",
                "https://image.tmdb.org/t/p/w1280/xCEg6KowNISWvMh8GvPSxtdf9TO.jpg",
                "2021-05-05", 338, 7.0)
        )
        movies.add(
            ResultMovie(399566,
                "https://image.tmdb.org/t/p/w1280/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "Godzilla vs. Kong",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "https://image.tmdb.org/t/p/w1280/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "2021-03-24", 5732, 8.1)
        )
        movies.add(
            ResultMovie(615457,
                "https://image.tmdb.org/t/p/w1280/sBwGOfJtSF6hlXaEgvFfBfeLqMk.jpg",
                "Nobody",
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "https://image.tmdb.org/t/p/w1280/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "2021-03-26", 1740,8.5)
        )
        movies.add(
            ResultMovie(637649,
                "https://image.tmdb.org/t/p/w1280/77tui163estZrQ78NBggqDB4n2C.jpg",
                "Wrath of Man",
                "A cold and mysterious new security guard for a Los Angeles cash truck company surprises his co-workers when he unleashes precision skills during a heist. The crew is left wondering who he is and where he came from. Soon, the marksman's ultimate motive becomes clear as he takes dramatic and irrevocable steps to settle a score.",
                "https://image.tmdb.org/t/p/w1280/YxopfHpsCV1oF8CZaL4M3Eodqa.jpg",
                "2021-04-22", 440,7.9)
        )
        movies.add(
            ResultMovie(520763,
                "https://image.tmdb.org/t/p/w1280/z2UtGA1WggESspi6KOXeo66lvLx.jpg",
                "A Quiet Place Part II",
                "Following the events at home, the Abbott family now face the terrors of the outside world. Forced to venture into the unknown, they realize that the creatures that hunt by sound are not the only threats that lurk beyond the sand path.",
                "https://image.tmdb.org/t/p/w1280/4q2hz2m8hubgvijz8Ez0T2Os2Yv.jpg",
                "2021-05-21",72,7.5)
        )
        movies.add(
            ResultMovie(681260,
                "https://image.tmdb.org/t/p/w1280/7HtvmsLrDeiAgDGa1W3m6senpfE.jp",
                "Maya the Bee: The Golden Orb",
                "When Maya, a headstrong little bee, and her best friend Willi, rescue an ant princess they find themselves in the middle of an epic bug battle that will take them to strange new worlds and test their friendship to its limits.",
                "https://image.tmdb.org/t/p/w1280/tMS2qcbhbkFpcwLnbUE9o9IK4HH.jpg",
                "2021-01-07",32,6.6)
        )
        movies.add(
            ResultMovie(458576,
                "https://image.tmdb.org/t/p/w1280/z8TvnEVRenMSTemxYZwLGqFofgF.jpg",
                "Monster Hunter",
                "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                "https://image.tmdb.org/t/p/w1280/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                "2020-12-03",1677,7.0)
        )
        movies.add(
            ResultMovie(412656,
                "https://image.tmdb.org/t/p/w1280/5NxjLfs7Bi07bfZCRl9CCnUw7AA.jpg",
                "Chaos Walking",
                "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",
                "https://image.tmdb.org/t/p/w1280/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                "2021-02-24", 648,7.1)
        )
        return movies
    }

    fun generateRemoteDummyTvShow(): List<ResultTvShow> {
        val tvShows = ArrayList<ResultTvShow>()
        tvShows.add(
            ResultTvShow(71712,
                "https://image.tmdb.org/t/p/w1280/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "https://image.tmdb.org/t/p/w1280/iDbIEpCM9nhoayUDTwqFL1iVwzb.jpg",
                "The Good Doctor",
                "2017-09-25",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                8507,8.6)
        )
        tvShows.add(
            ResultTvShow(1416,
                "https://image.tmdb.org/t/p/w1280/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "https://image.tmdb.org/t/p/w1280/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                "Grey's Anatomy",
                "2005-03-27",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                6134,8.2)
        )
        tvShows.add(
            ResultTvShow(95057,
                "https://image.tmdb.org/t/p/w1280/vlv1gn98GqMnKHLSh0dNciqGfBl.jpg",
                "https://image.tmdb.org/t/p/w1280/pPKiIJEEcV0E1hpVcWRXyp73ZpX.jpg",
                "Superman & Lois",
                "2021-02-23",
                "After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society.",
                876,8.3)
        )
        tvShows.add(
            ResultTvShow(105971,
                "https://image.tmdb.org/t/p/w1280/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg",
                "https://image.tmdb.org/t/p/w1280/sjxtIUCWR74yPPcZFfTsToepfWm.jpg",
                "The Bad Batch",
                "2021-05-04",
                "Follow the elite and experimental Clones of the Bad Batch as they find their way in a rapidly changing galaxy in the aftermath of the Clone Wars.",
                230,8.8)
        )
        tvShows.add(
            ResultTvShow(62286,
                "https://image.tmdb.org/t/p/w1280/4UjiPdFKJGJYdxwRs2Rzg7EmWqr.jpg",
                "https://image.tmdb.org/t/p/w1280/58PON1OrnBiX6CqEHgeWKVwrCn6.jpg",
                "Fear the Walking Dead",
                "2015-08-23",
                "What did the world look like as it was transforming into the horrifying apocalypse depicted in \\\"The Walking Dead\\\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
                3574,7.6)
        )
        tvShows.add(
            ResultTvShow(69478,
                "https://image.tmdb.org/t/p/w1280/oIkxqt6ug5zT5ZSUUyc1Iqopf02.jpg",
                "https://image.tmdb.org/t/p/w1280/jXB3OoWPkojsOP2O2OoLCeAIDRS.jpg",
                "The Handmaid's Tale",
                "2017-04-26",
                "Set in a dystopian future, a woman is forced to live as a concubine under a fundamentalist theocratic dictatorship. A TV adaptation of Margaret Atwood's novel.",
                1390,8.2)
        )
        tvShows.add(
            ResultTvShow(94667,
                "https://image.tmdb.org/t/p/w1280/o1lAdiCYmCuDb25wyBCJQMeUhVA.jpg",
                "https://image.tmdb.org/t/p/w1280/18ybGiTcKVnXCYCAmVBIIy8CNhx.jpg",
                "De viaje con los Derbez",
                "2019-10-18",
                "",
                915,7.5)
        )
        tvShows.add(
            ResultTvShow(2734,
                "https://image.tmdb.org/t/p/w1280/jDCgWVlejIo8sQYxw3Yf1cVQUIL.jpg",
                "https://image.tmdb.org/t/p/w1280/cD9PxbrdWYgL7MUpD9eOYuiSe2P.jpg",
                "Law & Order: Special Victims Unit",
                "1999-09-20",
                "In the criminal justice system, sexually-based offenses are considered especially heinous. In New York City, the dedicated detectives who investigate these vicious felonies are members of an elite squad known as the Special Victims Unit. These are their stories.",
                2436,7.8)
        )
        tvShows.add(
            ResultTvShow(65334,
                "https://image.tmdb.org/t/p/w1280/qxbW47zmgFyBVmZSIqD9NA1DEjT.jpg",
                "https://image.tmdb.org/t/p/w1280/9RqliZcoDEjSEISeA0LY9meAiNv.jpg",
                "Miraculous: Tales of Ladybug & Cat Noir",
                "2015-10-19",
                "Normal high school kids by day, protectors of Paris by night! Miraculous follows the heroic adventures of Marinette and Adrien as they transform into Ladybug and Cat Noir and set out to capture akumas, creatures responsible for turning the people of Paris into villains. But neither hero knows the other’s true identity – or that they’re classmates!",
                2672,7.9)
        )
        tvShows.add(
            ResultTvShow(65820,
                "https://image.tmdb.org/t/p/w1280/r8ODGmfNbZQlNhiJl2xQENE2jsk.jpg",
                "https://image.tmdb.org/t/p/w1280/5VltHQJXdmbSD6gEJw3R8R1Kbmc.jpg",
                "Van Helsing",
                "2016-09-23",
                "Vanessa Helsing, the daughter of famous vampire hunter and Dracula nemesis Abraham Van Helsing is resurrected five years in the future to find out that vampires have taken over the world and that she possesses unique power over them. She is humanity’s last hope to lead an offensive to take back what has been lost.",
                569,6.9)
        )
        return tvShows
    }
}