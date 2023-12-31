package com.example.pharmeasy

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }

    fun getErrorMessage(): String{
        return if(this is Error)
            this.exception.message ?: "Something went wrong. Please Try Again"
        else
            ""
    }

}

val Result<*>.succeeded
    get() = this is Result.Success && data != null
