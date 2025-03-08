package test.dapuk.core.data.remote

import com.google.gson.annotations.SerializedName

data class GamesItem(

	@field:SerializedName("added")
	val added: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("slug")
	val slug: String
)

data class GameResponse(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("previous")
	val previous: Any,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("results")
	val results: List<ResultsItem>
)

data class ResultsItem(

    @field:SerializedName("image")
	val image: String,

    @field:SerializedName("games_count")
	val gamesCount: Int,

    @field:SerializedName("name")
	val name: String,

    @field:SerializedName("games")
	val games: List<GamesItem>,

    @field:SerializedName("positions")
	val positions: List<PositionsItem>,

    @field:SerializedName("id")
	val id: Int,

    @field:SerializedName("image_background")
	val imageBackground: String,

    @field:SerializedName("slug")
	val slug: String,
)

data class PositionsItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("slug")
	val slug: String
)


data class Detail(

    @field:SerializedName("id")
	val id: Int,

    @field:SerializedName("name")
	val name: String,

    @field:SerializedName("games_count")
	val gamesCount: Int,

    @field:SerializedName("image_background")
	val imageBackground: String,

    @field:SerializedName("image")
	val image: String,

    @field:SerializedName("rating")
	val rating: String,

    @field:SerializedName("description")
	val description: String,

    @field:SerializedName("positions")
	val positions: List<PositionsItem>,

    @field:SerializedName("platforms")
	val platforms: Platforms,

    @field:SerializedName("rating_top")
	val ratingTop: Int,

    @field:SerializedName("ratings")
	val ratings: List<RatingsItem>,

    @field:SerializedName("timeline")
	val timeline: List<TimelineItem>,

    @field:SerializedName("updated")
	val updated: String,

    @field:SerializedName("slug")
	val slug: String,

    @field:SerializedName("reviews_count")
	val reviewsCount: Int
)


data class TimelineItem(

	@field:SerializedName("year")
	val year: Int,

	@field:SerializedName("count")
	val count: Int
)


data class Platform(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("slug")
	val slug: String
)

data class Platforms(

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("results")
	val results: List<ResultsItem>
)

data class RatingsItem(

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("percent")
	val percent: Any
)



