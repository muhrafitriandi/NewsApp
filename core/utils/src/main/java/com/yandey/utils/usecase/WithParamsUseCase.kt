package com.yandey.utils.usecase

interface WithParamsUseCase<in Params, out Result> {
    operator fun invoke(param: Params): Result = execute(param)

    fun execute(param: Params): Result
}