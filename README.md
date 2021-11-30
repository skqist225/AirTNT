# AirTNT

Air TNT web project inspired by Airbnb

@AboutProject
class ProjectVersionInfo {

public static final int Java = 11;

public static final String SpringBoot = "2.54";

public static final String Maven = "3.8";

public static final float TomCat = 9.0;
}

///Change countries table character set
ALTER TABLE airtnt.countries CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

///Drop foreign key check
SET FOREIGN_KEY_CHECKS = 0;

///List of MIMETYPE
https://www.iana.org/assignments/media-types/media-types.xhtml#image

//INSERT INTO `airtnt`.`reviews` (`created_at`, `comment`, `final_rating`, `accuracy_rating`, `checkin_rating`, `cleanliness_rating`, `contact_rating`, `location_rating`, `value_rating`, `booking_id`, `user_id`, `room_id`) VALUES (NOW(), 'hay lam', 4, 4, 4, 4, 4, 4, 4, 1, 17, 1);
