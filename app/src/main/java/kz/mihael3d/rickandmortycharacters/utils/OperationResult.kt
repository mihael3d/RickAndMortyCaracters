package kz.mihael3d.rickandmortycharacters.utils

import kotlinx.coroutines.CancellationException

sealed class OperationResult<out S, out E> {

     data class Success<out S>(val result: S) : OperationResult<S, Nothing>()

     data class Error<out E>(val result: E) : OperationResult<Nothing, E>()

}

inline fun <S, R> S.runOperationCatching(block: S.() -> R): OperationResult<R, Throwable> {
    return try {
        OperationResult.Success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        OperationResult.Error(e)
    }
}
inline fun <S, E> OperationResult<S, E>.doOnError(block: (E) -> Unit) : OperationResult<S, E> {
    if (this is OperationResult.Error) {
    block(this.result)
    }
    return this
}

inline fun <S, E, R> OperationResult<S, E>.mapSuccess(block: (S) -> R) : OperationResult<R, E> =
    when (this) {
        is OperationResult.Success -> OperationResult.Success(result = block(this.result))
        is OperationResult.Error -> OperationResult.Error(result = this.result)
    }

inline fun <S, E> OperationResult<S, E>.doOnSuccess(block: (S) -> Unit): OperationResult<S, E> {
    if (this is OperationResult.Success) {
        block(this.result)
    }
    return this
}
