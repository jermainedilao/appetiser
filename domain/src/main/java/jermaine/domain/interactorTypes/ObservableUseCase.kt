package jermaine.domain.interactorTypes

import io.reactivex.Observable
import io.reactivex.Single


/**
 * Use this when creating an interactor/usecase type that returns Single.
 */
interface ObservableUseCase<R> {
    fun execute(): Observable<R>
}