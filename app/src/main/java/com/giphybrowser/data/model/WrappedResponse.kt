package com.giphybrowser.data.model

import com.giphybrowser.data.model.meta.MetaBean
import com.giphybrowser.data.model.pagination.PaginationBean

/**
 * Created by {Kapil Sachan} on 28/12/2020.
 */

data class WrappedResponse<T>(
    var data: T? = null,
    var paginationBean: PaginationBean? = null,
    var metaBean: MetaBean? = null,
    var failureResponse: FailureResponse? = null
)