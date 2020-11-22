package com.swap.spacex.features.feed.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class PastLaunchesResponse(
	val flight_number: Int,
	var mission_name: String?,
	val mission_id: ArrayList<String>?,
	val upcoming: Boolean,
	val launch_year: Int,
	val launch_date_unix: Int,
	val launch_date_utc: String?,
	val launch_date_local: String?,
	val is_tentative: Boolean,
	val tentative_max_precision: String?,
	val tbd: Boolean,
	val launch_window: Int,
	val rocket: Rocket?,
	val ships: ArrayList<String>?,
	val telemetry: Telemetry?,
	val launch_site: Launch_site?,
	val launch_success: Boolean,
	val launch_failure_details: Launch_failure_details?,
	val links: Links?,
	val details: String?,
	val static_fire_date_utc: String?,
	val static_fire_date_unix: Int,
	val timeline: Timeline?,
	val crew: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
		parcel.readInt(),
		parcel.readString(),
		parcel.createStringArrayList(),
		parcel.readByte() != 0.toByte(),
		parcel.readInt(),
		parcel.readInt(),
		parcel.readString(),
		parcel.readString(),
		parcel.readByte() != 0.toByte(),
		parcel.readString(),
		parcel.readByte() != 0.toByte(),
		parcel.readInt(),
		parcel.readParcelable(Rocket::class.java.classLoader),
		parcel.createStringArrayList(),
		parcel.readParcelable(Telemetry::class.java.classLoader),
		parcel.readParcelable(Launch_site::class.java.classLoader),
		parcel.readByte() != 0.toByte(),
		parcel.readParcelable(Launch_failure_details::class.java.classLoader),
		parcel.readParcelable(Links::class.java.classLoader),
		parcel.readString(),
		parcel.readString(),
		parcel.readInt(),
		parcel.readParcelable(Timeline::class.java.classLoader),
		parcel.readString()
	)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(flight_number)
        parcel.writeString(mission_name)
        parcel.writeStringList(mission_id)
        parcel.writeByte(if (upcoming) 1 else 0)
        parcel.writeInt(launch_year)
        parcel.writeInt(launch_date_unix)
        parcel.writeString(launch_date_utc)
        parcel.writeString(launch_date_local)
        parcel.writeByte(if (is_tentative) 1 else 0)
        parcel.writeString(tentative_max_precision)
        parcel.writeByte(if (tbd) 1 else 0)
        parcel.writeInt(launch_window)
        parcel.writeParcelable(rocket, flags)
        parcel.writeStringList(ships)
        parcel.writeParcelable(telemetry, flags)
        parcel.writeParcelable(launch_site, flags)
        parcel.writeByte(if (launch_success) 1 else 0)
        parcel.writeParcelable(launch_failure_details, flags)
        parcel.writeParcelable(links, flags)
        parcel.writeString(details)
        parcel.writeString(static_fire_date_utc)
        parcel.writeInt(static_fire_date_unix)
        parcel.writeParcelable(timeline, flags)
        parcel.writeString(crew)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PastLaunchesResponse> {
        override fun createFromParcel(parcel: Parcel): PastLaunchesResponse {
            return PastLaunchesResponse(parcel)
        }

        override fun newArray(size: Int): Array<PastLaunchesResponse?> {
            return arrayOfNulls(size)
        }
    }
}

data class Rocket(
	val rocket_id: String?,
	val rocket_name: String?,
	val rocket_type: String?,
	val first_stage: First_stage?,
	val second_stage: Second_stage?,
	val fairings: Fairings?
) : Parcelable {
    constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readParcelable(First_stage::class.java.classLoader),
		parcel.readParcelable(Second_stage::class.java.classLoader),
		parcel.readParcelable(Fairings::class.java.classLoader)
	)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(rocket_id)
        parcel.writeString(rocket_name)
        parcel.writeString(rocket_type)
        parcel.writeParcelable(first_stage, flags)
        parcel.writeParcelable(second_stage, flags)
        parcel.writeParcelable(fairings, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rocket> {
        override fun createFromParcel(parcel: Parcel): Rocket {
            return Rocket(parcel)
        }

        override fun newArray(size: Int): Array<Rocket?> {
            return arrayOfNulls(size)
        }
    }
}

