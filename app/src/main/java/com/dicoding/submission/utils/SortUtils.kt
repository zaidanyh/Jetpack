package com.dicoding.submission.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val BEST = "Best"
    const val WORST = "Worst"
    const val RANDOM = "Random"
    const val MOVIE_ENTITY = "movie_entities"
    const val TV_SHOW_ENTITY = "tv_entities"

    fun getSortedQuery(filter: String, table_name: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM $table_name ")
        when(filter) {
            BEST -> simpleQuery.append("ORDER BY vote_average DESC")
            WORST -> simpleQuery.append("ORDER BY vote_average ASC")
            RANDOM -> simpleQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}