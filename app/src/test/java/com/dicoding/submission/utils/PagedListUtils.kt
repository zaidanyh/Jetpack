package com.dicoding.submission.utils

import androidx.paging.PagedList
import org.mockito.Mockito.*

@Suppress("UNCHECKED_CAST")
object PagedListUtils {
    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList = mock(PagedList::class.java)
        `when`(pagedList[anyInt()]).then { invocation ->
            val index = invocation.arguments[0] as Int
            list[index]
        }
        `when`(pagedList.size).thenReturn(list.size)

        return pagedList as PagedList<T>
    }
}