data class Telemetry(val flight_club: String?) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(flight_club)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Telemetry> {
        override fun createFromParcel(parcel: Parcel): Telemetry {
            return Telemetry(parcel)
        }

        override fun newArray(size: Int): Array<Telemetry?> {
            return arrayOfNulls(size)
        }
    }
}

data class Timeline(val webcast_liftoff: Int) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(webcast_liftoff)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Timeline> {
        override fun createFromParcel(parcel: Parcel): Timeline {
            return Timeline(parcel)
        }

        override fun newArray(size: Int): Array<Timeline?> {
            return arrayOfNulls(size)
        }
    }
}

data class Second_stage(val block: Int, val payloads: ArrayList<Payloads>?) : Parcelable {
    constructor(parcel: Parcel) : this(
		parcel.readInt(),
		parcel.createTypedArrayList(Payloads)
	)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(block)
        parcel.writeTypedList(payloads)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Second_stage> {
        override fun createFromParcel(parcel: Parcel): Second_stage {
            return Second_stage(parcel)
        }

        override fun newArray(size: Int): Array<Second_stage?> {
            return arrayOfNulls(size)
        }
    }
}

data class Payloads(
	val payload_id: String?,
	val norad_id: ArrayList<String>?,
	val reused: Boolean,
	val customers: ArrayList<String>?,
	val nationality: String?,
	val manufacturer: String?,
	val payload_type: String?,
	val payload_mass_kg: Float,
	val payload_mass_lbs: Float,
	val orbit: String?,
	val orbit_params: Orbit_params?
) : Parcelable {
    constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.createStringArrayList(),
		parcel.readByte() != 0.toByte(),
		parcel.createStringArrayList(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readFloat(),
		parcel.readFloat(),
		parcel.readString(),
		parcel.readParcelable(Orbit_params::class.java.classLoader)
	)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(payload_id)
        parcel.writeStringList(norad_id)
        parcel.writeByte(if (reused) 1 else 0)
        parcel.writeStringList(customers)
        parcel.writeString(nationality)
        parcel.writeString(manufacturer)
        parcel.writeString(payload_type)
        parcel.writeFloat(payload_mass_kg)
        parcel.writeFloat(payload_mass_lbs)
        parcel.writeString(orbit)
        parcel.writeParcelable(orbit_params, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Payloads> {
        override fun createFromParcel(parcel: Parcel): Payloads {
            return Payloads(parcel)
        }

        override fun newArray(size: Int): Array<Payloads?> {
            return arrayOfNulls(size)
        }
    }
}


data class Orbit_params(
	val reference_system: String?,
	val regime: String?,
	val longitude: String?,
	val semi_major_axis_km: String?,
	val eccentricity: String?,
	val periapsis_km: Float,
	val apoapsis_km: Float,
	val inclination_deg: Float,
	val period_min: String?,
	val lifespan_years: String?,
	val epoch: String?,
	val mean_motion: String?,
	val raan: String?,
	val arg_of_pericenter: String?,
	val mean_anomaly: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readFloat(),
		parcel.readFloat(),
		parcel.readFloat(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(reference_system)
        parcel.writeString(regime)
        parcel.writeString(longitude)
        parcel.writeString(semi_major_axis_km)
        parcel.writeString(eccentricity)
        parcel.writeFloat(periapsis_km)
        parcel.writeFloat(apoapsis_km)
        parcel.writeFloat(inclination_deg)
        parcel.writeString(period_min)
        parcel.writeString(lifespan_years)
        parcel.writeString(epoch)
        parcel.writeString(mean_motion)
        parcel.writeString(raan)
        parcel.writeString(arg_of_pericenter)
        parcel.writeString(mean_anomaly)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Orbit_params> {
        override fun createFromParcel(parcel: Parcel): Orbit_params {
            return Orbit_params(parcel)
        }

        override fun newArray(size: Int): Array<Orbit_params?> {
            return arrayOfNulls(size)
        }
    }
}


data class Links(
	val mission_patch: String?,
	val mission_patch_small: String?,
	val reddit_campaign: String?,
	val reddit_launch: String?,
	val reddit_recovery: String?,
	val reddit_media: String?,
	val presskit: String?,
	val article_link: String?,
	val wikipedia: String?,
	val video_link: String?,
	val youtube_id: String?,
	val flickr_images: ArrayList<String>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.createStringArrayList()
	)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(mission_patch)
        parcel.writeString(mission_patch_small)
        parcel.writeString(reddit_campaign)
        parcel.writeString(reddit_launch)
        parcel.writeString(reddit_recovery)
        parcel.writeString(reddit_media)
        parcel.writeString(presskit)
        parcel.writeString(article_link)
        parcel.writeString(wikipedia)
        parcel.writeString(video_link)
        parcel.writeString(youtube_id)
        parcel.writeStringList(flickr_images)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Links> {
        override fun createFromParcel(parcel: Parcel): Links {
            return Links(parcel)
        }

        override fun newArray(size: Int): Array<Links?> {
            return arrayOfNulls(size)
        }
    }
}


data class Launch_site(

	val site_id: String?,
	val site_name: String?,
	val site_name_long: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(site_id)
        parcel.writeString(site_name)
        parcel.writeString(site_name_long)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Launch_site> {
        override fun createFromParcel(parcel: Parcel): Launch_site {
            return Launch_site(parcel)
        }

        override fun newArray(size: Int): Array<Launch_site?> {
            return arrayOfNulls(size)
        }
    }
}

data class Launch_failure_details(
	val time: Int,
	val altitude: String?,
	val reason: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
		parcel.readInt(),
		parcel.readString(),
		parcel.readString()
	)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(time)
        parcel.writeString(altitude)
        parcel.writeString(reason)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Launch_failure_details> {
        override fun createFromParcel(parcel: Parcel): Launch_failure_details {
            return Launch_failure_details(parcel)
        }

        override fun newArray(size: Int): Array<Launch_failure_details?> {
            return arrayOfNulls(size)
        }
    }
}

