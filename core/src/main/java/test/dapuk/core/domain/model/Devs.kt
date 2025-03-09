package test.dapuk.core.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
@Keep
@Parcelize
data class Devs (
    val id: Int,
    val name: String,
    val image: String,
    val gamesCount: Int,
    val imageBackground: String,
    val slug: String

) : Parcelable

@Parcelize
data class DevsResultsItem(
    val image: String,
    val gamesCount: Int,
    val id: Int,
    val imageBackground: String,
    val slug: String,
): Parcelable

@Parcelize
data class DevsDetail(
    val id: Int,
    val name: String,
    val image: String,
    val gamesCount: Int,
    val imageBackground: String,
    val slug: String,
    val rating: String,
    val description: String,
    val ratingTop: Int,
    val updated: String,
    val reviewsCount: Int
): Parcelable
