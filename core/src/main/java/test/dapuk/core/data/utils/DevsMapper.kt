package test.dapuk.core.data.utils

import test.dapuk.core.data.local.DevsEntity
import test.dapuk.core.data.remote.Detail
import test.dapuk.core.data.remote.ResultsItem
import test.dapuk.core.domain.model.Devs
import test.dapuk.core.domain.model.DevsDetail

fun ResultsItem.toDomain() = Devs(id, name, image, gamesCount, imageBackground, slug)
fun Detail.toDomain() = DevsDetail(id, name, image, gamesCount, imageBackground, slug, rating, description, ratingTop, updated, reviewsCount)
fun DevsEntity.toDomain() = Devs(id, name, image, gamesCount, imageBackground, slug)
fun Devs.toEntity() = DevsEntity(id, name, image, gamesCount, imageBackground, slug)