data class First_stage(val cores: ArrayList<Cores>?) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Cores))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(cores)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<First_stage> {
        override fun createFromParcel(parcel: Parcel): First_stage {
            return First_stage(parcel)
        }

        override fun newArray(size: Int): Array<First_stage?> {
            return arrayOfNulls(size)
        }
    }
}

data class Fairings(
	val reused: Boolean,
	val recovery_attempt: Boolean,
	val recovered: Boolean,
	val ship: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
		parcel.readByte() != 0.toByte(),
		parcel.readByte() != 0.toByte(),
		parcel.readByte() != 0.toByte(),
		parcel.readString()
	)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (reused) 1 else 0)
        parcel.writeByte(if (recovery_attempt) 1 else 0)
        parcel.writeByte(if (recovered) 1 else 0)
        parcel.writeString(ship)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Fairings> {
        override fun createFromParcel(parcel: Parcel): Fairings {
            return Fairings(parcel)
        }

        override fun newArray(size: Int): Array<Fairings?> {
            return arrayOfNulls(size)
        }
    }
}

data class Cores(
	val core_serial: String?,
	val flight: Int,
	val block: String?,
	val gridfins: Boolean,
	val legs: Boolean,
	val reused: Boolean,
	val land_success: String?,
	val landing_intent: Boolean,
	val landing_type: String?,
	val landing_vehicle: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readInt(),
		parcel.readString(),
		parcel.readByte() != 0.toByte(),
		parcel.readByte() != 0.toByte(),
		parcel.readByte() != 0.toByte(),
		parcel.readString(),
		parcel.readByte() != 0.toByte(),
		parcel.readString(),
		parcel.readString()
	)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(core_serial)
        parcel.writeInt(flight)
        parcel.writeString(block)
        parcel.writeByte(if (gridfins) 1 else 0)
        parcel.writeByte(if (legs) 1 else 0)
        parcel.writeByte(if (reused) 1 else 0)
        parcel.writeString(land_success)
        parcel.writeByte(if (landing_intent) 1 else 0)
        parcel.writeString(landing_type)
        parcel.writeString(landing_vehicle)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cores> {
        override fun createFromParcel(parcel: Parcel): Cores {
            return Cores(parcel)
        }

        override fun newArray(size: Int): Array<Cores?> {
            return arrayOfNulls(size)
        }
    }
}