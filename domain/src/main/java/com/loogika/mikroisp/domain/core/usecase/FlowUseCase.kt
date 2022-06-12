package com.loogika.mikroisp.domain.core.usecase

import com.loogika.mikroisp.domain.core.viewstate.Uitate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

abstract class FlowUseCase<in P, R> {
    suspend operator fun invoke(parameters: P): Flow<Uitate<R>> =
        execute(parameters)
            .catch { e -> emit(Uitate.Failure(Exception(e))) }

    protected abstract suspend fun execute(parameters: P): Flow<Uitate<R>>
}

abstract class EmptyParameterFlowUseCase<R> {
    suspend operator fun invoke(): Flow<Uitate<R>> =
        execute()
            .catch { e -> emit(Uitate.Failure(Exception(e))) }

    protected abstract suspend fun execute(): Flow<Uitate<R>>
}
